package  ma.zyn.app.ws.dto.order;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.customer.CustomerDto;
import ma.zyn.app.ws.dto.product.ProductDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto  extends AuditBaseDto {

    private String orderDate ;

    private OrderStatusDto orderStatus ;
    private CustomerDto customer ;

    private List<OrderItemDto> orderItems ;


    public OrderDto(){
        super();
    }




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getOrderDate(){
        return this.orderDate;
    }
    public void setOrderDate(String orderDate){
        this.orderDate = orderDate;
    }


    public OrderStatusDto getOrderStatus(){
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatusDto orderStatus){
        this.orderStatus = orderStatus;
    }
    public CustomerDto getCustomer(){
        return this.customer;
    }

    public void setCustomer(CustomerDto customer){
        this.customer = customer;
    }



    public List<OrderItemDto> getOrderItems(){
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems){
        this.orderItems = orderItems;
    }



}
