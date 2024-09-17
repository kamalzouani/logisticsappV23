package  ma.zyn.app.ws.converter.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.product.ProductCategory;
import ma.zyn.app.ws.dto.product.ProductCategoryDto;

@Component
public class ProductCategoryConverter {



    public ProductCategory toItem(ProductCategoryDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProductCategory item = new ProductCategory();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());



        return item;
        }
    }


    public ProductCategoryDto toDto(ProductCategory item) {
        if (item == null) {
            return null;
        } else {
            ProductCategoryDto dto = new ProductCategoryDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());


        return dto;
        }
    }


	
    public List<ProductCategory> toItem(List<ProductCategoryDto> dtos) {
        List<ProductCategory> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProductCategoryDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProductCategoryDto> toDto(List<ProductCategory> items) {
        List<ProductCategoryDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProductCategory item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProductCategoryDto dto, ProductCategory t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProductCategory> copy(List<ProductCategoryDto> dtos) {
        List<ProductCategory> result = new ArrayList<>();
        if (dtos != null) {
            for (ProductCategoryDto dto : dtos) {
                ProductCategory instance = new ProductCategory();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
