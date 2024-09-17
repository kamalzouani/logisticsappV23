package  ma.zyn.app.dao.specification.core.delivery;

import ma.zyn.app.dao.criteria.core.delivery.DeliveryStatusCriteria;
import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class DeliveryStatusSpecification extends  AbstractSpecification<DeliveryStatusCriteria, DeliveryStatus>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public DeliveryStatusSpecification(DeliveryStatusCriteria criteria) {
        super(criteria);
    }

    public DeliveryStatusSpecification(DeliveryStatusCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
