package  ma.zyn.app.ws.dto.order;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zyn.app.ws.dto.product.ProductDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDto  extends AuditBaseDto {

    private Integer quantity  = 0 ;

    private ProductDto product ;
    private OrderDto order ;



    public OrderItemDto(){
        super();
    }




    public Integer getQuantity(){
        return this.quantity;
    }
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }


    public ProductDto getProduct(){
        return this.product;
    }

    public void setProduct(ProductDto product){
        this.product = product;
    }
    public OrderDto getOrder(){
        return this.order;
    }

    public void setOrder(OrderDto order){
        this.order = order;
    }






}
