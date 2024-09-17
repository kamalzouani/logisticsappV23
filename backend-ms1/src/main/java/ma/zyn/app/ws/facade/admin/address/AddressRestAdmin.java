package  ma.zyn.app.ws.facade.admin.address;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.address.Address;
import ma.zyn.app.dao.criteria.core.address.AddressCriteria;
import ma.zyn.app.service.facade.admin.address.AddressAdminService;
import ma.zyn.app.ws.converter.address.AddressConverter;
import ma.zyn.app.ws.dto.address.AddressDto;
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
@RequestMapping("/api/admin/address/")
public class AddressRestAdmin {




    @Operation(summary = "Finds a list of all addresss")
    @GetMapping("")
    public ResponseEntity<List<AddressDto>> findAll() throws Exception {
        ResponseEntity<List<AddressDto>> res = null;
        List<Address> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AddressDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a address by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable Long id) {
        Address t = service.findById(id);
        if (t != null) {
            AddressDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  address")
    @PostMapping("")
    public ResponseEntity<AddressDto> save(@RequestBody AddressDto dto) throws Exception {
        if(dto!=null){
            Address myT = converter.toItem(dto);
            Address t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AddressDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  address")
    @PutMapping("")
    public ResponseEntity<AddressDto> update(@RequestBody AddressDto dto) throws Exception {
        ResponseEntity<AddressDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Address t = service.findById(dto.getId());
            converter.copy(dto,t);
            Address updated = service.update(t);
            AddressDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of address")
    @PostMapping("multiple")
    public ResponseEntity<List<AddressDto>> delete(@RequestBody List<AddressDto> dtos) throws Exception {
        ResponseEntity<List<AddressDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Address> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified address")
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


    @Operation(summary = "Finds a address and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AddressDto> findWithAssociatedLists(@PathVariable Long id) {
        Address loaded =  service.findWithAssociatedLists(id);
        AddressDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds addresss by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AddressDto>> findByCriteria(@RequestBody AddressCriteria criteria) throws Exception {
        ResponseEntity<List<AddressDto>> res = null;
        List<Address> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AddressDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated addresss by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AddressCriteria criteria) throws Exception {
        List<Address> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<AddressDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets address data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AddressCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AddressDto> findDtos(List<Address> list){
        List<AddressDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AddressDto> getDtoResponseEntity(AddressDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public AddressRestAdmin(AddressAdminService service, AddressConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final AddressAdminService service;
    private final AddressConverter converter;





}
