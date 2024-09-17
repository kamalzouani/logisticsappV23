package ma.zyn.app.dao.facade.core.order;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.order.OrderStatus;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.order.OrderStatus;
import java.util.List;


@Repository
public interface OrderStatusDao extends AbstractRepository<OrderStatus,Long>  {
    OrderStatus findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW OrderStatus(item.id,item.label) FROM OrderStatus item")
    List<OrderStatus> findAllOptimized();

}
