package pl.edu.wit.jpa.repository.firmaA.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wit.jpa.dao.companyA.model.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDataRepositoryImpl {

    @Autowired
    private CustomerDataListRepository customerListRepo;
    @Autowired
    private CustomerDataRepository customerRepo;
    @Autowired
    private AccountRepository accountRepo;
//    private EntityManager em;

    public CustomerDataRepositoryImpl(){
    }
    @Transactional
    public void saveAll(List<CaCustomerData> customerDataList){
        customerRepo.saveAll(customerDataList);
    }
    public CaCustomerData findCustomerById(Long id){
        return customerRepo.findByid(id);
    }

    public void save(CaCustomerData customer){
            CaCustomerData n = new CaCustomerData();
            n.setName(customer.getName());
            n.setSurname(customer.getSurname());
            n.setPesel(customer.getPesel());
            n.setDocumentCountry(customer.getDocumentCountry());
            n.setDocumentType(customer.getDocumentType());
            n.setDocumentNumber(customer.getDocumentNumber());
            n.setCompanyName(customer.getCompanyName());
            n.setNip(customer.getNip());
            n.setPhones(customer.getPhones());
            n.setEmailAddresses(customer.getEmailAddresses());
            n.setAddress(customer.getAddress());
            n.setAccount(customer.getAccount());
            n.setType(customer.getType());

            customerRepo.save(n);
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
