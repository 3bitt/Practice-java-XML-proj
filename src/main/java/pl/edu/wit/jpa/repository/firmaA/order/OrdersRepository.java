package pl.edu.wit.jpa.repository.firmaA.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerDataList;
import pl.edu.wit.jpa.dao.companyA.model.CaOrderCustomerData;
import pl.edu.wit.jpa.dao.companyA.model.CaOrders;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<CaOrders, Long> {


    public CaOrders findOrdersById(Long id);
}
