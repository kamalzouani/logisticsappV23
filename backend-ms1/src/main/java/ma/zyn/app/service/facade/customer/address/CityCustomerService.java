package ma.zyn.app.service.facade.customer.address;

import java.util.List;
import ma.zyn.app.bean.core.address.City;
import ma.zyn.app.dao.criteria.core.address.CityCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CityCustomerService {







	City create(City t);

    City update(City t);

    List<City> update(List<City> ts,boolean createIfNotExist);

    City findById(Long id);

    City findOrSave(City t);

    City findByReferenceEntity(City t);

    City findWithAssociatedLists(Long id);

    List<City> findAllOptimized();

    List<City> findAll();

    List<City> findByCriteria(CityCriteria criteria);

    List<City> findPaginatedByCriteria(CityCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CityCriteria criteria);

    List<City> delete(List<City> ts);

    boolean deleteById(Long id);

    List<List<City>> getToBeSavedAndToBeDeleted(List<City> oldList, List<City> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
