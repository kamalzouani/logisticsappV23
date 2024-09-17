package  ma.zyn.app.ws.dto.address;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto  extends AuditBaseDto {

    private String street  ;
    private String number  ;
    private String city  ;
    private String postalCode  ;
    private String country  ;




    public AddressDto(){
        super();
    }




    public String getStreet(){
        return this.street;
    }
    public void setStreet(String street){
        this.street = street;
    }


    public String getNumber(){
        return this.number;
    }
    public void setNumber(String number){
        this.number = number;
    }


    public String getCity(){
        return this.city;
    }
    public void setCity(String city){
        this.city = city;
    }


    public String getPostalCode(){
        return this.postalCode;
    }
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }


    public String getCountry(){
        return this.country;
    }
    public void setCountry(String country){
        this.country = country;
    }








}
