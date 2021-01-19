package pl.edu.wit.jpa.repository.firmaA.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.wit.jpa.dao.companyA.model.*;
import pl.edu.wit.jpa.repository.firmaA.customer.AccountRepository;
import pl.edu.wit.jpa.repository.firmaA.customer.CustomerDataRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class OrderMasterImpl {

    @Autowired
    private OrdersRepository ordersRepo;
    @Autowired
    private OrderReporistory orderRepo;
    @Autowired
    private OrderCustomerRepository orderCustomerRepo;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private CustomerDataRepositoryImpl customerRepo;
    @Autowired
    public final EntityManager em;

    public OrderMasterImpl(EntityManager em){
        this.em = em;
    }

    public CaOrders saveParentOrderOnly(CaOrders parentOrder){
        CaOrders order = new CaOrders();
        order.setSynchronizeNo(parentOrder.getSynchronizeNo());
        order.setDateItem(parentOrder.getDateItem());
        return ordersRepo.save(order);
    }

    public void saveWholeOrders(CaOrders orders){
        CaOrders order = em.merge(orders);
        ordersRepo.save(order);
        em.flush();
    }

    public void saveChildOrder(CaOrder order){
        CaOrder ord = em.merge(order);
        orderRepo.save(em.getReference(CaOrder.class, ord.getId()));
    }

    public void processOrders(CaOrders orders){

        for (CaOrder order : orders.getOrder()){
            if (validateOrder(order, order.getAccountNumber())){

                orderCustomerRepo.save(order.getSender());
                orderCustomerRepo.save(order.getRecipient());
                em.flush();

            }
        }

        saveWholeOrders(orders);
    }

    public boolean validateOrder(CaOrder order, String accountNumber){
        CaOrderCustomerData recipient = order.getRecipient();
        CaOrderCustomerData sender = order.getSender();
        CaAccount orderTargetAccount = accountRepo.findByNumber(accountNumber);
        CaCustomerData customer;

        if (orderTargetAccount == null){
            System.err.println("----- Rachunek docelowy nie istnieje w bazie danych -----");
            System.err.println("---> ZAMÓWIENIE NR: " + order.getNumber());
            return false;
        }

        if (recipient.getPesel() != null){
            customer = customerRepo.findCustomerByPesel(recipient.getPesel());
            if (customer == null){
                System.err.println("\n----- Odbiorca nie został odnaleziony -----");
                System.err.println("---> ZAMÓWIENIE NR: " + order.getNumber());
                System.err.println("Name: " + recipient.getName());
                System.err.println("Surname: " + recipient.getSurname());
                System.err.println("Company: " + recipient.getCompanyName());
                System.err.println("PESEL: " + recipient.getPesel());
                return false;
            }
        } else if (recipient.getNip() != null) {
            customer = customerRepo.findCustomerByNip(recipient.getNip());
            if (customer == null){
                System.err.println("\n----- Odbiorca nie został odnaleziony -----");
                System.err.println("---> ZAMÓWIENIE NR: " + order.getNumber());
                System.err.println("Name: " + recipient.getName());
                System.err.println("Surname: " + recipient.getSurname());
                System.err.println("Company: " + recipient.getCompanyName());
                System.err.println("NIP: " + recipient.getNip());
                return false;
            }

        } else {
            System.err.println("\n----- Odbiorca nie posiada Peselu ani NIP'u - nie moge zidentyfikować -----");
            System.err.println("---> ZAMÓWIENIE NR: " + order.getNumber());
            System.err.println("Name: " + recipient.getName());
            System.err.println("Surname: " + recipient.getSurname());
            System.err.println("Company: " + recipient.getCompanyName());
            System.err.println("NIP: " + recipient.getNip());
            return false;
        }

        List<CaAccount> customerAccounts = customer.getAccount();

        for (CaAccount acc : customerAccounts){
            String targetAccount = orderTargetAccount.getNumber();
            String customerAccount = acc.getNumber();
            if (targetAccount.equals(customerAccount)){
                return true;
            }
        }

        System.err.println("\n----- Odbiorca nie posiada rachunku dolecowego -----");
        System.err.println("Rachunek docelowy: " + accountNumber);
        System.err.println("-- Rachunki odbiorcy --");
        customerAccounts.forEach((account) -> System.err.println(account.getNumber()));
        return false;
    }
}
