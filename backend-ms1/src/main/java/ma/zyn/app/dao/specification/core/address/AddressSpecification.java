package  ma.zyn.app.dao.specification.core.address;

import ma.zyn.app.dao.criteria.core.address.AddressCriteria;
import ma.zyn.app.bean.core.address.Address;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class AddressSpecification extends  AbstractSpecification<AddressCriteria, Address>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("street", criteria.getStreet(),criteria.getStreetLike());
        addPredicate("number", criteria.getNumber(),criteria.getNumberLike());
        addPredicate("city", criteria.getCity(),criteria.getCityLike());
        addPredicate("postalCode", criteria.getPostalCode(),criteria.getPostalCodeLike());
        addPredicate("country", criteria.getCountry(),criteria.getCountryLike());
    }

    public AddressSpecification(AddressCriteria criteria) {
        super(criteria);
    }

    public AddressSpecification(AddressCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
