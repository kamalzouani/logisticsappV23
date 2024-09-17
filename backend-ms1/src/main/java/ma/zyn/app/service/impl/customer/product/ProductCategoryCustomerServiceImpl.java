package ma.zyn.app.service.impl.customer.product;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.product.ProductCategory;
import ma.zyn.app.dao.criteria.core.product.ProductCategoryCriteria;
import ma.zyn.app.dao.facade.core.product.ProductCategoryDao;
import ma.zyn.app.dao.specification.core.product.ProductCategorySpecification;
import ma.zyn.app.service.facade.customer.product.ProductCategoryCustomerService;
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
public class ProductCategoryCustomerServiceImpl implements ProductCategoryCustomerService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProductCategory update(ProductCategory t) {
        ProductCategory loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProductCategory.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProductCategory findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProductCategory findOrSave(ProductCategory t) {
        if (t != null) {
            ProductCategory result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProductCategory> findAll() {
        return dao.findAll();
    }

    public List<ProductCategory> findByCriteria(ProductCategoryCriteria criteria) {
        List<ProductCategory> content = null;
        if (criteria != null) {
            ProductCategorySpecification mySpecification = constructSpecification(criteria);
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


    private ProductCategorySpecification constructSpecification(ProductCategoryCriteria criteria) {
        ProductCategorySpecification mySpecification =  (ProductCategorySpecification) RefelexivityUtil.constructObjectUsingOneParam(ProductCategorySpecification.class, criteria);
        return mySpecification;
    }

    public List<ProductCategory> findPaginatedByCriteria(ProductCategoryCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProductCategorySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProductCategoryCriteria criteria) {
        ProductCategorySpecification mySpecification = constructSpecification(criteria);
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
    public List<ProductCategory> delete(List<ProductCategory> list) {
		List<ProductCategory> result = new ArrayList();
        if (list != null) {
            for (ProductCategory t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProductCategory create(ProductCategory t) {
        ProductCategory loaded = findByReferenceEntity(t);
        ProductCategory saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProductCategory findWithAssociatedLists(Long id){
        ProductCategory result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProductCategory> update(List<ProductCategory> ts, boolean createIfNotExist) {
        List<ProductCategory> result = new ArrayList<>();
        if (ts != null) {
            for (ProductCategory t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProductCategory loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProductCategory t, ProductCategory loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProductCategory findByReferenceEntity(ProductCategory t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<ProductCategory> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<ProductCategory>> getToBeSavedAndToBeDeleted(List<ProductCategory> oldList, List<ProductCategory> newList) {
        List<List<ProductCategory>> result = new ArrayList<>();
        List<ProductCategory> resultDelete = new ArrayList<>();
        List<ProductCategory> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProductCategory> oldList, List<ProductCategory> newList, List<ProductCategory> resultUpdateOrSave, List<ProductCategory> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProductCategory myOld = oldList.get(i);
                ProductCategory t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProductCategory myNew = newList.get(i);
                ProductCategory t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ProductCategoryCustomerServiceImpl(ProductCategoryDao dao) {
        this.dao = dao;
    }

    private ProductCategoryDao dao;
}
