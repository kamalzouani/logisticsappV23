package ma.zyn.app.service.impl.customer.delivery;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.delivery.Delivery;
import ma.zyn.app.dao.criteria.core.delivery.DeliveryCriteria;
import ma.zyn.app.dao.facade.core.delivery.DeliveryDao;
import ma.zyn.app.dao.specification.core.delivery.DeliverySpecification;
import ma.zyn.app.service.facade.customer.delivery.DeliveryCustomerService;
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

import ma.zyn.app.service.facade.customer.address.AddressCustomerService ;
import ma.zyn.app.bean.core.address.Address ;
import ma.zyn.app.service.facade.customer.order.OrderCustomerService ;
import ma.zyn.app.bean.core.order.Order ;
import ma.zyn.app.service.facade.customer.delivery.DeliveryStatusCustomerService ;
import ma.zyn.app.bean.core.delivery.DeliveryStatus ;

import java.util.List;
@Service
public class DeliveryCustomerServiceImpl implements DeliveryCustomerService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Delivery update(Delivery t) {
        Delivery loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Delivery.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Delivery findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Delivery findOrSave(Delivery t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Delivery result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Delivery> findAll() {
        return dao.findAll();
    }

    public List<Delivery> findByCriteria(DeliveryCriteria criteria) {
        List<Delivery> content = null;
        if (criteria != null) {
            DeliverySpecification mySpecification = constructSpecification(criteria);
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


    private DeliverySpecification constructSpecification(DeliveryCriteria criteria) {
        DeliverySpecification mySpecification =  (DeliverySpecification) RefelexivityUtil.constructObjectUsingOneParam(DeliverySpecification.class, criteria);
        return mySpecification;
    }

    public List<Delivery> findPaginatedByCriteria(DeliveryCriteria criteria, int page, int pageSize, String order, String sortField) {
        DeliverySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DeliveryCriteria criteria) {
        DeliverySpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Delivery> findByAddressId(Long id){
        return dao.findByAddressId(id);
    }
    public int deleteByAddressId(Long id){
        return dao.deleteByAddressId(id);
    }
    public long countByAddressId(Long id){
        return dao.countByAddressId(id);
    }
    public List<Delivery> findByDeliveryStatusCode(String code){
        return dao.findByDeliveryStatusCode(code);
    }
    public List<Delivery> findByDeliveryStatusId(Long id){
        return dao.findByDeliveryStatusId(id);
    }
    public int deleteByDeliveryStatusCode(String code){
        return dao.deleteByDeliveryStatusCode(code);
    }
    public int deleteByDeliveryStatusId(Long id){
        return dao.deleteByDeliveryStatusId(id);
    }
    public long countByDeliveryStatusCode(String code){
        return dao.countByDeliveryStatusCode(code);
    }
    public List<Delivery> findByOrderId(Long id){
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
    public List<Delivery> delete(List<Delivery> list) {
		List<Delivery> result = new ArrayList();
        if (list != null) {
            for (Delivery t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Delivery create(Delivery t) {
        Delivery loaded = findByReferenceEntity(t);
        Delivery saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Delivery findWithAssociatedLists(Long id){
        Delivery result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Delivery> update(List<Delivery> ts, boolean createIfNotExist) {
        List<Delivery> result = new ArrayList<>();
        if (ts != null) {
            for (Delivery t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Delivery loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Delivery t, Delivery loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Delivery findByReferenceEntity(Delivery t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Delivery t){
        if( t != null) {
            t.setOrder(orderService.findOrSave(t.getOrder()));
        }
    }



    public List<Delivery> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Delivery>> getToBeSavedAndToBeDeleted(List<Delivery> oldList, List<Delivery> newList) {
        List<List<Delivery>> result = new ArrayList<>();
        List<Delivery> resultDelete = new ArrayList<>();
        List<Delivery> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Delivery> oldList, List<Delivery> newList, List<Delivery> resultUpdateOrSave, List<Delivery> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Delivery myOld = oldList.get(i);
                Delivery t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Delivery myNew = newList.get(i);
                Delivery t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private AddressCustomerService addressService ;
    @Autowired
    private OrderCustomerService orderService ;
    @Autowired
    private DeliveryStatusCustomerService deliveryStatusService ;

    public DeliveryCustomerServiceImpl(DeliveryDao dao) {
        this.dao = dao;
    }

    private DeliveryDao dao;
}
