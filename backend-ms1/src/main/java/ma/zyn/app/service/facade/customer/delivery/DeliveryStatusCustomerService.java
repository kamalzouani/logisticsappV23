package ma.zyn.app.service.facade.customer.delivery;

import java.util.List;
import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import ma.zyn.app.dao.criteria.core.delivery.DeliveryStatusCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface DeliveryStatusCustomerService {







	DeliveryStatus create(DeliveryStatus t);

    DeliveryStatus update(DeliveryStatus t);

    List<DeliveryStatus> update(List<DeliveryStatus> ts,boolean createIfNotExist);

    DeliveryStatus findById(Long id);

    DeliveryStatus findOrSave(DeliveryStatus t);

    DeliveryStatus findByReferenceEntity(DeliveryStatus t);

    DeliveryStatus findWithAssociatedLists(Long id);

    List<DeliveryStatus> findAllOptimized();

    List<DeliveryStatus> findAll();

    List<DeliveryStatus> findByCriteria(DeliveryStatusCriteria criteria);

    List<DeliveryStatus> findPaginatedByCriteria(DeliveryStatusCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DeliveryStatusCriteria criteria);

    List<DeliveryStatus> delete(List<DeliveryStatus> ts);

    boolean deleteById(Long id);

    List<List<DeliveryStatus>> getToBeSavedAndToBeDeleted(List<DeliveryStatus> oldList, List<DeliveryStatus> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
