package ma.zyn.app.service.impl.customer.address;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.address.Address;
import ma.zyn.app.dao.criteria.core.address.AddressCriteria;
import ma.zyn.app.dao.facade.core.address.AddressDao;
import ma.zyn.app.dao.specification.core.address.AddressSpecification;
import ma.zyn.app.service.facade.customer.address.AddressCustomerService;
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
public class AddressCustomerServiceImpl implements AddressCustomerService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Address update(Address t) {
        Address loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Address.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Address findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Address findOrSave(Address t) {
        if (t != null) {
            Address result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Address> findAll() {
        return dao.findAll();
    }

    public List<Address> findByCriteria(AddressCriteria criteria) {
        List<Address> content = null;
        if (criteria != null) {
            AddressSpecification mySpecification = constructSpecification(criteria);
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


    private AddressSpecification constructSpecification(AddressCriteria criteria) {
        AddressSpecification mySpecification =  (AddressSpecification) RefelexivityUtil.constructObjectUsingOneParam(AddressSpecification.class, criteria);
        return mySpecification;
    }

    public List<Address> findPaginatedByCriteria(AddressCriteria criteria, int page, int pageSize, String order, String sortField) {
        AddressSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AddressCriteria criteria) {
        AddressSpecification mySpecification = constructSpecification(criteria);
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
    public List<Address> delete(List<Address> list) {
		List<Address> result = new ArrayList();
        if (list != null) {
            for (Address t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Address create(Address t) {
        Address loaded = findByReferenceEntity(t);
        Address saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Address findWithAssociatedLists(Long id){
        Address result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Address> update(List<Address> ts, boolean createIfNotExist) {
        List<Address> result = new ArrayList<>();
        if (ts != null) {
            for (Address t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Address loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Address t, Address loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Address findByReferenceEntity(Address t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Address> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Address>> getToBeSavedAndToBeDeleted(List<Address> oldList, List<Address> newList) {
        List<List<Address>> result = new ArrayList<>();
        List<Address> resultDelete = new ArrayList<>();
        List<Address> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Address> oldList, List<Address> newList, List<Address> resultUpdateOrSave, List<Address> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Address myOld = oldList.get(i);
                Address t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Address myNew = newList.get(i);
                Address t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public AddressCustomerServiceImpl(AddressDao dao) {
        this.dao = dao;
    }

    private AddressDao dao;
}
