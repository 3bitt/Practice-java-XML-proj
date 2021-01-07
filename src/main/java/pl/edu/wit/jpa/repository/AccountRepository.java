package pl.edu.wit.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wit.jpa.dao.companyA.model.CaAccount;

@Repository
public interface AccountRepository extends CrudRepository<CaAccount, Long> {

    CaAccount findByid(Long id);

}
