package  ma.zyn.app.ws.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.order.OrderConverter;
import ma.zyn.app.bean.core.order.Order;
import ma.zyn.app.ws.converter.product.ProductConverter;
import ma.zyn.app.bean.core.product.Product;

import ma.zyn.app.bean.core.order.Order;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.order.OrderItem;
import ma.zyn.app.ws.dto.order.OrderItemDto;

@Component
public class OrderItemConverter {

    @Autowired
    private OrderConverter orderConverter ;
    @Autowired
    private ProductConverter productConverter ;
    private boolean product;
    private boolean order;

    public  OrderItemConverter() {
        initObject(true);
    }

    public OrderItem toItem(OrderItemDto dto) {
        if (dto == null) {
            return null;
        } else {
        OrderItem item = new OrderItem();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getQuantity()))
                item.setQuantity(dto.getQuantity());
            if(this.product && dto.getProduct()!=null)
                item.setProduct(productConverter.toItem(dto.getProduct())) ;

            if(dto.getOrder() != null && dto.getOrder().getId() != null){
                item.setOrder(new Order());
                item.getOrder().setId(dto.getOrder().getId());
                item.getOrder().setId(dto.getOrder().getId());
            }




        return item;
        }
    }


    public OrderItemDto toDto(OrderItem item) {
        if (item == null) {
            return null;
        } else {
            OrderItemDto dto = new OrderItemDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getQuantity()))
                dto.setQuantity(item.getQuantity());
            if(this.product && item.getProduct()!=null) {
                dto.setProduct(productConverter.toDto(item.getProduct())) ;

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
        this.product = value;
        this.order = value;
    }
	
    public List<OrderItem> toItem(List<OrderItemDto> dtos) {
        List<OrderItem> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (OrderItemDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<OrderItemDto> toDto(List<OrderItem> items) {
        List<OrderItemDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (OrderItem item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(OrderItemDto dto, OrderItem t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduct() == null  && dto.getProduct() != null){
            t.setProduct(new Product());
        }else if (t.getProduct() != null  && dto.getProduct() != null){
            t.setProduct(null);
            t.setProduct(new Product());
        }
        if(t.getOrder() == null  && dto.getOrder() != null){
            t.setOrder(new Order());
        }else if (t.getOrder() != null  && dto.getOrder() != null){
            t.setOrder(null);
            t.setOrder(new Order());
        }
        if (dto.getProduct() != null)
        productConverter.copy(dto.getProduct(), t.getProduct());
        if (dto.getOrder() != null)
        orderConverter.copy(dto.getOrder(), t.getOrder());
    }

    public List<OrderItem> copy(List<OrderItemDto> dtos) {
        List<OrderItem> result = new ArrayList<>();
        if (dtos != null) {
            for (OrderItemDto dto : dtos) {
                OrderItem instance = new OrderItem();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public OrderConverter getOrderConverter(){
        return this.orderConverter;
    }
    public void setOrderConverter(OrderConverter orderConverter ){
        this.orderConverter = orderConverter;
    }
    public ProductConverter getProductConverter(){
        return this.productConverter;
    }
    public void setProductConverter(ProductConverter productConverter ){
        this.productConverter = productConverter;
    }
    public boolean  isProduct(){
        return this.product;
    }
    public void  setProduct(boolean product){
        this.product = product;
    }
    public boolean  isOrder(){
        return this.order;
    }
    public void  setOrder(boolean order){
        this.order = order;
    }
}
