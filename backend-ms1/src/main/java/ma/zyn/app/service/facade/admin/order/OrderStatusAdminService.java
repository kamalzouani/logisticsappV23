package ma.zyn.app.service.facade.admin.order;

import java.util.List;
import ma.zyn.app.bean.core.order.OrderStatus;
import ma.zyn.app.dao.criteria.core.order.OrderStatusCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface OrderStatusAdminService {







	OrderStatus create(OrderStatus t);

    OrderStatus update(OrderStatus t);

    List<OrderStatus> update(List<OrderStatus> ts,boolean createIfNotExist);

    OrderStatus findById(Long id);

    OrderStatus findOrSave(OrderStatus t);

    OrderStatus findByReferenceEntity(OrderStatus t);

    OrderStatus findWithAssociatedLists(Long id);

    List<OrderStatus> findAllOptimized();

    List<OrderStatus> findAll();

    List<OrderStatus> findByCriteria(OrderStatusCriteria criteria);

    List<OrderStatus> findPaginatedByCriteria(OrderStatusCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(OrderStatusCriteria criteria);

    List<OrderStatus> delete(List<OrderStatus> ts);

    boolean deleteById(Long id);

    List<List<OrderStatus>> getToBeSavedAndToBeDeleted(List<OrderStatus> oldList, List<OrderStatus> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
