package ma.zyn.app.service.facade.customer.delivery;

import java.util.List;
import ma.zyn.app.bean.core.delivery.Delivery;
import ma.zyn.app.dao.criteria.core.delivery.DeliveryCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface DeliveryCustomerService {



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




	Delivery create(Delivery t);

    Delivery update(Delivery t);

    List<Delivery> update(List<Delivery> ts,boolean createIfNotExist);

    Delivery findById(Long id);

    Delivery findOrSave(Delivery t);

    Delivery findByReferenceEntity(Delivery t);

    Delivery findWithAssociatedLists(Long id);

    List<Delivery> findAllOptimized();

    List<Delivery> findAll();

    List<Delivery> findByCriteria(DeliveryCriteria criteria);

    List<Delivery> findPaginatedByCriteria(DeliveryCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DeliveryCriteria criteria);

    List<Delivery> delete(List<Delivery> ts);

    boolean deleteById(Long id);

    List<List<Delivery>> getToBeSavedAndToBeDeleted(List<Delivery> oldList, List<Delivery> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
