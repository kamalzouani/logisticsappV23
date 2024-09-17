package  ma.zyn.app.ws.facade.customer.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.customer.Customer;
import ma.zyn.app.dao.criteria.core.customer.CustomerCriteria;
import ma.zyn.app.service.facade.customer.customer.CustomerCustomerService;
import ma.zyn.app.ws.converter.customer.CustomerConverter;
import ma.zyn.app.ws.dto.customer.CustomerDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import ma.zyn.app.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/customer/customer/")
public class CustomerRestCustomer {




    @Operation(summary = "Finds a list of all customers")
    @GetMapping("")
    public ResponseEntity<List<CustomerDto>> findAll() throws Exception {
        ResponseEntity<List<CustomerDto>> res = null;
        List<Customer> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<CustomerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all customers")
    @GetMapping("optimized")
    public ResponseEntity<List<CustomerDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CustomerDto>> res = null;
        List<Customer> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CustomerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a customer by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
        Customer t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CustomerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a customer by email")
    @GetMapping("email/{email}")
    public ResponseEntity<CustomerDto> findByEmail(@PathVariable String email) {
	    Customer t = service.findByReferenceEntity(new Customer(email));
        if (t != null) {
            converter.init(true);
            CustomerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  customer")
    @PostMapping("")
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Customer myT = converter.toItem(dto);
            Customer t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CustomerDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  customer")
    @PutMapping("")
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto dto) throws Exception {
        ResponseEntity<CustomerDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Customer t = service.findById(dto.getId());
            converter.copy(dto,t);
            Customer updated = service.update(t);
            CustomerDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of customer")
    @PostMapping("multiple")
    public ResponseEntity<List<CustomerDto>> delete(@RequestBody List<CustomerDto> dtos) throws Exception {
        ResponseEntity<List<CustomerDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Customer> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified customer")
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
    public List<CustomerDto> findByAddressId(@PathVariable Long id){
        return findDtos(service.findByAddressId(id));
    }
    @Operation(summary = "delete by address id")
    @DeleteMapping("address/id/{id}")
    public int deleteByAddressId(@PathVariable Long id){
        return service.deleteByAddressId(id);
    }

    @Operation(summary = "Finds a customer and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CustomerDto> findWithAssociatedLists(@PathVariable Long id) {
        Customer loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CustomerDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds customers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CustomerDto>> findByCriteria(@RequestBody CustomerCriteria criteria) throws Exception {
        ResponseEntity<List<CustomerDto>> res = null;
        List<Customer> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CustomerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated customers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CustomerCriteria criteria) throws Exception {
        List<Customer> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<CustomerDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets customer data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CustomerCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CustomerDto> findDtos(List<Customer> list){
        converter.initObject(true);
        List<CustomerDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CustomerDto> getDtoResponseEntity(CustomerDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public CustomerRestCustomer(CustomerCustomerService service, CustomerConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CustomerCustomerService service;
    private final CustomerConverter converter;





}
