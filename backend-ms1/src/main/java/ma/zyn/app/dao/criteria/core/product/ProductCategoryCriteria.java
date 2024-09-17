package  ma.zyn.app.dao.criteria.core.product;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ProductCategoryCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;



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


}
