package ma.zyn.app.service.impl.admin.order;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.order.OrderItem;
import ma.zyn.app.dao.criteria.core.order.OrderItemCriteria;
import ma.zyn.app.dao.facade.core.order.OrderItemDao;
import ma.zyn.app.dao.specification.core.order.OrderItemSpecification;
import ma.zyn.app.service.facade.admin.order.OrderItemAdminService;
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

import ma.zyn.app.service.facade.admin.order.OrderAdminService ;
import ma.zyn.app.bean.core.order.Order ;
import ma.zyn.app.service.facade.admin.product.ProductAdminService ;
import ma.zyn.app.bean.core.product.Product ;

import java.util.List;
@Service
public class OrderItemAdminServiceImpl implements OrderItemAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public OrderItem update(OrderItem t) {
        OrderItem loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{OrderItem.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public OrderItem findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public OrderItem findOrSave(OrderItem t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            OrderItem result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<OrderItem> findAll() {
        return dao.findAll();
    }

    public List<OrderItem> findByCriteria(OrderItemCriteria criteria) {
        List<OrderItem> content = null;
        if (criteria != null) {
            OrderItemSpecification mySpecification = constructSpecification(criteria);
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


    private OrderItemSpecification constructSpecification(OrderItemCriteria criteria) {
        OrderItemSpecification mySpecification =  (OrderItemSpecification) RefelexivityUtil.constructObjectUsingOneParam(OrderItemSpecification.class, criteria);
        return mySpecification;
    }

    public List<OrderItem> findPaginatedByCriteria(OrderItemCriteria criteria, int page, int pageSize, String order, String sortField) {
        OrderItemSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(OrderItemCriteria criteria) {
        OrderItemSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<OrderItem> findByProductId(Long id){
        return dao.findByProductId(id);
    }
    public int deleteByProductId(Long id){
        return dao.deleteByProductId(id);
    }
    public long countByProductId(Long id){
        return dao.countByProductId(id);
    }
    public List<OrderItem> findByOrderId(Long id){
        return dao.findByOrderId(id);
    }
    public int deleteByOrderId(Long id){
        return dao.deleteByOrderId(id);
    }
    public long countByOrderId(Long id){
        return dao.countByOrderId(id);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<OrderItem> delete(List<OrderItem> list) {
		List<OrderItem> result = new ArrayList();
        if (list != null) {
            for (OrderItem t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public OrderItem create(OrderItem t) {
        OrderItem loaded = findByReferenceEntity(t);
        OrderItem saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public OrderItem findWithAssociatedLists(Long id){
        OrderItem result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<OrderItem> update(List<OrderItem> ts, boolean createIfNotExist) {
        List<OrderItem> result = new ArrayList<>();
        if (ts != null) {
            for (OrderItem t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    OrderItem loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, OrderItem t, OrderItem loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public OrderItem findByReferenceEntity(OrderItem t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(OrderItem t){
        if( t != null) {
            t.setProduct(productService.findOrSave(t.getProduct()));
            t.setOrder(orderService.findOrSave(t.getOrder()));
        }
    }



    public List<OrderItem> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<OrderItem>> getToBeSavedAndToBeDeleted(List<OrderItem> oldList, List<OrderItem> newList) {
        List<List<OrderItem>> result = new ArrayList<>();
        List<OrderItem> resultDelete = new ArrayList<>();
        List<OrderItem> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<OrderItem> oldList, List<OrderItem> newList, List<OrderItem> resultUpdateOrSave, List<OrderItem> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                OrderItem myOld = oldList.get(i);
                OrderItem t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                OrderItem myNew = newList.get(i);
                OrderItem t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private OrderAdminService orderService ;
    @Autowired
    private ProductAdminService productService ;

    public OrderItemAdminServiceImpl(OrderItemDao dao) {
        this.dao = dao;
    }

    private OrderItemDao dao;
}
