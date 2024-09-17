package ma.zyn.app.bean.core.product;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_category")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="product_category_seq",sequenceName="product_category_seq",allocationSize=1, initialValue = 1)
public class ProductCategory  extends BaseEntity     {




    @Column(length = 500)
    private String name;



    public ProductCategory(){
        super();
    }

    public ProductCategory(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="product_category_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory productCategory = (ProductCategory) o;
        return id != null && id.equals(productCategory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

