package pl.edu.wit.jpa.repository.firmaA.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wit.jpa.dao.companyA.model.CaOrder;
import pl.edu.wit.jpa.dao.companyA.model.CaOrderCustomerData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class OrderImpl {

    @Autowired
    private OrdersRepository ordersRepo;
    @Autowired
    private OrderReporistory orderRepo;
    @Autowired
    private OrderCustomerRepository orderCustomerRepo;
    @Autowired
    public final EntityManager em;

    public OrderImpl(EntityManager em){
        this.em = em;
    }
    @Transactional
    public void saveOrder(CaOrder order){
        orderRepo.save(order);
    }
    @Transactional
    public void saveOrderssss(CaOrder order){
//        Inicjalizacja recipient i sender poniewaz samo .save(order) nie dziala
//        hibernate/JPA nie ogarnia jak to zrobic (niepoprawnie skonfigurowane encje)

        CaOrderCustomerData customerOrderData = new CaOrderCustomerData();
        customerOrderData.setName(order.getSender().getName());
        customerOrderData.setSurname(order.getSender().getSurname());
        customerOrderData.setPesel(order.getSender().getPesel());
        customerOrderData.setDocumentCountry(order.getSender().getDocumentCountry());
        customerOrderData.setDocumentType(order.getSender().getDocumentType());
        customerOrderData.setDocumentNumber(order.getSender().getDocumentNumber());
        customerOrderData.setCompanyName(order.getSender().getCompanyName());
        customerOrderData.setNip(order.getSender().getNip());
        customerOrderData.setPhone(order.getSender().getPhone());
        customerOrderData.setEmail(order.getSender().getEmail());
        customerOrderData.setAddress(order.getSender().getAddress());
        customerOrderData.setAccount(order.getSender().getAccount());
        customerOrderData.setType(order.getSender().getType());

        orderCustomerRepo.save(customerOrderData);

        customerOrderData = new CaOrderCustomerData();
        customerOrderData.setName(order.getRecipient().getName());
        customerOrderData.setSurname(order.getRecipient().getSurname());
        customerOrderData.setPesel(order.getRecipient().getPesel());
        customerOrderData.setDocumentCountry(order.getRecipient().getDocumentCountry());
        customerOrderData.setDocumentType(order.getRecipient().getDocumentType());
        customerOrderData.setDocumentNumber(order.getRecipient().getDocumentNumber());
        customerOrderData.setCompanyName(order.getRecipient().getCompanyName());
        customerOrderData.setNip(order.getRecipient().getNip());
        customerOrderData.setPhone(order.getRecipient().getPhone());
        customerOrderData.setEmail(order.getRecipient().getEmail());
        customerOrderData.setAddress(order.getRecipient().getAddress());
        customerOrderData.setAccount(order.getRecipient().getAccount());
        customerOrderData.setType(order.getRecipient().getType());
        orderCustomerRepo.save(customerOrderData);

        orderRepo.save(order);
        orderRepo.flush();
    }

//    @Transactional
//    public saveOrder


}
