package  ma.zyn.app.ws.dto.customer;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zyn.app.zynerator.security.bean.Role;
import java.util.Collection;
import ma.zyn.app.zynerator.security.ws.dto.UserDto;


import ma.zyn.app.ws.dto.address.AddressDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto  extends UserDto {

    private String firstName  ;
    private String lastName  ;
    private String phoneNumber  ;

    private AddressDto address ;



    private Collection<Role> roles;
    public CustomerDto(){
        super();
    }




    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }


    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }










    public AddressDto getAddress(){
        return this.address;
    }

    public void setAddress(AddressDto address){
        this.address = address;
    }







    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
