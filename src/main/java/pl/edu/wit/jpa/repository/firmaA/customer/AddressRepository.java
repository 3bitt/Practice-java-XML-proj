package pl.edu.wit.jpa.repository.firmaA.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wit.jpa.dao.companyA.model.CaAddress;

@Repository
public interface AddressRepository extends JpaRepository<CaAddress, Long> {
}
