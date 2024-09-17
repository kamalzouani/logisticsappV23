package  ma.zyn.app.ws.facade.admin.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.product.ProductCategory;
import ma.zyn.app.dao.criteria.core.product.ProductCategoryCriteria;
import ma.zyn.app.service.facade.admin.product.ProductCategoryAdminService;
import ma.zyn.app.ws.converter.product.ProductCategoryConverter;
import ma.zyn.app.ws.dto.product.ProductCategoryDto;
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
@RequestMapping("/api/admin/productCategory/")
public class ProductCategoryRestAdmin {




    @Operation(summary = "Finds a list of all productCategorys")
    @GetMapping("")
    public ResponseEntity<List<ProductCategoryDto>> findAll() throws Exception {
        ResponseEntity<List<ProductCategoryDto>> res = null;
        List<ProductCategory> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProductCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a productCategory by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProductCategoryDto> findById(@PathVariable Long id) {
        ProductCategory t = service.findById(id);
        if (t != null) {
            ProductCategoryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  productCategory")
    @PostMapping("")
    public ResponseEntity<ProductCategoryDto> save(@RequestBody ProductCategoryDto dto) throws Exception {
        if(dto!=null){
            ProductCategory myT = converter.toItem(dto);
            ProductCategory t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProductCategoryDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  productCategory")
    @PutMapping("")
    public ResponseEntity<ProductCategoryDto> update(@RequestBody ProductCategoryDto dto) throws Exception {
        ResponseEntity<ProductCategoryDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProductCategory t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProductCategory updated = service.update(t);
            ProductCategoryDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of productCategory")
    @PostMapping("multiple")
    public ResponseEntity<List<ProductCategoryDto>> delete(@RequestBody List<ProductCategoryDto> dtos) throws Exception {
        ResponseEntity<List<ProductCategoryDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProductCategory> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified productCategory")
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


    @Operation(summary = "Finds a productCategory and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProductCategoryDto> findWithAssociatedLists(@PathVariable Long id) {
        ProductCategory loaded =  service.findWithAssociatedLists(id);
        ProductCategoryDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds productCategorys by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProductCategoryDto>> findByCriteria(@RequestBody ProductCategoryCriteria criteria) throws Exception {
        ResponseEntity<List<ProductCategoryDto>> res = null;
        List<ProductCategory> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProductCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated productCategorys by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProductCategoryCriteria criteria) throws Exception {
        List<ProductCategory> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ProductCategoryDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets productCategory data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProductCategoryCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProductCategoryDto> findDtos(List<ProductCategory> list){
        List<ProductCategoryDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProductCategoryDto> getDtoResponseEntity(ProductCategoryDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ProductCategoryRestAdmin(ProductCategoryAdminService service, ProductCategoryConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ProductCategoryAdminService service;
    private final ProductCategoryConverter converter;





}
