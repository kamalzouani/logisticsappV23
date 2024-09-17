package ma.zyn.app.bean.core.order;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.customer.Customer;
import ma.zyn.app.bean.core.product.Product;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="order_seq",sequenceName="order_seq",allocationSize=1, initialValue = 1)
public class Order  extends BaseEntity     {




    private LocalDateTime orderDate ;

    private OrderStatus orderStatus ;
    private Customer customer ;

    private List<OrderItem> orderItems ;

    public Order(){
        super();
    }

    public Order(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="order_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public LocalDateTime getOrderDate(){
        return this.orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate){
        this.orderDate = orderDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status")
    public OrderStatus getOrderStatus(){
        return this.orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    public Customer getCustomer(){
        return this.customer;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    @OneToMany(mappedBy = "order")
    public List<OrderItem> getOrderItems(){
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems){
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id != null && id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

