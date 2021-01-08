package pl.edu.wit.jpa.repository.firmaA.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wit.jpa.dao.companyA.model.CaOrderCustomerData;

import javax.persistence.EntityManager;

@Repository
public interface OrderCustomerRepository extends JpaRepository<CaOrderCustomerData, Long> {


}
