package ma.zyn.app.service.facade.customer.customer;

import java.util.List;
import ma.zyn.app.bean.core.customer.Customer;
import ma.zyn.app.dao.criteria.core.customer.CustomerCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CustomerCustomerService {


    Customer findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Customer> findByAddressId(Long id);
    int deleteByAddressId(Long id);
    long countByAddressId(Long id);




	Customer create(Customer t);

    Customer update(Customer t);

    List<Customer> update(List<Customer> ts,boolean createIfNotExist);

    Customer findById(Long id);

    Customer findOrSave(Customer t);

    Customer findByReferenceEntity(Customer t);

    Customer findWithAssociatedLists(Long id);

    List<Customer> findAllOptimized();

    List<Customer> findAll();

    List<Customer> findByCriteria(CustomerCriteria criteria);

    List<Customer> findPaginatedByCriteria(CustomerCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CustomerCriteria criteria);

    List<Customer> delete(List<Customer> ts);

    boolean deleteById(Long id);

    List<List<Customer>> getToBeSavedAndToBeDeleted(List<Customer> oldList, List<Customer> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
