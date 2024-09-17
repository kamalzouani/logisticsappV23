package ma.zyn.app.bean.core.address;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "country")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="country_seq",sequenceName="country_seq",allocationSize=1, initialValue = 1)
public class Country  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String code;



    public Country(){
        super();
    }

    public Country(Long id){
        this.id = id;
    }

    public Country(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public Country(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="country_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id != null && id.equals(country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

