package ma.zyn.app.service.facade.admin.order;

import java.util.List;
import ma.zyn.app.bean.core.order.Order;
import ma.zyn.app.dao.criteria.core.order.OrderCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface OrderAdminService {



    List<Order> findByOrderStatusCode(String code);
    List<Order> findByOrderStatusId(Long id);
    int deleteByOrderStatusId(Long id);
    int deleteByOrderStatusCode(String code);
    long countByOrderStatusCode(String code);
    List<Order> findByCustomerId(Long id);
    int deleteByCustomerId(Long id);
    long countByCustomerEmail(String email);




	Order create(Order t);

    Order update(Order t);

    List<Order> update(List<Order> ts,boolean createIfNotExist);

    Order findById(Long id);

    Order findOrSave(Order t);

    Order findByReferenceEntity(Order t);

    Order findWithAssociatedLists(Long id);

    List<Order> findAllOptimized();

    List<Order> findAll();

    List<Order> findByCriteria(OrderCriteria criteria);

    List<Order> findPaginatedByCriteria(OrderCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(OrderCriteria criteria);

    List<Order> delete(List<Order> ts);

    boolean deleteById(Long id);

    List<List<Order>> getToBeSavedAndToBeDeleted(List<Order> oldList, List<Order> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
