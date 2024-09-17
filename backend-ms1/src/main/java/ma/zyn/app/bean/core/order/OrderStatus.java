package ma.zyn.app.bean.core.order;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_status")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="order_status_seq",sequenceName="order_status_seq",allocationSize=1, initialValue = 1)
public class OrderStatus  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    @Column(length = 500)
    private String style;



    public OrderStatus(){
        super();
    }

    public OrderStatus(Long id){
        this.id = id;
    }

    public OrderStatus(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public OrderStatus(String label){
        this.label = label ;
    }
    public OrderStatus(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="order_status_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus orderStatus = (OrderStatus) o;
        return id != null && id.equals(orderStatus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

