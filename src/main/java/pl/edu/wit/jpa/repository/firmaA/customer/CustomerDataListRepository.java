package pl.edu.wit.jpa.repository.firmaA.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerDataList;

import java.util.Optional;

@Repository
public interface CustomerDataListRepository extends CrudRepository<CaCustomerDataList, Long> {

    CaCustomerDataList findByid(Long id);



}