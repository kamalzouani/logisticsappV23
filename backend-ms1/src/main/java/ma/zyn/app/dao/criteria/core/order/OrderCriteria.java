package  ma.zyn.app.dao.criteria.core.order;


import ma.zyn.app.dao.criteria.core.customer.CustomerCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class OrderCriteria extends  BaseCriteria  {

    private LocalDateTime orderDate;
    private LocalDateTime orderDateFrom;
    private LocalDateTime orderDateTo;

    private OrderStatusCriteria orderStatus ;
    private List<OrderStatusCriteria> orderStatuss ;
    private CustomerCriteria customer ;
    private List<CustomerCriteria> customers ;


    public LocalDateTime getOrderDate(){
        return this.orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate){
        this.orderDate = orderDate;
    }
    public LocalDateTime getOrderDateFrom(){
        return this.orderDateFrom;
    }
    public void setOrderDateFrom(LocalDateTime orderDateFrom){
        this.orderDateFrom = orderDateFrom;
    }
    public LocalDateTime getOrderDateTo(){
        return this.orderDateTo;
    }
    public void setOrderDateTo(LocalDateTime orderDateTo){
        this.orderDateTo = orderDateTo;
    }

    public OrderStatusCriteria getOrderStatus(){
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatusCriteria orderStatus){
        this.orderStatus = orderStatus;
    }
    public List<OrderStatusCriteria> getOrderStatuss(){
        return this.orderStatuss;
    }

    public void setOrderStatuss(List<OrderStatusCriteria> orderStatuss){
        this.orderStatuss = orderStatuss;
    }
    public CustomerCriteria getCustomer(){
        return this.customer;
    }

    public void setCustomer(CustomerCriteria customer){
        this.customer = customer;
    }
    public List<CustomerCriteria> getCustomers(){
        return this.customers;
    }

    public void setCustomers(List<CustomerCriteria> customers){
        this.customers = customers;
    }
}
