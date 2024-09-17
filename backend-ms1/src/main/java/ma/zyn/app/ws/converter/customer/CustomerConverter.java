package  ma.zyn.app.ws.converter.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.address.AddressConverter;
import ma.zyn.app.bean.core.address.Address;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.customer.Customer;
import ma.zyn.app.ws.dto.customer.CustomerDto;

@Component
public class CustomerConverter {

    @Autowired
    private AddressConverter addressConverter ;
    private boolean address;

    public  CustomerConverter() {
        initObject(true);
    }

    public Customer toItem(CustomerDto dto) {
        if (dto == null) {
            return null;
        } else {
        Customer item = new Customer();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());
            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());
            if(StringUtil.isNotEmpty(dto.getPhoneNumber()))
                item.setPhoneNumber(dto.getPhoneNumber());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            item.setPasswordChanged(dto.getPasswordChanged());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            item.setEnabled(dto.getEnabled());
            if(this.address && dto.getAddress()!=null)
                item.setAddress(addressConverter.toItem(dto.getAddress())) ;



            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public CustomerDto toDto(Customer item) {
        if (item == null) {
            return null;
        } else {
            CustomerDto dto = new CustomerDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstName()))
                dto.setFirstName(item.getFirstName());
            if(StringUtil.isNotEmpty(item.getLastName()))
                dto.setLastName(item.getLastName());
            if(StringUtil.isNotEmpty(item.getPhoneNumber()))
                dto.setPhoneNumber(item.getPhoneNumber());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(this.address && item.getAddress()!=null) {
                dto.setAddress(addressConverter.toDto(item.getAddress())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.address = value;
    }
	
    public List<Customer> toItem(List<CustomerDto> dtos) {
        List<Customer> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CustomerDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CustomerDto> toDto(List<Customer> items) {
        List<CustomerDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Customer item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CustomerDto dto, Customer t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getAddress() == null  && dto.getAddress() != null){
            t.setAddress(new Address());
        }else if (t.getAddress() != null  && dto.getAddress() != null){
            t.setAddress(null);
            t.setAddress(new Address());
        }
        if (dto.getAddress() != null)
        addressConverter.copy(dto.getAddress(), t.getAddress());
    }

    public List<Customer> copy(List<CustomerDto> dtos) {
        List<Customer> result = new ArrayList<>();
        if (dtos != null) {
            for (CustomerDto dto : dtos) {
                Customer instance = new Customer();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public AddressConverter getAddressConverter(){
        return this.addressConverter;
    }
    public void setAddressConverter(AddressConverter addressConverter ){
        this.addressConverter = addressConverter;
    }
    public boolean  isAddress(){
        return this.address;
    }
    public void  setAddress(boolean address){
        this.address = address;
    }
}
