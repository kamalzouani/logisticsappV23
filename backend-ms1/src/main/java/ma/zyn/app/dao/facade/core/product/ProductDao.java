package ma.zyn.app.dao.facade.core.product;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.product.Product;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductDao extends AbstractRepository<Product,Long>  {

    List<Product> findByProductCategoryId(Long id);
    int deleteByProductCategoryId(Long id);
    long countByProductCategoryId(Long id);


}
