package  ma.zyn.app.dao.specification.core.customer;

import ma.zyn.app.dao.criteria.core.customer.CustomerCriteria;
import ma.zyn.app.bean.core.customer.Customer;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CustomerSpecification extends  AbstractSpecification<CustomerCriteria, Customer>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("phoneNumber", criteria.getPhoneNumber(),criteria.getPhoneNumberLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateFk("address","id", criteria.getAddress()==null?null:criteria.getAddress().getId());
        addPredicateFk("address","id", criteria.getAddresss());
    }

    public CustomerSpecification(CustomerCriteria criteria) {
        super(criteria);
    }

    public CustomerSpecification(CustomerCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
