package pl.edu.wit.jpa.repository.firmaA.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wit.jpa.dao.companyA.model.CaAccount;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<CaAccount, Long> {

    CaAccount findByid(Long id);

    CaAccount findByNumber(String number);
}
