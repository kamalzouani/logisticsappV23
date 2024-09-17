package  ma.zyn.app.ws.converter.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.address.City;
import ma.zyn.app.ws.dto.address.CityDto;

@Component
public class CityConverter {



    public City toItem(CityDto dto) {
        if (dto == null) {
            return null;
        } else {
        City item = new City();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getPostalCode()))
                item.setPostalCode(dto.getPostalCode());



        return item;
        }
    }


    public CityDto toDto(City item) {
        if (item == null) {
            return null;
        } else {
            CityDto dto = new CityDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getPostalCode()))
                dto.setPostalCode(item.getPostalCode());


        return dto;
        }
    }


	
    public List<City> toItem(List<CityDto> dtos) {
        List<City> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CityDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CityDto> toDto(List<City> items) {
        List<CityDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (City item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CityDto dto, City t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<City> copy(List<CityDto> dtos) {
        List<City> result = new ArrayList<>();
        if (dtos != null) {
            for (CityDto dto : dtos) {
                City instance = new City();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
