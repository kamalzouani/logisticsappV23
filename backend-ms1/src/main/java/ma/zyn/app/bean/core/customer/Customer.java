package ma.zyn.app.bean.core.customer;






import ma.zyn.app.bean.core.address.Address;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zyn.app.zynerator.security.bean.User;

@Entity
@Table(name = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="customer_seq",sequenceName="customer_seq",allocationSize=1, initialValue = 1)
public class Customer  extends User    {


    public Customer(String username) {
        super(username);
    }


    @Column(length = 500)
    private String firstName;

    @Column(length = 500)
    private String lastName;

    @Column(length = 500)
    private String phoneNumber;









    private Address address ;


    public Customer(){
        super();
    }

    public Customer(Long id){
        this.id = id;
    }

    public Customer(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="customer_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    public Address getAddress(){
        return this.address;
    }
    public void setAddress(Address address){
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id != null && id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

