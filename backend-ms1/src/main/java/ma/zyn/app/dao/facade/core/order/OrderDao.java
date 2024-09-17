package ma.zyn.app.dao.facade.core.order;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.order.Order;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface OrderDao extends AbstractRepository<Order,Long>  {

    List<Order> findByOrderStatusCode(String code);
    List<Order> findByOrderStatusId(Long id);
    int deleteByOrderStatusId(Long id);
    int deleteByOrderStatusCode(String code);
    long countByOrderStatusCode(String code);
    List<Order> findByCustomerId(Long id);
    int deleteByCustomerId(Long id);
    long countByCustomerEmail(String email);


}
