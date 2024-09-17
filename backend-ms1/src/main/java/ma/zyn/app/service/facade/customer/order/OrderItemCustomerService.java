package ma.zyn.app.service.facade.customer.order;

import java.util.List;
import ma.zyn.app.bean.core.order.OrderItem;
import ma.zyn.app.dao.criteria.core.order.OrderItemCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface OrderItemCustomerService {



    List<OrderItem> findByProductId(Long id);
    int deleteByProductId(Long id);
    long countByProductId(Long id);
    List<OrderItem> findByOrderId(Long id);
    int deleteByOrderId(Long id);
    long countByOrderId(Long id);




	OrderItem create(OrderItem t);

    OrderItem update(OrderItem t);

    List<OrderItem> update(List<OrderItem> ts,boolean createIfNotExist);

    OrderItem findById(Long id);

    OrderItem findOrSave(OrderItem t);

    OrderItem findByReferenceEntity(OrderItem t);

    OrderItem findWithAssociatedLists(Long id);

    List<OrderItem> findAllOptimized();

    List<OrderItem> findAll();

    List<OrderItem> findByCriteria(OrderItemCriteria criteria);

    List<OrderItem> findPaginatedByCriteria(OrderItemCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(OrderItemCriteria criteria);

    List<OrderItem> delete(List<OrderItem> ts);

    boolean deleteById(Long id);

    List<List<OrderItem>> getToBeSavedAndToBeDeleted(List<OrderItem> oldList, List<OrderItem> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
