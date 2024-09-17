package  ma.zyn.app.ws.converter.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import ma.zyn.app.ws.dto.delivery.DeliveryStatusDto;

@Component
public class DeliveryStatusConverter {



    public DeliveryStatus toItem(DeliveryStatusDto dto) {
        if (dto == null) {
            return null;
        } else {
        DeliveryStatus item = new DeliveryStatus();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());



        return item;
        }
    }


    public DeliveryStatusDto toDto(DeliveryStatus item) {
        if (item == null) {
            return null;
        } else {
            DeliveryStatusDto dto = new DeliveryStatusDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());


        return dto;
        }
    }


	
    public List<DeliveryStatus> toItem(List<DeliveryStatusDto> dtos) {
        List<DeliveryStatus> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DeliveryStatusDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DeliveryStatusDto> toDto(List<DeliveryStatus> items) {
        List<DeliveryStatusDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DeliveryStatus item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DeliveryStatusDto dto, DeliveryStatus t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DeliveryStatus> copy(List<DeliveryStatusDto> dtos) {
        List<DeliveryStatus> result = new ArrayList<>();
        if (dtos != null) {
            for (DeliveryStatusDto dto : dtos) {
                DeliveryStatus instance = new DeliveryStatus();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
