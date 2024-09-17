package ma.zyn.app.service.impl.admin.address;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.address.City;
import ma.zyn.app.dao.criteria.core.address.CityCriteria;
import ma.zyn.app.dao.facade.core.address.CityDao;
import ma.zyn.app.dao.specification.core.address.CitySpecification;
import ma.zyn.app.service.facade.admin.address.CityAdminService;
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
public class CityAdminServiceImpl implements CityAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public City update(City t) {
        City loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{City.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public City findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public City findOrSave(City t) {
        if (t != null) {
            City result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<City> findAll() {
        return dao.findAll();
    }

    public List<City> findByCriteria(CityCriteria criteria) {
        List<City> content = null;
        if (criteria != null) {
            CitySpecification mySpecification = constructSpecification(criteria);
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


    private CitySpecification constructSpecification(CityCriteria criteria) {
        CitySpecification mySpecification =  (CitySpecification) RefelexivityUtil.constructObjectUsingOneParam(CitySpecification.class, criteria);
        return mySpecification;
    }

    public List<City> findPaginatedByCriteria(CityCriteria criteria, int page, int pageSize, String order, String sortField) {
        CitySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CityCriteria criteria) {
        CitySpecification mySpecification = constructSpecification(criteria);
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
    public List<City> delete(List<City> list) {
		List<City> result = new ArrayList();
        if (list != null) {
            for (City t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public City create(City t) {
        City loaded = findByReferenceEntity(t);
        City saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public City findWithAssociatedLists(Long id){
        City result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<City> update(List<City> ts, boolean createIfNotExist) {
        List<City> result = new ArrayList<>();
        if (ts != null) {
            for (City t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    City loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, City t, City loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public City findByReferenceEntity(City t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<City> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<City>> getToBeSavedAndToBeDeleted(List<City> oldList, List<City> newList) {
        List<List<City>> result = new ArrayList<>();
        List<City> resultDelete = new ArrayList<>();
        List<City> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<City> oldList, List<City> newList, List<City> resultUpdateOrSave, List<City> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                City myOld = oldList.get(i);
                City t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                City myNew = newList.get(i);
                City t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public CityAdminServiceImpl(CityDao dao) {
        this.dao = dao;
    }

    private CityDao dao;
}
