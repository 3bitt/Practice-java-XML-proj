package pl.edu.wit.jpa.repository.firmaA.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerDataList;

import java.util.List;

@Repository
public interface CustomerDataListRepository extends JpaRepository<CaCustomerDataList, Long> {

    public CaCustomerDataList findByid(Long id);

    public List<CaCustomerDataList> findAll();




}