package pl.edu.wit.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pl.edu.wit.controller.JaxbFactory;
import pl.edu.wit.jpa.dao.companyA.model.CaAccount;
import pl.edu.wit.jpa.dao.companyA.model.CaCountryCode;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerDataList;
import pl.edu.wit.jpa.dao.companyA.model.ObjectFactory;
import pl.edu.wit.jpa.repository.AccountRepository;
import pl.edu.wit.jpa.repository.CustomerDataListRepository;

import javax.xml.bind.*;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

@ShellComponent("KLIENT")
public class XMLControllerCommands {

    @Autowired
    private JaxbFactory jaxbFactory;

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private CustomerDataListRepository customerRepo;

    @ShellMethod("Załaduj plik z danymi klientów podając nazwe pliku jako argument")
    public void zaladujKlientow(
            @ShellOption(defaultValue="FA_customerDatas_0.xml") String plik) throws JAXBException, IOException, XMLStreamException {

        Unmarshaller mar = jaxbFactory.getUnmarshaller(ObjectFactory.class);


//      ### Get root to fix missing XmlRootElement in jaxb class when unmarshalling
        JAXBElement<CaCustomerDataList> root =
                mar.unmarshal(new StreamSource(plik), CaCustomerDataList.class);

        CaCustomerDataList customerData = root.getValue();

        customerRepo.save(customerData);

    }

    @ShellMethod("Generate dummy xml")
    public void marshal() throws JAXBException, IOException {

//        CaCustomerDataList customerList = new CaCustomerDataList();
//        customerList.setDateItem(new Date());
//        customerList.setId(1L);
//        customerList.setSynchronizeNo("synchronizeNo-1");
//        CaAccount account = new CaAccount();
//        account.setName("Account name");
//        account.setNumber("111111155577");
//        account.setCountryCode(CaCountryCode.AD);
//
//        Marshaller mar = jaxbFactory.getMarshaller(CaAccount.class);
//        mar.marshal(new JAXBElement<CaAccount>(new QName("firmaB", "account"),CaAccount.class, account),new File("./account.xml"));

    }

}


// ###### Kawalek kodu ze stackOverflow - problem przestrzeni nazw
// ###### https://stackoverflow.com/questions/1871060/jaxb-unmarshalling-ignoring-namespace-turns-element-attributes-into-null/7693661

//        XMLInputFactory xif = XMLInputFactory.newFactory();
//        xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false); // this is the magic line
//
//// create xml stream reader using our configured factory
//        StreamSource source = new StreamSource(plik);
//        XMLStreamReader xsr = xif.createXMLStreamReader(source);
//
//// unmarshall, note that it's better to reuse JAXBContext, as newInstance()
//// calls are pretty expensive
//        JAXBContext jc = JAXBContext.newInstance(your.ObjectFactory.class);
//        Unmarshaller unmarshaller = jc.createUnmarshaller();
//        Object unmarshal = unmarshaller.unmarshal(xsr);