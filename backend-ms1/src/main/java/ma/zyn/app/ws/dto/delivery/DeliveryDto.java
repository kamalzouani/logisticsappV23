package  ma.zyn.app.ws.dto.delivery;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.address.AddressDto;
import ma.zyn.app.ws.dto.order.OrderDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryDto  extends AuditBaseDto {

    private String deliveryDate ;

    private AddressDto address ;
    private DeliveryStatusDto deliveryStatus ;
    private OrderDto order ;



    public DeliveryDto(){
        super();
    }




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDeliveryDate(){
        return this.deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate){
        this.deliveryDate = deliveryDate;
    }


    public AddressDto getAddress(){
        return this.address;
    }

    public void setAddress(AddressDto address){
        this.address = address;
    }
    public DeliveryStatusDto getDeliveryStatus(){
        return this.deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatusDto deliveryStatus){
        this.deliveryStatus = deliveryStatus;
    }
    public OrderDto getOrder(){
        return this.order;
    }

    public void setOrder(OrderDto order){
        this.order = order;
    }






}
