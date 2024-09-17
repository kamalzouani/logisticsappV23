package  ma.zyn.app.dao.specification.core.order;

import ma.zyn.app.dao.criteria.core.order.OrderStatusCriteria;
import ma.zyn.app.bean.core.order.OrderStatus;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class OrderStatusSpecification extends  AbstractSpecification<OrderStatusCriteria, OrderStatus>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public OrderStatusSpecification(OrderStatusCriteria criteria) {
        super(criteria);
    }

    public OrderStatusSpecification(OrderStatusCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
