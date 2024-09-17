package  ma.zyn.app.ws.converter.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.address.AddressConverter;
import ma.zyn.app.bean.core.address.Address;
import ma.zyn.app.ws.converter.order.OrderConverter;
import ma.zyn.app.bean.core.order.Order;
import ma.zyn.app.ws.converter.delivery.DeliveryStatusConverter;
import ma.zyn.app.bean.core.delivery.DeliveryStatus;

import ma.zyn.app.bean.core.order.Order;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.delivery.Delivery;
import ma.zyn.app.ws.dto.delivery.DeliveryDto;

@Component
public class DeliveryConverter {

    @Autowired
    private AddressConverter addressConverter ;
    @Autowired
    private OrderConverter orderConverter ;
    @Autowired
    private DeliveryStatusConverter deliveryStatusConverter ;
    private boolean address;
    private boolean deliveryStatus;
    private boolean order;

    public  DeliveryConverter() {
        initObject(true);
    }

    public Delivery toItem(DeliveryDto dto) {
        if (dto == null) {
            return null;
        } else {
        Delivery item = new Delivery();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDeliveryDate()))
                item.setDeliveryDate(DateUtil.stringEnToDate(dto.getDeliveryDate()));
            if(this.address && dto.getAddress()!=null)
                item.setAddress(addressConverter.toItem(dto.getAddress())) ;

            if(this.deliveryStatus && dto.getDeliveryStatus()!=null)
                item.setDeliveryStatus(deliveryStatusConverter.toItem(dto.getDeliveryStatus())) ;

            if(dto.getOrder() != null && dto.getOrder().getId() != null){
                item.setOrder(new Order());
                item.getOrder().setId(dto.getOrder().getId());
                item.getOrder().setId(dto.getOrder().getId());
            }




        return item;
        }
    }


    public DeliveryDto toDto(Delivery item) {
        if (item == null) {
            return null;
        } else {
            DeliveryDto dto = new DeliveryDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getDeliveryDate()!=null)
                dto.setDeliveryDate(DateUtil.dateTimeToString(item.getDeliveryDate()));
            if(this.address && item.getAddress()!=null) {
                dto.setAddress(addressConverter.toDto(item.getAddress())) ;

            }
            if(this.deliveryStatus && item.getDeliveryStatus()!=null) {
                dto.setDeliveryStatus(deliveryStatusConverter.toDto(item.getDeliveryStatus())) ;

            }
            if(this.order && item.getOrder()!=null) {
                dto.setOrder(orderConverter.toDto(item.getOrder())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.address = value;
        this.deliveryStatus = value;
        this.order = value;
    }
	
    public List<Delivery> toItem(List<DeliveryDto> dtos) {
        List<Delivery> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DeliveryDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DeliveryDto> toDto(List<Delivery> items) {
        List<DeliveryDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Delivery item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DeliveryDto dto, Delivery t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getAddress() == null  && dto.getAddress() != null){
            t.setAddress(new Address());
        }else if (t.getAddress() != null  && dto.getAddress() != null){
            t.setAddress(null);
            t.setAddress(new Address());
        }
        if(t.getDeliveryStatus() == null  && dto.getDeliveryStatus() != null){
            t.setDeliveryStatus(new DeliveryStatus());
        }else if (t.getDeliveryStatus() != null  && dto.getDeliveryStatus() != null){
            t.setDeliveryStatus(null);
            t.setDeliveryStatus(new DeliveryStatus());
        }
        if(t.getOrder() == null  && dto.getOrder() != null){
            t.setOrder(new Order());
        }else if (t.getOrder() != null  && dto.getOrder() != null){
            t.setOrder(null);
            t.setOrder(new Order());
        }
        if (dto.getAddress() != null)
        addressConverter.copy(dto.getAddress(), t.getAddress());
        if (dto.getDeliveryStatus() != null)
        deliveryStatusConverter.copy(dto.getDeliveryStatus(), t.getDeliveryStatus());
        if (dto.getOrder() != null)
        orderConverter.copy(dto.getOrder(), t.getOrder());
    }

    public List<Delivery> copy(List<DeliveryDto> dtos) {
        List<Delivery> result = new ArrayList<>();
        if (dtos != null) {
            for (DeliveryDto dto : dtos) {
                Delivery instance = new Delivery();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public AddressConverter getAddressConverter(){
        return this.addressConverter;
    }
    public void setAddressConverter(AddressConverter addressConverter ){
        this.addressConverter = addressConverter;
    }
    public OrderConverter getOrderConverter(){
        return this.orderConverter;
    }
    public void setOrderConverter(OrderConverter orderConverter ){
        this.orderConverter = orderConverter;
    }
    public DeliveryStatusConverter getDeliveryStatusConverter(){
        return this.deliveryStatusConverter;
    }
    public void setDeliveryStatusConverter(DeliveryStatusConverter deliveryStatusConverter ){
        this.deliveryStatusConverter = deliveryStatusConverter;
    }
    public boolean  isAddress(){
        return this.address;
    }
    public void  setAddress(boolean address){
        this.address = address;
    }
    public boolean  isDeliveryStatus(){
        return this.deliveryStatus;
    }
    public void  setDeliveryStatus(boolean deliveryStatus){
        this.deliveryStatus = deliveryStatus;
    }
    public boolean  isOrder(){
        return this.order;
    }
    public void  setOrder(boolean order){
        this.order = order;
    }
}
