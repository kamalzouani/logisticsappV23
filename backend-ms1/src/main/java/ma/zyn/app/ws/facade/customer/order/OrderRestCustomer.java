package  ma.zyn.app.ws.facade.customer.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.order.Order;
import ma.zyn.app.dao.criteria.core.order.OrderCriteria;
import ma.zyn.app.service.facade.customer.order.OrderCustomerService;
import ma.zyn.app.ws.converter.order.OrderConverter;
import ma.zyn.app.ws.dto.order.OrderDto;
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
@RequestMapping("/api/customer/order/")
public class OrderRestCustomer {




    @Operation(summary = "Finds a list of all orders")
    @GetMapping("")
    public ResponseEntity<List<OrderDto>> findAll() throws Exception {
        ResponseEntity<List<OrderDto>> res = null;
        List<Order> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<OrderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a order by id")
    @GetMapping("id/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        Order t = service.findById(id);
        if (t != null) {
            converter.init(true);
            OrderDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  order")
    @PostMapping("")
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Order myT = converter.toItem(dto);
            Order t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                OrderDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  order")
    @PutMapping("")
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto dto) throws Exception {
        ResponseEntity<OrderDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Order t = service.findById(dto.getId());
            converter.copy(dto,t);
            Order updated = service.update(t);
            OrderDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of order")
    @PostMapping("multiple")
    public ResponseEntity<List<OrderDto>> delete(@RequestBody List<OrderDto> dtos) throws Exception {
        ResponseEntity<List<OrderDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Order> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified order")
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


    @Operation(summary = "Finds a order and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<OrderDto> findWithAssociatedLists(@PathVariable Long id) {
        Order loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        OrderDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds orders by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<OrderDto>> findByCriteria(@RequestBody OrderCriteria criteria) throws Exception {
        ResponseEntity<List<OrderDto>> res = null;
        List<Order> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<OrderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated orders by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody OrderCriteria criteria) throws Exception {
        List<Order> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<OrderDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets order data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody OrderCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<OrderDto> findDtos(List<Order> list){
        converter.initList(false);
        converter.initObject(true);
        List<OrderDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<OrderDto> getDtoResponseEntity(OrderDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public OrderRestCustomer(OrderCustomerService service, OrderConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final OrderCustomerService service;
    private final OrderConverter converter;





}
