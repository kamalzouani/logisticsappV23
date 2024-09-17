package ma.zyn.app.dao.facade.core.address;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.address.Address;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AddressDao extends AbstractRepository<Address,Long>  {



}
