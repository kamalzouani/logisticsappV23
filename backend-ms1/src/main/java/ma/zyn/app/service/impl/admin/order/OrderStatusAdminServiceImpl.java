package ma.zyn.app.service.impl.admin.order;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.order.OrderStatus;
import ma.zyn.app.dao.criteria.core.order.OrderStatusCriteria;
import ma.zyn.app.dao.facade.core.order.OrderStatusDao;
import ma.zyn.app.dao.specification.core.order.OrderStatusSpecification;
import ma.zyn.app.service.facade.admin.order.OrderStatusAdminService;
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


import java.util.List;
@Service
public class OrderStatusAdminServiceImpl implements OrderStatusAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public OrderStatus update(OrderStatus t) {
        OrderStatus loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{OrderStatus.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public OrderStatus findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public OrderStatus findOrSave(OrderStatus t) {
        if (t != null) {
            OrderStatus result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<OrderStatus> findAll() {
        return dao.findAll();
    }

    public List<OrderStatus> findByCriteria(OrderStatusCriteria criteria) {
        List<OrderStatus> content = null;
        if (criteria != null) {
            OrderStatusSpecification mySpecification = constructSpecification(criteria);
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


    private OrderStatusSpecification constructSpecification(OrderStatusCriteria criteria) {
        OrderStatusSpecification mySpecification =  (OrderStatusSpecification) RefelexivityUtil.constructObjectUsingOneParam(OrderStatusSpecification.class, criteria);
        return mySpecification;
    }

    public List<OrderStatus> findPaginatedByCriteria(OrderStatusCriteria criteria, int page, int pageSize, String order, String sortField) {
        OrderStatusSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(OrderStatusCriteria criteria) {
        OrderStatusSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public List<OrderStatus> delete(List<OrderStatus> list) {
		List<OrderStatus> result = new ArrayList();
        if (list != null) {
            for (OrderStatus t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public OrderStatus create(OrderStatus t) {
        OrderStatus loaded = findByReferenceEntity(t);
        OrderStatus saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public OrderStatus findWithAssociatedLists(Long id){
        OrderStatus result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<OrderStatus> update(List<OrderStatus> ts, boolean createIfNotExist) {
        List<OrderStatus> result = new ArrayList<>();
        if (ts != null) {
            for (OrderStatus t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    OrderStatus loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, OrderStatus t, OrderStatus loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public OrderStatus findByReferenceEntity(OrderStatus t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<OrderStatus> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<OrderStatus>> getToBeSavedAndToBeDeleted(List<OrderStatus> oldList, List<OrderStatus> newList) {
        List<List<OrderStatus>> result = new ArrayList<>();
        List<OrderStatus> resultDelete = new ArrayList<>();
        List<OrderStatus> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<OrderStatus> oldList, List<OrderStatus> newList, List<OrderStatus> resultUpdateOrSave, List<OrderStatus> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                OrderStatus myOld = oldList.get(i);
                OrderStatus t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                OrderStatus myNew = newList.get(i);
                OrderStatus t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public OrderStatusAdminServiceImpl(OrderStatusDao dao) {
        this.dao = dao;
    }

    private OrderStatusDao dao;
}
