package ma.zyn.app.dao.facade.core.order;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.order.OrderItem;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface OrderItemDao extends AbstractRepository<OrderItem,Long>  {

    List<OrderItem> findByProductId(Long id);
    int deleteByProductId(Long id);
    long countByProductId(Long id);
    List<OrderItem> findByOrderId(Long id);
    int deleteByOrderId(Long id);
    long countByOrderId(Long id);


}
