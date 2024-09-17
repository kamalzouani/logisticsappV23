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

import ma.zyn.app.bean.core.order.OrderStatus;
import ma.zyn.app.dao.criteria.core.order.OrderStatusCriteria;
import ma.zyn.app.service.facade.admin.order.OrderStatusAdminService;
import ma.zyn.app.ws.converter.order.OrderStatusConverter;
import ma.zyn.app.ws.dto.order.OrderStatusDto;
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
@RequestMapping("/api/admin/orderStatus/")
public class OrderStatusRestAdmin {




    @Operation(summary = "Finds a list of all orderStatuss")
    @GetMapping("")
    public ResponseEntity<List<OrderStatusDto>> findAll() throws Exception {
        ResponseEntity<List<OrderStatusDto>> res = null;
        List<OrderStatus> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<OrderStatusDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all orderStatuss")
    @GetMapping("optimized")
    public ResponseEntity<List<OrderStatusDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<OrderStatusDto>> res = null;
        List<OrderStatus> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<OrderStatusDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a orderStatus by id")
    @GetMapping("id/{id}")
    public ResponseEntity<OrderStatusDto> findById(@PathVariable Long id) {
        OrderStatus t = service.findById(id);
        if (t != null) {
            OrderStatusDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a orderStatus by label")
    @GetMapping("label/{label}")
    public ResponseEntity<OrderStatusDto> findByLabel(@PathVariable String label) {
	    OrderStatus t = service.findByReferenceEntity(new OrderStatus(label));
        if (t != null) {
            OrderStatusDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  orderStatus")
    @PostMapping("")
    public ResponseEntity<OrderStatusDto> save(@RequestBody OrderStatusDto dto) throws Exception {
        if(dto!=null){
            OrderStatus myT = converter.toItem(dto);
            OrderStatus t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                OrderStatusDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  orderStatus")
    @PutMapping("")
    public ResponseEntity<OrderStatusDto> update(@RequestBody OrderStatusDto dto) throws Exception {
        ResponseEntity<OrderStatusDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            OrderStatus t = service.findById(dto.getId());
            converter.copy(dto,t);
            OrderStatus updated = service.update(t);
            OrderStatusDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of orderStatus")
    @PostMapping("multiple")
    public ResponseEntity<List<OrderStatusDto>> delete(@RequestBody List<OrderStatusDto> dtos) throws Exception {
        ResponseEntity<List<OrderStatusDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<OrderStatus> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified orderStatus")
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


    @Operation(summary = "Finds a orderStatus and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<OrderStatusDto> findWithAssociatedLists(@PathVariable Long id) {
        OrderStatus loaded =  service.findWithAssociatedLists(id);
        OrderStatusDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds orderStatuss by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<OrderStatusDto>> findByCriteria(@RequestBody OrderStatusCriteria criteria) throws Exception {
        ResponseEntity<List<OrderStatusDto>> res = null;
        List<OrderStatus> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<OrderStatusDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated orderStatuss by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody OrderStatusCriteria criteria) throws Exception {
        List<OrderStatus> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<OrderStatusDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets orderStatus data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody OrderStatusCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<OrderStatusDto> findDtos(List<OrderStatus> list){
        List<OrderStatusDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<OrderStatusDto> getDtoResponseEntity(OrderStatusDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public OrderStatusRestAdmin(OrderStatusAdminService service, OrderStatusConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final OrderStatusAdminService service;
    private final OrderStatusConverter converter;





}
