package ma.zyn.app.bean.core.delivery;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.address.Address;
import ma.zyn.app.bean.core.order.Order;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "delivery")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="delivery_seq",sequenceName="delivery_seq",allocationSize=1, initialValue = 1)
public class Delivery  extends BaseEntity     {




    private LocalDateTime deliveryDate ;

    private Address address ;
    private DeliveryStatus deliveryStatus ;
    private Order order ;


    public Delivery(){
        super();
    }

    public Delivery(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="delivery_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    public Address getAddress(){
        return this.address;
    }
    public void setAddress(Address address){
        this.address = address;
    }
    public LocalDateTime getDeliveryDate(){
        return this.deliveryDate;
    }
    public void setDeliveryDate(LocalDateTime deliveryDate){
        this.deliveryDate = deliveryDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_status")
    public DeliveryStatus getDeliveryStatus(){
        return this.deliveryStatus;
    }
    public void setDeliveryStatus(DeliveryStatus deliveryStatus){
        this.deliveryStatus = deliveryStatus;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order")
    public Order getOrder(){
        return this.order;
    }
    public void setOrder(Order order){
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return id != null && id.equals(delivery.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

