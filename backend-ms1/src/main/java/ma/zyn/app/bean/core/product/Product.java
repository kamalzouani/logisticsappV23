package ma.zyn.app.bean.core.product;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="product_seq",sequenceName="product_seq",allocationSize=1, initialValue = 1)
public class Product  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String description;

    private BigDecimal price = BigDecimal.ZERO;

    private Integer stock = 0;

    private ProductCategory productCategory ;


    public Product(){
        super();
    }

    public Product(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="product_seq")
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
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    public Integer getStock(){
        return this.stock;
    }
    public void setStock(Integer stock){
        this.stock = stock;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category")
    public ProductCategory getProductCategory(){
        return this.productCategory;
    }
    public void setProductCategory(ProductCategory productCategory){
        this.productCategory = productCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id != null && id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

