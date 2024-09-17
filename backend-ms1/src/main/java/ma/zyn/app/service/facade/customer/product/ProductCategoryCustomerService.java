package ma.zyn.app.service.facade.customer.product;

import java.util.List;
import ma.zyn.app.bean.core.product.ProductCategory;
import ma.zyn.app.dao.criteria.core.product.ProductCategoryCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ProductCategoryCustomerService {







	ProductCategory create(ProductCategory t);

    ProductCategory update(ProductCategory t);

    List<ProductCategory> update(List<ProductCategory> ts,boolean createIfNotExist);

    ProductCategory findById(Long id);

    ProductCategory findOrSave(ProductCategory t);

    ProductCategory findByReferenceEntity(ProductCategory t);

    ProductCategory findWithAssociatedLists(Long id);

    List<ProductCategory> findAllOptimized();

    List<ProductCategory> findAll();

    List<ProductCategory> findByCriteria(ProductCategoryCriteria criteria);

    List<ProductCategory> findPaginatedByCriteria(ProductCategoryCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProductCategoryCriteria criteria);

    List<ProductCategory> delete(List<ProductCategory> ts);

    boolean deleteById(Long id);

    List<List<ProductCategory>> getToBeSavedAndToBeDeleted(List<ProductCategory> oldList, List<ProductCategory> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
