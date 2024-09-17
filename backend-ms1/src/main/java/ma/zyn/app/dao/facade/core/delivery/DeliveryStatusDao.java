package ma.zyn.app.dao.facade.core.delivery;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import java.util.List;


@Repository
public interface DeliveryStatusDao extends AbstractRepository<DeliveryStatus,Long>  {
    DeliveryStatus findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DeliveryStatus(item.id,item.label) FROM DeliveryStatus item")
    List<DeliveryStatus> findAllOptimized();

}
