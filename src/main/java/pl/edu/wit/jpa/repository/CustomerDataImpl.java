package pl.edu.wit.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.edu.wit.jpa.dao.companyA.model.CaAccount;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerData;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerDataImpl {

    @Autowired
    private CustomerDataRepository customerRepo;
    @Autowired
    private AccountRepository accountRepo;

    public CustomerDataImpl(EntityManager em){
    }

    public List<CaCustomerData> getAllCustomers(){
        List<CaCustomerData> customers = new ArrayList<>();
        customerRepo.findAll().forEach(customers::add);
        return customers;
    }

    public void addCustomer(CaCustomerData customer){
        customerRepo.save(customer);
    }
    public void addCustomerAccount(CaCustomerData customer){
        List<CaAccount> account = customer.getAccount();
        for (CaAccount acc : account){
            accountRepo.save(acc);
        }
    }
    public void updateCustomer(CaCustomerData customer){
        customerRepo.save(customer);
    }

    public void deleteCustomer(Long id){
        customerRepo.deleteById(id);
    }

}
