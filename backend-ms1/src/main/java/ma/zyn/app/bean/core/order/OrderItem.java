package ma.zyn.app.bean.core.order;






import ma.zyn.app.bean.core.product.Product;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_item")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="order_item_seq",sequenceName="order_item_seq",allocationSize=1, initialValue = 1)
public class OrderItem  extends BaseEntity     {




    private Integer quantity = 0;

    private Product product ;
    private Order order ;


    public OrderItem(){
        super();
    }

    public OrderItem(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="order_item_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public Integer getQuantity(){
        return this.quantity;
    }
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    public Product getProduct(){
        return this.product;
    }
    public void setProduct(Product product){
        this.product = product;
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
        OrderItem orderItem = (OrderItem) o;
        return id != null && id.equals(orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

