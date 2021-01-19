package pl.edu.wit.jpa.repository.firmaA.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.edu.wit.jpa.dao.companyA.model.CaAddress;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerData;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerDataList;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.lang.annotation.Inherited;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerDataListRepositoryImpl{

    @Autowired
    CustomerDataListRepository customerDataListRepo;

    @Autowired
    EntityManager em;

    CustomerDataListRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Transactional
    public void saveParent(CaCustomerDataList customerList){
        CaCustomerDataList list = new CaCustomerDataList();
        list.setSynchronizeNo(customerList.getSynchronizeNo());
        list.setDateItem(customerList.getDateItem());
        CaCustomerDataList l = em.merge(customerList);
        em.persist(l);
//        customerDataListRepo.save(list);
    }

    public void save(CaCustomerDataList customerList){
        customerDataListRepo.save(customerList);
    }

    @Transactional
    public List<CaCustomerDataList> findAll(){
        return customerDataListRepo.findAll();

    }
}


