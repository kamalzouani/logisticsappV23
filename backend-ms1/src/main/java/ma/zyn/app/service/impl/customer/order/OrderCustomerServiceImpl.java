package ma.zyn.app.service.impl.customer.order;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.order.Order;
import ma.zyn.app.dao.criteria.core.order.OrderCriteria;
import ma.zyn.app.dao.facade.core.order.OrderDao;
import ma.zyn.app.dao.specification.core.order.OrderSpecification;
import ma.zyn.app.service.facade.customer.order.OrderCustomerService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.customer.customer.CustomerCustomerService ;
import ma.zyn.app.bean.core.customer.Customer ;
import ma.zyn.app.service.facade.customer.order.OrderItemCustomerService ;
import ma.zyn.app.bean.core.order.OrderItem ;
import ma.zyn.app.service.facade.customer.order.OrderStatusCustomerService ;
import ma.zyn.app.bean.core.order.OrderStatus ;

import java.util.List;
@Service
public class OrderCustomerServiceImpl implements OrderCustomerService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Order update(Order t) {
        Order loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Order.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Order findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Order findOrSave(Order t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Order result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Order> findAll() {
        return dao.findAll();
    }

    public List<Order> findByCriteria(OrderCriteria criteria) {
        List<Order> content = null;
        if (criteria != null) {
            OrderSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private OrderSpecification constructSpecification(OrderCriteria criteria) {
        OrderSpecification mySpecification =  (OrderSpecification) RefelexivityUtil.constructObjectUsingOneParam(OrderSpecification.class, criteria);
        return mySpecification;
    }

    public List<Order> findPaginatedByCriteria(OrderCriteria criteria, int page, int pageSize, String order, String sortField) {
        OrderSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(OrderCriteria criteria) {
        OrderSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Order> findByOrderStatusCode(String code){
        return dao.findByOrderStatusCode(code);
    }
    public List<Order> findByOrderStatusId(Long id){
        return dao.findByOrderStatusId(id);
    }
    public int deleteByOrderStatusCode(String code){
        return dao.deleteByOrderStatusCode(code);
    }
    public int deleteByOrderStatusId(Long id){
        return dao.deleteByOrderStatusId(id);
    }
    public long countByOrderStatusCode(String code){
        return dao.countByOrderStatusCode(code);
    }
    public List<Order> findByCustomerId(Long id){
        return dao.findByCustomerId(id);
    }
    public int deleteByCustomerId(Long id){
        return dao.deleteByCustomerId(id);
    }
    public long countByCustomerEmail(String email){
        return dao.countByCustomerEmail(email);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        orderItemService.deleteByOrderId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Order> delete(List<Order> list) {
		List<Order> result = new ArrayList();
        if (list != null) {
            for (Order t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Order create(Order t) {
        Order loaded = findByReferenceEntity(t);
        Order saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getOrderItems() != null) {
                t.getOrderItems().forEach(element-> {
                    element.setOrder(saved);
                    orderItemService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Order findWithAssociatedLists(Long id){
        Order result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setOrderItems(orderItemService.findByOrderId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Order> update(List<Order> ts, boolean createIfNotExist) {
        List<Order> result = new ArrayList<>();
        if (ts != null) {
            for (Order t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Order loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Order t, Order loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Order order){
    if(order !=null && order.getId() != null){
        List<List<OrderItem>> resultOrderItems= orderItemService.getToBeSavedAndToBeDeleted(orderItemService.findByOrderId(order.getId()),order.getOrderItems());
            orderItemService.delete(resultOrderItems.get(1));
        emptyIfNull(resultOrderItems.get(0)).forEach(e -> e.setOrder(order));
        orderItemService.update(resultOrderItems.get(0),true);
        }
    }








    public Order findByReferenceEntity(Order t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Order t){
        if( t != null) {
            t.setCustomer(customerService.findOrSave(t.getCustomer()));
        }
    }



    public List<Order> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Order>> getToBeSavedAndToBeDeleted(List<Order> oldList, List<Order> newList) {
        List<List<Order>> result = new ArrayList<>();
        List<Order> resultDelete = new ArrayList<>();
        List<Order> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<Order> oldList, List<Order> newList, List<Order> resultUpdateOrSave, List<Order> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Order myOld = oldList.get(i);
                Order t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Order myNew = newList.get(i);
                Order t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private CustomerCustomerService customerService ;
    @Autowired
    private OrderItemCustomerService orderItemService ;
    @Autowired
    private OrderStatusCustomerService orderStatusService ;

    public OrderCustomerServiceImpl(OrderDao dao) {
        this.dao = dao;
    }

    private OrderDao dao;
}
