package  ma.zyn.app.dao.criteria.core.delivery;


import ma.zyn.app.dao.criteria.core.address.AddressCriteria;
import ma.zyn.app.dao.criteria.core.order.OrderCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class DeliveryCriteria extends  BaseCriteria  {

    private LocalDateTime deliveryDate;
    private LocalDateTime deliveryDateFrom;
    private LocalDateTime deliveryDateTo;

    private AddressCriteria address ;
    private List<AddressCriteria> addresss ;
    private DeliveryStatusCriteria deliveryStatus ;
    private List<DeliveryStatusCriteria> deliveryStatuss ;
    private OrderCriteria order ;
    private List<OrderCriteria> orders ;


    public LocalDateTime getDeliveryDate(){
        return this.deliveryDate;
    }
    public void setDeliveryDate(LocalDateTime deliveryDate){
        this.deliveryDate = deliveryDate;
    }
    public LocalDateTime getDeliveryDateFrom(){
        return this.deliveryDateFrom;
    }
    public void setDeliveryDateFrom(LocalDateTime deliveryDateFrom){
        this.deliveryDateFrom = deliveryDateFrom;
    }
    public LocalDateTime getDeliveryDateTo(){
        return this.deliveryDateTo;
    }
    public void setDeliveryDateTo(LocalDateTime deliveryDateTo){
        this.deliveryDateTo = deliveryDateTo;
    }

    public AddressCriteria getAddress(){
        return this.address;
    }

    public void setAddress(AddressCriteria address){
        this.address = address;
    }
    public List<AddressCriteria> getAddresss(){
        return this.addresss;
    }

    public void setAddresss(List<AddressCriteria> addresss){
        this.addresss = addresss;
    }
    public DeliveryStatusCriteria getDeliveryStatus(){
        return this.deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatusCriteria deliveryStatus){
        this.deliveryStatus = deliveryStatus;
    }
    public List<DeliveryStatusCriteria> getDeliveryStatuss(){
        return this.deliveryStatuss;
    }

    public void setDeliveryStatuss(List<DeliveryStatusCriteria> deliveryStatuss){
        this.deliveryStatuss = deliveryStatuss;
    }
    public OrderCriteria getOrder(){
        return this.order;
    }

    public void setOrder(OrderCriteria order){
        this.order = order;
    }
    public List<OrderCriteria> getOrders(){
        return this.orders;
    }

    public void setOrders(List<OrderCriteria> orders){
        this.orders = orders;
    }
}
