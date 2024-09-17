package  ma.zyn.app.ws.converter.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.product.ProductCategoryConverter;
import ma.zyn.app.bean.core.product.ProductCategory;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.product.Product;
import ma.zyn.app.ws.dto.product.ProductDto;

@Component
public class ProductConverter {

    @Autowired
    private ProductCategoryConverter productCategoryConverter ;
    private boolean productCategory;

    public  ProductConverter() {
        initObject(true);
    }

    public Product toItem(ProductDto dto) {
        if (dto == null) {
            return null;
        } else {
        Product item = new Product();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getPrice()))
                item.setPrice(dto.getPrice());
            if(StringUtil.isNotEmpty(dto.getStock()))
                item.setStock(dto.getStock());
            if(this.productCategory && dto.getProductCategory()!=null)
                item.setProductCategory(productCategoryConverter.toItem(dto.getProductCategory())) ;




        return item;
        }
    }


    public ProductDto toDto(Product item) {
        if (item == null) {
            return null;
        } else {
            ProductDto dto = new ProductDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getPrice()))
                dto.setPrice(item.getPrice());
            if(StringUtil.isNotEmpty(item.getStock()))
                dto.setStock(item.getStock());
            if(this.productCategory && item.getProductCategory()!=null) {
                dto.setProductCategory(productCategoryConverter.toDto(item.getProductCategory())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.productCategory = value;
    }
	
    public List<Product> toItem(List<ProductDto> dtos) {
        List<Product> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProductDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProductDto> toDto(List<Product> items) {
        List<ProductDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Product item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProductDto dto, Product t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProductCategory() == null  && dto.getProductCategory() != null){
            t.setProductCategory(new ProductCategory());
        }else if (t.getProductCategory() != null  && dto.getProductCategory() != null){
            t.setProductCategory(null);
            t.setProductCategory(new ProductCategory());
        }
        if (dto.getProductCategory() != null)
        productCategoryConverter.copy(dto.getProductCategory(), t.getProductCategory());
    }

    public List<Product> copy(List<ProductDto> dtos) {
        List<Product> result = new ArrayList<>();
        if (dtos != null) {
            for (ProductDto dto : dtos) {
                Product instance = new Product();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProductCategoryConverter getProductCategoryConverter(){
        return this.productCategoryConverter;
    }
    public void setProductCategoryConverter(ProductCategoryConverter productCategoryConverter ){
        this.productCategoryConverter = productCategoryConverter;
    }
    public boolean  isProductCategory(){
        return this.productCategory;
    }
    public void  setProductCategory(boolean productCategory){
        this.productCategory = productCategory;
    }
}
