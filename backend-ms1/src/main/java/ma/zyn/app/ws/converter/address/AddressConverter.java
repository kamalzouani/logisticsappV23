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
import ma.zyn.app.bean.core.address.Address;
import ma.zyn.app.ws.dto.address.AddressDto;

@Component
public class AddressConverter {



    public Address toItem(AddressDto dto) {
        if (dto == null) {
            return null;
        } else {
        Address item = new Address();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getStreet()))
                item.setStreet(dto.getStreet());
            if(StringUtil.isNotEmpty(dto.getNumber()))
                item.setNumber(dto.getNumber());
            if(StringUtil.isNotEmpty(dto.getCity()))
                item.setCity(dto.getCity());
            if(StringUtil.isNotEmpty(dto.getPostalCode()))
                item.setPostalCode(dto.getPostalCode());
            if(StringUtil.isNotEmpty(dto.getCountry()))
                item.setCountry(dto.getCountry());



        return item;
        }
    }


    public AddressDto toDto(Address item) {
        if (item == null) {
            return null;
        } else {
            AddressDto dto = new AddressDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getStreet()))
                dto.setStreet(item.getStreet());
            if(StringUtil.isNotEmpty(item.getNumber()))
                dto.setNumber(item.getNumber());
            if(StringUtil.isNotEmpty(item.getCity()))
                dto.setCity(item.getCity());
            if(StringUtil.isNotEmpty(item.getPostalCode()))
                dto.setPostalCode(item.getPostalCode());
            if(StringUtil.isNotEmpty(item.getCountry()))
                dto.setCountry(item.getCountry());


        return dto;
        }
    }


	
    public List<Address> toItem(List<AddressDto> dtos) {
        List<Address> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AddressDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AddressDto> toDto(List<Address> items) {
        List<AddressDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Address item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AddressDto dto, Address t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Address> copy(List<AddressDto> dtos) {
        List<Address> result = new ArrayList<>();
        if (dtos != null) {
            for (AddressDto dto : dtos) {
                Address instance = new Address();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
