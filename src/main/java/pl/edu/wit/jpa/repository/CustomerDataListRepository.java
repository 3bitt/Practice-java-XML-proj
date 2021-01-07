package pl.edu.wit.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerDataList;

@Repository
public interface CustomerDataListRepository extends CrudRepository<CaCustomerDataList, Long> {

    CaCustomerDataList findByhjid(Long id);

}