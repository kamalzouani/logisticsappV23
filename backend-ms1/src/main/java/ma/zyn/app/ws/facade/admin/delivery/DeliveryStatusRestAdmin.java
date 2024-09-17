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

import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import ma.zyn.app.dao.criteria.core.delivery.DeliveryStatusCriteria;
import ma.zyn.app.service.facade.admin.delivery.DeliveryStatusAdminService;
import ma.zyn.app.ws.converter.delivery.DeliveryStatusConverter;
import ma.zyn.app.ws.dto.delivery.DeliveryStatusDto;
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
@RequestMapping("/api/admin/deliveryStatus/")
public class DeliveryStatusRestAdmin {




    @Operation(summary = "Finds a list of all deliveryStatuss")
    @GetMapping("")
    public ResponseEntity<List<DeliveryStatusDto>> findAll() throws Exception {
        ResponseEntity<List<DeliveryStatusDto>> res = null;
        List<DeliveryStatus> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DeliveryStatusDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all deliveryStatuss")
    @GetMapping("optimized")
    public ResponseEntity<List<DeliveryStatusDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DeliveryStatusDto>> res = null;
        List<DeliveryStatus> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DeliveryStatusDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a deliveryStatus by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DeliveryStatusDto> findById(@PathVariable Long id) {
        DeliveryStatus t = service.findById(id);
        if (t != null) {
            DeliveryStatusDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a deliveryStatus by label")
    @GetMapping("label/{label}")
    public ResponseEntity<DeliveryStatusDto> findByLabel(@PathVariable String label) {
	    DeliveryStatus t = service.findByReferenceEntity(new DeliveryStatus(label));
        if (t != null) {
            DeliveryStatusDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  deliveryStatus")
    @PostMapping("")
    public ResponseEntity<DeliveryStatusDto> save(@RequestBody DeliveryStatusDto dto) throws Exception {
        if(dto!=null){
            DeliveryStatus myT = converter.toItem(dto);
            DeliveryStatus t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DeliveryStatusDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  deliveryStatus")
    @PutMapping("")
    public ResponseEntity<DeliveryStatusDto> update(@RequestBody DeliveryStatusDto dto) throws Exception {
        ResponseEntity<DeliveryStatusDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DeliveryStatus t = service.findById(dto.getId());
            converter.copy(dto,t);
            DeliveryStatus updated = service.update(t);
            DeliveryStatusDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of deliveryStatus")
    @PostMapping("multiple")
    public ResponseEntity<List<DeliveryStatusDto>> delete(@RequestBody List<DeliveryStatusDto> dtos) throws Exception {
        ResponseEntity<List<DeliveryStatusDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DeliveryStatus> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified deliveryStatus")
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


    @Operation(summary = "Finds a deliveryStatus and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DeliveryStatusDto> findWithAssociatedLists(@PathVariable Long id) {
        DeliveryStatus loaded =  service.findWithAssociatedLists(id);
        DeliveryStatusDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds deliveryStatuss by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DeliveryStatusDto>> findByCriteria(@RequestBody DeliveryStatusCriteria criteria) throws Exception {
        ResponseEntity<List<DeliveryStatusDto>> res = null;
        List<DeliveryStatus> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DeliveryStatusDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated deliveryStatuss by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DeliveryStatusCriteria criteria) throws Exception {
        List<DeliveryStatus> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DeliveryStatusDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets deliveryStatus data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DeliveryStatusCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DeliveryStatusDto> findDtos(List<DeliveryStatus> list){
        List<DeliveryStatusDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DeliveryStatusDto> getDtoResponseEntity(DeliveryStatusDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public DeliveryStatusRestAdmin(DeliveryStatusAdminService service, DeliveryStatusConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final DeliveryStatusAdminService service;
    private final DeliveryStatusConverter converter;





}
