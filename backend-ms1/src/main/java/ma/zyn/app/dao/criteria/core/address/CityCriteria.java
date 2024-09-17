package  ma.zyn.app.dao.criteria.core.address;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class CityCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String postalCode;
    private String postalCodeLike;



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

    public String getPostalCode(){
        return this.postalCode;
    }
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public String getPostalCodeLike(){
        return this.postalCodeLike;
    }
    public void setPostalCodeLike(String postalCodeLike){
        this.postalCodeLike = postalCodeLike;
    }


}
