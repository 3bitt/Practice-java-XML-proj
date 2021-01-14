package pl.edu.wit.jpa.repository.firmaA.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private AddressRepository addressRepo;
    @Autowired
    EntityManager em;

    public CustomerDataRepositoryImpl(EntityManager em){
        this.em = em;
    }
    @Transactional
    public void saveAll(List<CaCustomerData> customerDataList){
        customerRepo.saveAll(customerDataList);
    }
    public CaCustomerData findCustomerById(Long id){
        return customerRepo.findByid(id);
    }


    @Transactional
    public void save(CaCustomerData customer){

//        List<CaAccount> act = customer.getAccount();
//        List<CaAddress> adr = customer.getAddress();

        CaCustomerData n = em.merge(customer);

//        System.out.println("\n\nCUSTOMER ID: " + customer.getId());
//        n.setId(customer.getId());
//        n.setName(customer.getName());
//        n.setSurname(customer.getSurname());
//        n.setPesel(customer.getPesel());
//        n.setDocumentCountry(customer.getDocumentCountry());
//        n.setDocumentType(customer.getDocumentType());
//        n.setDocumentNumber(customer.getDocumentNumber());
//        n.setCompanyName(customer.getCompanyName());
//        n.setNip(customer.getNip());
//        n.setPhones(customer.getPhones());
//        n.setEmailAddresses(customer.getEmailAddresses());
//        n.setAddress(customer.getAddress());
//        n.setAccount(customer.getAccount());
//        n.setType(customer.getType());

//        List<CaAccount> list = new ArrayList<CaAccount>();
//        for (CaAccount acc : customer.getAccount()){
//            CaAccount a = accountRepo.findByid(acc.getId());
//            if (a == null){
//               CaAccount created =  accountRepo.save(acc);
//               list.add(created);
//            } else {
//                System.out.println("\n\n\nPRZED MERGEM: " + a.getId());
//                CaAccount popo = em.merge(a);
//                list.add(popo);
//                System.out.println("\n\n\nPO MERGU: " + popo.getId());
//            }
//        }


        em.persist(n);
    }
    public List<CaAccount> checkAccount(List<CaAccount> account){
        List<CaAccount> list = new ArrayList<CaAccount>();

        for (CaAccount acc : account){
            if (accountRepo.findByNumber(acc.getNumber()) == null){
                System.out.println("Nie znalazlem takiego konta" + acc.getNumber());
                accountRepo.saveAndFlush(acc);
                list.add(acc);
            } else {
                System.out.println("Znalazłem konto" + acc.getNumber());
                list.add(acc);
            }
        }
        return list;
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
