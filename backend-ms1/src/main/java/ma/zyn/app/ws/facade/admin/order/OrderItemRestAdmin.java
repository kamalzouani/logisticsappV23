package  ma.zyn.app.ws.facade.admin.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.order.OrderItem;
import ma.zyn.app.dao.criteria.core.order.OrderItemCriteria;
import ma.zyn.app.service.facade.admin.order.OrderItemAdminService;
import ma.zyn.app.ws.converter.order.OrderItemConverter;
import ma.zyn.app.ws.dto.order.OrderItemDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/orderItem/")
public class OrderItemRestAdmin {




    @Operation(summary = "Finds a list of all orderItems")
    @GetMapping("")
    public ResponseEntity<List<OrderItemDto>> findAll() throws Exception {
        ResponseEntity<List<OrderItemDto>> res = null;
        List<OrderItem> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<OrderItemDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a orderItem by id")
    @GetMapping("id/{id}")
    public ResponseEntity<OrderItemDto> findById(@PathVariable Long id) {
        OrderItem t = service.findById(id);
        if (t != null) {
            converter.init(true);
            OrderItemDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  orderItem")
    @PostMapping("")
    public ResponseEntity<OrderItemDto> save(@RequestBody OrderItemDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            OrderItem myT = converter.toItem(dto);
            OrderItem t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                OrderItemDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  orderItem")
    @PutMapping("")
    public ResponseEntity<OrderItemDto> update(@RequestBody OrderItemDto dto) throws Exception {
        ResponseEntity<OrderItemDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            OrderItem t = service.findById(dto.getId());
            converter.copy(dto,t);
            OrderItem updated = service.update(t);
            OrderItemDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of orderItem")
    @PostMapping("multiple")
    public ResponseEntity<List<OrderItemDto>> delete(@RequestBody List<OrderItemDto> dtos) throws Exception {
        ResponseEntity<List<OrderItemDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<OrderItem> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified orderItem")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by product id")
    @GetMapping("product/id/{id}")
    public List<OrderItemDto> findByProductId(@PathVariable Long id){
        return findDtos(service.findByProductId(id));
    }
    @Operation(summary = "delete by product id")
    @DeleteMapping("product/id/{id}")
    public int deleteByProductId(@PathVariable Long id){
        return service.deleteByProductId(id);
    }
    @Operation(summary = "find by order id")
    @GetMapping("order/id/{id}")
    public List<OrderItemDto> findByOrderId(@PathVariable Long id){
        return findDtos(service.findByOrderId(id));
    }
    @Operation(summary = "delete by order id")
    @DeleteMapping("order/id/{id}")
    public int deleteByOrderId(@PathVariable Long id){
        return service.deleteByOrderId(id);
    }

    @Operation(summary = "Finds a orderItem and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<OrderItemDto> findWithAssociatedLists(@PathVariable Long id) {
        OrderItem loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        OrderItemDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds orderItems by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<OrderItemDto>> findByCriteria(@RequestBody OrderItemCriteria criteria) throws Exception {
        ResponseEntity<List<OrderItemDto>> res = null;
        List<OrderItem> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<OrderItemDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated orderItems by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody OrderItemCriteria criteria) throws Exception {
        List<OrderItem> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<OrderItemDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets orderItem data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody OrderItemCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<OrderItemDto> findDtos(List<OrderItem> list){
        converter.initObject(true);
        List<OrderItemDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<OrderItemDto> getDtoResponseEntity(OrderItemDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public OrderItemRestAdmin(OrderItemAdminService service, OrderItemConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final OrderItemAdminService service;
    private final OrderItemConverter converter;





}
