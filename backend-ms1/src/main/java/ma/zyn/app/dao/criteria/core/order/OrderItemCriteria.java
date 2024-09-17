package  ma.zyn.app.dao.criteria.core.order;


import ma.zyn.app.dao.criteria.core.product.ProductCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class OrderItemCriteria extends  BaseCriteria  {

    private String quantity;
    private String quantityMin;
    private String quantityMax;

    private ProductCriteria product ;
    private List<ProductCriteria> products ;
    private OrderCriteria order ;
    private List<OrderCriteria> orders ;


    public String getQuantity(){
        return this.quantity;
    }
    public void setQuantity(String quantity){
        this.quantity = quantity;
    }   
    public String getQuantityMin(){
        return this.quantityMin;
    }
    public void setQuantityMin(String quantityMin){
        this.quantityMin = quantityMin;
    }
    public String getQuantityMax(){
        return this.quantityMax;
    }
    public void setQuantityMax(String quantityMax){
        this.quantityMax = quantityMax;
    }
      

    public ProductCriteria getProduct(){
        return this.product;
    }

    public void setProduct(ProductCriteria product){
        this.product = product;
    }
    public List<ProductCriteria> getProducts(){
        return this.products;
    }

    public void setProducts(List<ProductCriteria> products){
        this.products = products;
    }
    public OrderCriteria getOrder(){
        return this.order;
    }

    public void setOrder(OrderCriteria order){
        this.order = order;
    }
    public List<OrderCriteria> getOrders(){
        return this.orders;
    }

    public void setOrders(List<OrderCriteria> orders){
        this.orders = orders;
    }
}
