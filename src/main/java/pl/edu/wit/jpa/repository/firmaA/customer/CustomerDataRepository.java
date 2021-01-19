package pl.edu.wit.jpa.repository.firmaA.customer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerData;

import java.math.BigInteger;

@Repository
public interface CustomerDataRepository extends JpaRepository<CaCustomerData, Long> {

    CaCustomerData findByid(Long id);
//    CaCustomerData saveCustomer(CaCustomerData customer);

    CaCustomerData findByNipEquals(BigInteger nip);

    CaCustomerData findByPeselEquals(String pesel);

}
