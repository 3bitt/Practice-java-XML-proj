package pl.edu.wit.jpa.repository.firmaA.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wit.jpa.dao.companyA.model.CaOrders;

import javax.persistence.EntityManager;

@Component
public class OrdersImpl{

    @Autowired
    private OrdersRepository ordersRepo;
    @Autowired
    public final EntityManager em;

    public OrdersImpl(EntityManager em, OrderReporistory orderRepo){
        this.em = em;
    }

//    public void saveOrders(CaOrders orders){
//        ordersRepo.
//    }

}
