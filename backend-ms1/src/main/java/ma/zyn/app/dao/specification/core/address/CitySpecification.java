package  ma.zyn.app.dao.specification.core.address;

import ma.zyn.app.dao.criteria.core.address.CityCriteria;
import ma.zyn.app.bean.core.address.City;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CitySpecification extends  AbstractSpecification<CityCriteria, City>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("postalCode", criteria.getPostalCode(),criteria.getPostalCodeLike());
    }

    public CitySpecification(CityCriteria criteria) {
        super(criteria);
    }

    public CitySpecification(CityCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
