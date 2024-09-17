package ma.zyn.app.service.impl.admin.address;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.address.Country;
import ma.zyn.app.dao.criteria.core.address.CountryCriteria;
import ma.zyn.app.dao.facade.core.address.CountryDao;
import ma.zyn.app.dao.specification.core.address.CountrySpecification;
import ma.zyn.app.service.facade.admin.address.CountryAdminService;
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
public class CountryAdminServiceImpl implements CountryAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Country update(Country t) {
        Country loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Country.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Country findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Country findOrSave(Country t) {
        if (t != null) {
            Country result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Country> findAll() {
        return dao.findAll();
    }

    public List<Country> findByCriteria(CountryCriteria criteria) {
        List<Country> content = null;
        if (criteria != null) {
            CountrySpecification mySpecification = constructSpecification(criteria);
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


    private CountrySpecification constructSpecification(CountryCriteria criteria) {
        CountrySpecification mySpecification =  (CountrySpecification) RefelexivityUtil.constructObjectUsingOneParam(CountrySpecification.class, criteria);
        return mySpecification;
    }

    public List<Country> findPaginatedByCriteria(CountryCriteria criteria, int page, int pageSize, String order, String sortField) {
        CountrySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CountryCriteria criteria) {
        CountrySpecification mySpecification = constructSpecification(criteria);
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
    public List<Country> delete(List<Country> list) {
		List<Country> result = new ArrayList();
        if (list != null) {
            for (Country t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Country create(Country t) {
        Country loaded = findByReferenceEntity(t);
        Country saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Country findWithAssociatedLists(Long id){
        Country result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Country> update(List<Country> ts, boolean createIfNotExist) {
        List<Country> result = new ArrayList<>();
        if (ts != null) {
            for (Country t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Country loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Country t, Country loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Country findByReferenceEntity(Country t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Country> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Country>> getToBeSavedAndToBeDeleted(List<Country> oldList, List<Country> newList) {
        List<List<Country>> result = new ArrayList<>();
        List<Country> resultDelete = new ArrayList<>();
        List<Country> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Country> oldList, List<Country> newList, List<Country> resultUpdateOrSave, List<Country> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Country myOld = oldList.get(i);
                Country t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Country myNew = newList.get(i);
                Country t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public CountryAdminServiceImpl(CountryDao dao) {
        this.dao = dao;
    }

    private CountryDao dao;
}
