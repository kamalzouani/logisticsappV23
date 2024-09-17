package ma.zyn.app.bean.core.delivery;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "delivery_status")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="delivery_status_seq",sequenceName="delivery_status_seq",allocationSize=1, initialValue = 1)
public class DeliveryStatus  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    @Column(length = 500)
    private String style;



    public DeliveryStatus(){
        super();
    }

    public DeliveryStatus(Long id){
        this.id = id;
    }

    public DeliveryStatus(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public DeliveryStatus(String label){
        this.label = label ;
    }
    public DeliveryStatus(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="delivery_status_seq")
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
        DeliveryStatus deliveryStatus = (DeliveryStatus) o;
        return id != null && id.equals(deliveryStatus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

