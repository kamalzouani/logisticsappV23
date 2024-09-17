package  ma.zyn.app.ws.dto.product;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto  extends AuditBaseDto {

    private String name  ;
    private String description  ;
    private BigDecimal price  ;
    private Integer stock  = 0 ;

    private ProductCategoryDto productCategory ;



    public ProductDto(){
        super();
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


    public ProductCategoryDto getProductCategory(){
        return this.productCategory;
    }

    public void setProductCategory(ProductCategoryDto productCategory){
        this.productCategory = productCategory;
    }






}
