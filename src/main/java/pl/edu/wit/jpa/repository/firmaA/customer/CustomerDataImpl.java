package pl.edu.wit.jpa.repository.firmaA.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wit.jpa.dao.companyA.model.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDataImpl {

    @Autowired
    private CustomerDataListRepository customerListRepo;
    @Autowired
    private CustomerDataRepository customerRepo;
    @Autowired
    private AccountRepository accountRepo;
//    private EntityManager em;

    public CustomerDataImpl(){
    }
    @Transactional
    public void saveAll(List<CaCustomerData> customerDataList){
        customerRepo.saveAll(customerDataList);
    }
    public CaCustomerData findCustomerById(Long id){
        return customerRepo.findByid(id);
    }
    @Transactional
    public CaCustomerData findCustomerByOrder(CaOrder order){
        return customerRepo.findByid(order.getSender().getId());
    }
//    public List<CaCustomerData> getAllCustomers(){
//        List<CaCustomerData> customers = new ArrayList<>();
//        customerRepo.findAll().forEach(customers::add);
//        return customers;
//    }
//    public void addCustomer(CaCustomerData customer){
//        customerRepo.save(customer);
//    }
//    public void addCustomerAccount(CaCustomerData customer){
//        List<CaAccount> account = customer.getAccount();
//        for (CaAccount acc : account){
//            accountRepo.save(acc);
//        }
//    }
//    public void updateCustomer(CaCustomerData customer){
//        customerRepo.save(customer);
//    }
//
//    public void deleteCustomer(Long id){
//        customerRepo.deleteById(id);
//    }



}
