package pl.edu.wit.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.xml.sax.SAXException;
import pl.edu.wit.controller.JaxbFactory;
import pl.edu.wit.jpa.dao.companyA.model.CaAccount;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerData;
import pl.edu.wit.jpa.dao.companyA.model.CaCustomerDataList;
import pl.edu.wit.jpa.dao.companyA.model.ObjectFactory;
import pl.edu.wit.jpa.repository.AccountRepository;
import pl.edu.wit.jpa.repository.CustomerDataImpl;
import pl.edu.wit.jpa.repository.CustomerDataListRepository;
import pl.edu.wit.jpa.repository.CustomerDataRepository;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

@ShellComponent("KLIENT")
public class XMLControllerCommands {

    @Autowired
    private JaxbFactory jaxbFactory;

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private CustomerDataListRepository customerListRepo;
    @Autowired
    private CustomerDataImpl customerRepoImpl;

    @ShellMethod("Załaduj plik z danymi klientów podając nazwe pliku jako argument")
    public void zaladuj(
            @ShellOption(defaultValue="FA_customerDatas_0.xml") String plik) throws JAXBException, IOException, XMLStreamException, SAXException {

        Unmarshaller mar = jaxbFactory.getUnmarshaller(ObjectFactory.class);

//  #### Set Schema so marshaller can validate XML file basing on XSD schema file
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("FIRMA_A.xsd"));
        mar.setSchema(schema);

        try {
//      ### Get root to fix missing XmlRootElement in jaxb class when unmarshalling
            JAXBElement<CaCustomerDataList> root =
                    mar.unmarshal(new StreamSource(plik), CaCustomerDataList.class);
            CaCustomerDataList customerData = root.getValue();
//            List<CaCustomerData> a = customerData.getCustomerDatas();

            for (CaCustomerData c : customerData.getCustomerDatas()){
                List<CaAccount> acc = c.getAccount();
                accountRepo.saveAll(acc);
                customerRepoImpl.addCustomer(c);
            }
            CaCustomerDataList list = new CaCustomerDataList();
            list.setId(customerData.getId());
            list.setDateItem(customerData.getDateItem());
            list.setSynchronizeNo(customerData.getSynchronizeNo());


            customerListRepo.save(list);
        } catch (JAXBException e){

        }

    }

    @ShellMethod("Generate dummy xml")
    public void wyladuj() throws JAXBException, IOException {



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