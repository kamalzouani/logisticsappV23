package ma.zyn.app.service.facade.admin.address;

import java.util.List;
import ma.zyn.app.bean.core.address.Address;
import ma.zyn.app.dao.criteria.core.address.AddressCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface AddressAdminService {







	Address create(Address t);

    Address update(Address t);

    List<Address> update(List<Address> ts,boolean createIfNotExist);

    Address findById(Long id);

    Address findOrSave(Address t);

    Address findByReferenceEntity(Address t);

    Address findWithAssociatedLists(Long id);

    List<Address> findAllOptimized();

    List<Address> findAll();

    List<Address> findByCriteria(AddressCriteria criteria);

    List<Address> findPaginatedByCriteria(AddressCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AddressCriteria criteria);

    List<Address> delete(List<Address> ts);

    boolean deleteById(Long id);

    List<List<Address>> getToBeSavedAndToBeDeleted(List<Address> oldList, List<Address> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
