package pl.edu.wit.jpa.repository.firmaA.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.wit.jpa.dao.companyA.model.CaOrder;
import pl.edu.wit.jpa.dao.companyA.model.CaOrderCustomerData;
import pl.edu.wit.jpa.dao.companyA.model.CaOrders;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
public class OrderMasterImpl {

    @Autowired
    private OrdersRepository ordersRepo;
    @Autowired
    private OrderReporistory orderRepo;
    @Autowired
    private OrderCustomerRepository orderCustomerRepo;
    @Autowired
    public final EntityManager em;

    public OrderMasterImpl(EntityManager em){
        this.em = em;
    }

    public void saveParentOrder(CaOrders parentOrder){
        CaOrders order = new CaOrders();
        order.setSynchronizeNo(parentOrder.getSynchronizeNo());
        order.setDateItem(parentOrder.getDateItem());
        ordersRepo.saveAndFlush(order);
    }
    @Transactional
    public void save(CaOrders orders){
        CaOrders order = em.merge(orders);
        ordersRepo.save(order);
    }

    public void saveChildOrder(CaOrder order){
        CaOrderCustomerData orderCustomer = new CaOrderCustomerData();
//        System.out.println("ID::::: " + order.getSender().getId());
        orderRepo.saveAndFlush(order);
    }
}
