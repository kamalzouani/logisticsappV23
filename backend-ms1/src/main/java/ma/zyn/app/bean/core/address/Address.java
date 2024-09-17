package ma.zyn.app.bean.core.address;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="address_seq",sequenceName="address_seq",allocationSize=1, initialValue = 1)
public class Address  extends BaseEntity     {




    @Column(length = 500)
    private String street;

    @Column(length = 500)
    private String number;

    @Column(length = 500)
    private String city;

    @Column(length = 500)
    private String postalCode;

    @Column(length = 500)
    private String country;



    public Address(){
        super();
    }

    public Address(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="address_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id != null && id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

