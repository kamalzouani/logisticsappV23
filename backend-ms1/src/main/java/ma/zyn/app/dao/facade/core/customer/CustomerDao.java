package ma.zyn.app.dao.facade.core.customer;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.customer.Customer;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.customer.Customer;
import java.util.List;


@Repository
public interface CustomerDao extends AbstractRepository<Customer,Long>  {
    Customer findByEmail(String email);
    int deleteByEmail(String email);

    List<Customer> findByAddressId(Long id);
    int deleteByAddressId(Long id);
    long countByAddressId(Long id);
    Customer findByUsername(String username);

    @Query("SELECT NEW Customer(item.id,item.email) FROM Customer item")
    List<Customer> findAllOptimized();

}
