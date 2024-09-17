package  ma.zyn.app.ws.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.customer.CustomerConverter;
import ma.zyn.app.bean.core.customer.Customer;
import ma.zyn.app.ws.converter.order.OrderItemConverter;
import ma.zyn.app.bean.core.order.OrderItem;
import ma.zyn.app.ws.converter.product.ProductConverter;
import ma.zyn.app.bean.core.product.Product;
import ma.zyn.app.ws.converter.order.OrderStatusConverter;
import ma.zyn.app.bean.core.order.OrderStatus;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.order.Order;
import ma.zyn.app.ws.dto.order.OrderDto;

@Component
public class OrderConverter {

    @Autowired
    private CustomerConverter customerConverter ;
    @Autowired
    private OrderItemConverter orderItemConverter ;
    @Autowired
    private ProductConverter productConverter ;
    @Autowired
    private OrderStatusConverter orderStatusConverter ;
    private boolean orderStatus;
    private boolean customer;
    private boolean orderItems;

    public  OrderConverter() {
        init(true);
    }

    public Order toItem(OrderDto dto) {
        if (dto == null) {
            return null;
        } else {
        Order item = new Order();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getOrderDate()))
                item.setOrderDate(DateUtil.stringEnToDate(dto.getOrderDate()));
            if(this.orderStatus && dto.getOrderStatus()!=null)
                item.setOrderStatus(orderStatusConverter.toItem(dto.getOrderStatus())) ;

            if(this.customer && dto.getCustomer()!=null)
                item.setCustomer(customerConverter.toItem(dto.getCustomer())) ;


            if(this.orderItems && ListUtil.isNotEmpty(dto.getOrderItems()))
                item.setOrderItems(orderItemConverter.toItem(dto.getOrderItems()));


        return item;
        }
    }


    public OrderDto toDto(Order item) {
        if (item == null) {
            return null;
        } else {
            OrderDto dto = new OrderDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getOrderDate()!=null)
                dto.setOrderDate(DateUtil.dateTimeToString(item.getOrderDate()));
            if(this.orderStatus && item.getOrderStatus()!=null) {
                dto.setOrderStatus(orderStatusConverter.toDto(item.getOrderStatus())) ;

            }
            if(this.customer && item.getCustomer()!=null) {
                dto.setCustomer(customerConverter.toDto(item.getCustomer())) ;

            }
        if(this.orderItems && ListUtil.isNotEmpty(item.getOrderItems())){
            orderItemConverter.init(true);
            orderItemConverter.setOrder(false);
            dto.setOrderItems(orderItemConverter.toDto(item.getOrderItems()));
            orderItemConverter.setOrder(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.orderItems = value;
    }
    public void initObject(boolean value) {
        this.orderStatus = value;
        this.customer = value;
    }
	
    public List<Order> toItem(List<OrderDto> dtos) {
        List<Order> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (OrderDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<OrderDto> toDto(List<Order> items) {
        List<OrderDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Order item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(OrderDto dto, Order t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getOrderStatus() == null  && dto.getOrderStatus() != null){
            t.setOrderStatus(new OrderStatus());
        }else if (t.getOrderStatus() != null  && dto.getOrderStatus() != null){
            t.setOrderStatus(null);
            t.setOrderStatus(new OrderStatus());
        }
        if(t.getCustomer() == null  && dto.getCustomer() != null){
            t.setCustomer(new Customer());
        }else if (t.getCustomer() != null  && dto.getCustomer() != null){
            t.setCustomer(null);
            t.setCustomer(new Customer());
        }
        if (dto.getOrderStatus() != null)
        orderStatusConverter.copy(dto.getOrderStatus(), t.getOrderStatus());
        if (dto.getCustomer() != null)
        customerConverter.copy(dto.getCustomer(), t.getCustomer());
        if (dto.getOrderItems() != null)
            t.setOrderItems(orderItemConverter.copy(dto.getOrderItems()));
    }

    public List<Order> copy(List<OrderDto> dtos) {
        List<Order> result = new ArrayList<>();
        if (dtos != null) {
            for (OrderDto dto : dtos) {
                Order instance = new Order();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CustomerConverter getCustomerConverter(){
        return this.customerConverter;
    }
    public void setCustomerConverter(CustomerConverter customerConverter ){
        this.customerConverter = customerConverter;
    }
    public OrderItemConverter getOrderItemConverter(){
        return this.orderItemConverter;
    }
    public void setOrderItemConverter(OrderItemConverter orderItemConverter ){
        this.orderItemConverter = orderItemConverter;
    }
    public ProductConverter getProductConverter(){
        return this.productConverter;
    }
    public void setProductConverter(ProductConverter productConverter ){
        this.productConverter = productConverter;
    }
    public OrderStatusConverter getOrderStatusConverter(){
        return this.orderStatusConverter;
    }
    public void setOrderStatusConverter(OrderStatusConverter orderStatusConverter ){
        this.orderStatusConverter = orderStatusConverter;
    }
    public boolean  isOrderStatus(){
        return this.orderStatus;
    }
    public void  setOrderStatus(boolean orderStatus){
        this.orderStatus = orderStatus;
    }
    public boolean  isCustomer(){
        return this.customer;
    }
    public void  setCustomer(boolean customer){
        this.customer = customer;
    }
    public boolean  isOrderItems(){
        return this.orderItems ;
    }
    public void  setOrderItems(boolean orderItems ){
        this.orderItems  = orderItems ;
    }
}
