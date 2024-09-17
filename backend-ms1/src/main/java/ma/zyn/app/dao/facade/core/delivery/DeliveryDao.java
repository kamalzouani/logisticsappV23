package ma.zyn.app.dao.facade.core.delivery;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.delivery.Delivery;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DeliveryDao extends AbstractRepository<Delivery,Long>  {

    List<Delivery> findByAddressId(Long id);
    int deleteByAddressId(Long id);
    long countByAddressId(Long id);
    List<Delivery> findByDeliveryStatusCode(String code);
    List<Delivery> findByDeliveryStatusId(Long id);
    int deleteByDeliveryStatusId(Long id);
    int deleteByDeliveryStatusCode(String code);
    long countByDeliveryStatusCode(String code);
    List<Delivery> findByOrderId(Long id);
    int deleteByOrderId(Long id);
    long countByOrderId(Long id);


}
