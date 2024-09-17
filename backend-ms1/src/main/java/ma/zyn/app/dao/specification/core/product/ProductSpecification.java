package  ma.zyn.app.dao.specification.core.product;

import ma.zyn.app.dao.criteria.core.product.ProductCriteria;
import ma.zyn.app.bean.core.product.Product;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProductSpecification extends  AbstractSpecification<ProductCriteria, Product>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicateBigDecimal("price", criteria.getPrice(), criteria.getPriceMin(), criteria.getPriceMax());
        addPredicateInt("stock", criteria.getStock(), criteria.getStockMin(), criteria.getStockMax());
        addPredicateFk("productCategory","id", criteria.getProductCategory()==null?null:criteria.getProductCategory().getId());
        addPredicateFk("productCategory","id", criteria.getProductCategorys());
    }

    public ProductSpecification(ProductCriteria criteria) {
        super(criteria);
    }

    public ProductSpecification(ProductCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
