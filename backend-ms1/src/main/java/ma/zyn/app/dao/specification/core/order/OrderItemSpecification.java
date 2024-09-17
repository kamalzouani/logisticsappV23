package  ma.zyn.app.dao.specification.core.order;

import ma.zyn.app.dao.criteria.core.order.OrderItemCriteria;
import ma.zyn.app.bean.core.order.OrderItem;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class OrderItemSpecification extends  AbstractSpecification<OrderItemCriteria, OrderItem>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateInt("quantity", criteria.getQuantity(), criteria.getQuantityMin(), criteria.getQuantityMax());
        addPredicateFk("product","id", criteria.getProduct()==null?null:criteria.getProduct().getId());
        addPredicateFk("product","id", criteria.getProducts());
        addPredicateFk("order","id", criteria.getOrder()==null?null:criteria.getOrder().getId());
        addPredicateFk("order","id", criteria.getOrders());
    }

    public OrderItemSpecification(OrderItemCriteria criteria) {
        super(criteria);
    }

    public OrderItemSpecification(OrderItemCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
