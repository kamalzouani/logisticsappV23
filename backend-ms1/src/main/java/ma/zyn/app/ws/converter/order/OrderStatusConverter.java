package  ma.zyn.app.ws.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.order.OrderStatus;
import ma.zyn.app.ws.dto.order.OrderStatusDto;

@Component
public class OrderStatusConverter {



    public OrderStatus toItem(OrderStatusDto dto) {
        if (dto == null) {
            return null;
        } else {
        OrderStatus item = new OrderStatus();
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


    public OrderStatusDto toDto(OrderStatus item) {
        if (item == null) {
            return null;
        } else {
            OrderStatusDto dto = new OrderStatusDto();
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


	
    public List<OrderStatus> toItem(List<OrderStatusDto> dtos) {
        List<OrderStatus> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (OrderStatusDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<OrderStatusDto> toDto(List<OrderStatus> items) {
        List<OrderStatusDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (OrderStatus item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(OrderStatusDto dto, OrderStatus t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<OrderStatus> copy(List<OrderStatusDto> dtos) {
        List<OrderStatus> result = new ArrayList<>();
        if (dtos != null) {
            for (OrderStatusDto dto : dtos) {
                OrderStatus instance = new OrderStatus();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
