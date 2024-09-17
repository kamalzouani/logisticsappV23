package  ma.zyn.app.dao.specification.core.product;

import ma.zyn.app.dao.criteria.core.product.ProductCategoryCriteria;
import ma.zyn.app.bean.core.product.ProductCategory;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProductCategorySpecification extends  AbstractSpecification<ProductCategoryCriteria, ProductCategory>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
    }

    public ProductCategorySpecification(ProductCategoryCriteria criteria) {
        super(criteria);
    }

    public ProductCategorySpecification(ProductCategoryCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
