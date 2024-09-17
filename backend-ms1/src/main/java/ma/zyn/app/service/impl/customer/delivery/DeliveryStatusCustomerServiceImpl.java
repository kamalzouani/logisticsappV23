package ma.zyn.app.service.impl.customer.delivery;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import ma.zyn.app.dao.criteria.core.delivery.DeliveryStatusCriteria;
import ma.zyn.app.dao.facade.core.delivery.DeliveryStatusDao;
import ma.zyn.app.dao.specification.core.delivery.DeliveryStatusSpecification;
import ma.zyn.app.service.facade.customer.delivery.DeliveryStatusCustomerService;
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
public class DeliveryStatusCustomerServiceImpl implements DeliveryStatusCustomerService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DeliveryStatus update(DeliveryStatus t) {
        DeliveryStatus loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DeliveryStatus.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DeliveryStatus findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DeliveryStatus findOrSave(DeliveryStatus t) {
        if (t != null) {
            DeliveryStatus result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<DeliveryStatus> findAll() {
        return dao.findAll();
    }

    public List<DeliveryStatus> findByCriteria(DeliveryStatusCriteria criteria) {
        List<DeliveryStatus> content = null;
        if (criteria != null) {
            DeliveryStatusSpecification mySpecification = constructSpecification(criteria);
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


    private DeliveryStatusSpecification constructSpecification(DeliveryStatusCriteria criteria) {
        DeliveryStatusSpecification mySpecification =  (DeliveryStatusSpecification) RefelexivityUtil.constructObjectUsingOneParam(DeliveryStatusSpecification.class, criteria);
        return mySpecification;
    }

    public List<DeliveryStatus> findPaginatedByCriteria(DeliveryStatusCriteria criteria, int page, int pageSize, String order, String sortField) {
        DeliveryStatusSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DeliveryStatusCriteria criteria) {
        DeliveryStatusSpecification mySpecification = constructSpecification(criteria);
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
    public List<DeliveryStatus> delete(List<DeliveryStatus> list) {
		List<DeliveryStatus> result = new ArrayList();
        if (list != null) {
            for (DeliveryStatus t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DeliveryStatus create(DeliveryStatus t) {
        DeliveryStatus loaded = findByReferenceEntity(t);
        DeliveryStatus saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public DeliveryStatus findWithAssociatedLists(Long id){
        DeliveryStatus result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DeliveryStatus> update(List<DeliveryStatus> ts, boolean createIfNotExist) {
        List<DeliveryStatus> result = new ArrayList<>();
        if (ts != null) {
            for (DeliveryStatus t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DeliveryStatus loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, DeliveryStatus t, DeliveryStatus loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public DeliveryStatus findByReferenceEntity(DeliveryStatus t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<DeliveryStatus> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DeliveryStatus>> getToBeSavedAndToBeDeleted(List<DeliveryStatus> oldList, List<DeliveryStatus> newList) {
        List<List<DeliveryStatus>> result = new ArrayList<>();
        List<DeliveryStatus> resultDelete = new ArrayList<>();
        List<DeliveryStatus> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<DeliveryStatus> oldList, List<DeliveryStatus> newList, List<DeliveryStatus> resultUpdateOrSave, List<DeliveryStatus> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                DeliveryStatus myOld = oldList.get(i);
                DeliveryStatus t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                DeliveryStatus myNew = newList.get(i);
                DeliveryStatus t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public DeliveryStatusCustomerServiceImpl(DeliveryStatusDao dao) {
        this.dao = dao;
    }

    private DeliveryStatusDao dao;
}
