package ma.zyn.app.dao.facade.core.product;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.product.ProductCategory;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductCategoryDao extends AbstractRepository<ProductCategory,Long>  {



}
