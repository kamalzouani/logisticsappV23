package  ma.zyn.app.dao.criteria.core.product;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ProductCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;
    private String price;
    private String priceMin;
    private String priceMax;
    private String stock;
    private String stockMin;
    private String stockMax;

    private ProductCategoryCriteria productCategory ;
    private List<ProductCategoryCriteria> productCategorys ;


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNameLike(){
        return this.nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public String getPrice(){
        return this.price;
    }
    public void setPrice(String price){
        this.price = price;
    }   
    public String getPriceMin(){
        return this.priceMin;
    }
    public void setPriceMin(String priceMin){
        this.priceMin = priceMin;
    }
    public String getPriceMax(){
        return this.priceMax;
    }
    public void setPriceMax(String priceMax){
        this.priceMax = priceMax;
    }
      
    public String getStock(){
        return this.stock;
    }
    public void setStock(String stock){
        this.stock = stock;
    }   
    public String getStockMin(){
        return this.stockMin;
    }
    public void setStockMin(String stockMin){
        this.stockMin = stockMin;
    }
    public String getStockMax(){
        return this.stockMax;
    }
    public void setStockMax(String stockMax){
        this.stockMax = stockMax;
    }
      

    public ProductCategoryCriteria getProductCategory(){
        return this.productCategory;
    }

    public void setProductCategory(ProductCategoryCriteria productCategory){
        this.productCategory = productCategory;
    }
    public List<ProductCategoryCriteria> getProductCategorys(){
        return this.productCategorys;
    }

    public void setProductCategorys(List<ProductCategoryCriteria> productCategorys){
        this.productCategorys = productCategorys;
    }
}
