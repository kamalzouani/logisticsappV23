package  ma.zyn.app.dao.specification.core.delivery;

import ma.zyn.app.dao.criteria.core.delivery.DeliveryCriteria;
import ma.zyn.app.bean.core.delivery.Delivery;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class DeliverySpecification extends  AbstractSpecification<DeliveryCriteria, Delivery>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("deliveryDate", criteria.getDeliveryDate(), criteria.getDeliveryDateFrom(), criteria.getDeliveryDateTo());
        addPredicateFk("address","id", criteria.getAddress()==null?null:criteria.getAddress().getId());
        addPredicateFk("address","id", criteria.getAddresss());
        addPredicateFk("deliveryStatus","id", criteria.getDeliveryStatus()==null?null:criteria.getDeliveryStatus().getId());
        addPredicateFk("deliveryStatus","id", criteria.getDeliveryStatuss());
        addPredicateFk("deliveryStatus","code", criteria.getDeliveryStatus()==null?null:criteria.getDeliveryStatus().getCode());
        addPredicateFk("order","id", criteria.getOrder()==null?null:criteria.getOrder().getId());
        addPredicateFk("order","id", criteria.getOrders());
    }

    public DeliverySpecification(DeliveryCriteria criteria) {
        super(criteria);
    }

    public DeliverySpecification(DeliveryCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
