package  ma.zyn.app.ws.facade.admin.delivery;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.delivery.Delivery;
import ma.zyn.app.dao.criteria.core.delivery.DeliveryCriteria;
import ma.zyn.app.service.facade.admin.delivery.DeliveryAdminService;
import ma.zyn.app.ws.converter.delivery.DeliveryConverter;
import ma.zyn.app.ws.dto.delivery.DeliveryDto;
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
@RequestMapping("/api/admin/delivery/")
public class DeliveryRestAdmin {




    @Operation(summary = "Finds a list of all deliverys")
    @GetMapping("")
    public ResponseEntity<List<DeliveryDto>> findAll() throws Exception {
        ResponseEntity<List<DeliveryDto>> res = null;
        List<Delivery> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DeliveryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a delivery by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DeliveryDto> findById(@PathVariable Long id) {
        Delivery t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DeliveryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  delivery")
    @PostMapping("")
    public ResponseEntity<DeliveryDto> save(@RequestBody DeliveryDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Delivery myT = converter.toItem(dto);
            Delivery t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DeliveryDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  delivery")
    @PutMapping("")
    public ResponseEntity<DeliveryDto> update(@RequestBody DeliveryDto dto) throws Exception {
        ResponseEntity<DeliveryDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Delivery t = service.findById(dto.getId());
            converter.copy(dto,t);
            Delivery updated = service.update(t);
            DeliveryDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of delivery")
    @PostMapping("multiple")
    public ResponseEntity<List<DeliveryDto>> delete(@RequestBody List<DeliveryDto> dtos) throws Exception {
        ResponseEntity<List<DeliveryDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Delivery> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified delivery")
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

    @Operation(summary = "find by address id")
    @GetMapping("address/id/{id}")
    public List<DeliveryDto> findByAddressId(@PathVariable Long id){
        return findDtos(service.findByAddressId(id));
    }
    @Operation(summary = "delete by address id")
    @DeleteMapping("address/id/{id}")
    public int deleteByAddressId(@PathVariable Long id){
        return service.deleteByAddressId(id);
    }

    @Operation(summary = "Finds a delivery and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DeliveryDto> findWithAssociatedLists(@PathVariable Long id) {
        Delivery loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DeliveryDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds deliverys by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DeliveryDto>> findByCriteria(@RequestBody DeliveryCriteria criteria) throws Exception {
        ResponseEntity<List<DeliveryDto>> res = null;
        List<Delivery> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DeliveryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated deliverys by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DeliveryCriteria criteria) throws Exception {
        List<Delivery> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DeliveryDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets delivery data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DeliveryCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DeliveryDto> findDtos(List<Delivery> list){
        converter.initObject(true);
        List<DeliveryDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DeliveryDto> getDtoResponseEntity(DeliveryDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public DeliveryRestAdmin(DeliveryAdminService service, DeliveryConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final DeliveryAdminService service;
    private final DeliveryConverter converter;





}
