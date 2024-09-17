package  ma.zyn.app.dao.specification.core.order;

import ma.zyn.app.dao.criteria.core.order.OrderCriteria;
import ma.zyn.app.bean.core.order.Order;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class OrderSpecification extends  AbstractSpecification<OrderCriteria, Order>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("orderDate", criteria.getOrderDate(), criteria.getOrderDateFrom(), criteria.getOrderDateTo());
        addPredicateFk("orderStatus","id", criteria.getOrderStatus()==null?null:criteria.getOrderStatus().getId());
        addPredicateFk("orderStatus","id", criteria.getOrderStatuss());
        addPredicateFk("orderStatus","code", criteria.getOrderStatus()==null?null:criteria.getOrderStatus().getCode());
        addPredicateFk("customer","id", criteria.getCustomer()==null?null:criteria.getCustomer().getId());
        addPredicateFk("customer","id", criteria.getCustomers());
        addPredicateFk("customer","email", criteria.getCustomer()==null?null:criteria.getCustomer().getEmail());
    }

    public OrderSpecification(OrderCriteria criteria) {
        super(criteria);
    }

    public OrderSpecification(OrderCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
