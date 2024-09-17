package  ma.zyn.app.dao.criteria.core.address;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class AddressCriteria extends  BaseCriteria  {

    private String street;
    private String streetLike;
    private String number;
    private String numberLike;
    private String city;
    private String cityLike;
    private String postalCode;
    private String postalCodeLike;
    private String country;
    private String countryLike;



    public String getStreet(){
        return this.street;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public String getStreetLike(){
        return this.streetLike;
    }
    public void setStreetLike(String streetLike){
        this.streetLike = streetLike;
    }

    public String getNumber(){
        return this.number;
    }
    public void setNumber(String number){
        this.number = number;
    }
    public String getNumberLike(){
        return this.numberLike;
    }
    public void setNumberLike(String numberLike){
        this.numberLike = numberLike;
    }

    public String getCity(){
        return this.city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCityLike(){
        return this.cityLike;
    }
    public void setCityLike(String cityLike){
        this.cityLike = cityLike;
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

    public String getCountry(){
        return this.country;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountryLike(){
        return this.countryLike;
    }
    public void setCountryLike(String countryLike){
        this.countryLike = countryLike;
    }


}
