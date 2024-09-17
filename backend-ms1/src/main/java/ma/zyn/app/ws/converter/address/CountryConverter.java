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
import ma.zyn.app.bean.core.address.Country;
import ma.zyn.app.ws.dto.address.CountryDto;

@Component
public class CountryConverter {



    public Country toItem(CountryDto dto) {
        if (dto == null) {
            return null;
        } else {
        Country item = new Country();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());



        return item;
        }
    }


    public CountryDto toDto(Country item) {
        if (item == null) {
            return null;
        } else {
            CountryDto dto = new CountryDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());


        return dto;
        }
    }


	
    public List<Country> toItem(List<CountryDto> dtos) {
        List<Country> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CountryDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CountryDto> toDto(List<Country> items) {
        List<CountryDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Country item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CountryDto dto, Country t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Country> copy(List<CountryDto> dtos) {
        List<Country> result = new ArrayList<>();
        if (dtos != null) {
            for (CountryDto dto : dtos) {
                Country instance = new Country();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
