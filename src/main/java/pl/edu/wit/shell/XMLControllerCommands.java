package pl.edu.wit.shell;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.xml.sax.SAXException;
import pl.edu.wit.controller.JaxbFactory;
import pl.edu.wit.jpa.dao.companyA.model.*;
import pl.edu.wit.jpa.repository.firmaA.customer.CustomerDataImpl;
import pl.edu.wit.jpa.repository.firmaA.customer.CustomerDataListRepository;
import pl.edu.wit.jpa.repository.firmaA.order.OrderCustomerRepository;
import pl.edu.wit.jpa.repository.firmaA.order.OrderImpl;
import pl.edu.wit.jpa.repository.firmaA.order.OrdersRepository;
import pl.edu.wit.jpa.repository.firmaA.order.OrderReporistory;

import javax.transaction.Transactional;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.util.List;

@ShellComponent("KLIENT")
public class XMLControllerCommands {

    @Autowired
    private JaxbFactory jaxbFactory;

//    @Autowired
//    private AccountRepository accountRepo;
    @Autowired
    private CustomerDataListRepository customerListRepo;
    @Autowired
    private CustomerDataImpl customerRepoImpl;

    @Autowired
    private OrderReporistory orderRepo;
    @Autowired
    private OrdersRepository ordersRepo;
    @Autowired
    private OrderCustomerRepository orderCustomerRepo;
    @Autowired
    private OrderImpl orderRepoImpl;

    @ShellMethod("Załaduj plik z danymi klientów podając nazwe pliku jako argument")
    @Transactional
    public void ladujKlientow(
            @ShellOption(defaultValue="customerDatas.xml") String plik) throws JAXBException, IOException, SAXException {

        JAXBContext context = JAXBContext.newInstance();
        Unmarshaller jaxb = context.createUnmarshaller();

        if (plik.equals("customerDatas.xml")) {
            jaxb = jaxbFactory.getUnmarshaller(ObjectFactory.class, "Firma_A.xsd");
        } else if (plik.equals("customerSync.xml") ) {
            jaxb = jaxbFactory.getUnmarshaller(ObjectFactory.class, "Firma_B.xsd");
        } else {
            System.err.println("Niepoprawna nazwa pliku: " + plik);
            System.err.println("Możliwe nazwy: [customerDatas.xml] lub [customerSync.xml]");
            return;
        }

        try {
//      ### Get root to fix missing XmlRootElement in jaxb class when unmarshalling
            JAXBElement<CaCustomerDataList> root =
                    jaxb.unmarshal(new StreamSource(plik), CaCustomerDataList.class);
            CaCustomerDataList customerData = root.getValue();

//      #### Zapisujemy customerDataList jako pojedyńczy obiekt bez zagnieżdzonych obiektów
            CaCustomerDataList list = new CaCustomerDataList();
            list.setId(customerData.getId());
            list.setDateItem(customerData.getDateItem());
            list.setSynchronizeNo(customerData.getSynchronizeNo());
            customerListRepo.save(list);

//      #### Zapisujemy liste klientów wraz z zagnieżdzonymi elementami (adres, konta, itd.)
            customerRepoImpl.saveAll(customerData.getCustomerDatas());

        } catch (JAXBException e) {
            System.out.println("Exception: " + e);
        }
    }


    @ShellMethod("Zaladuj zamowienia klientow")
    @Transactional
    public void ladujZamowienia(
            @ShellOption(defaultValue="orders.xml") String plik) throws JAXBException, IOException, SAXException {

        JAXBContext context = JAXBContext.newInstance();
        Unmarshaller jaxb = context.createUnmarshaller();

        if (plik.equals("orders.xml")) {
            jaxb = jaxbFactory.getUnmarshaller(ObjectFactory.class, "Firma_A.xsd");
        } else if (plik.equals("orderSync.xml") ) {
            jaxb = jaxbFactory.getUnmarshaller(ObjectFactory.class, "Firma_B.xsd");
        } else {
            System.err.println("Niepoprawna nazwa pliku: " + plik);
            System.err.println("Możliwe nazwy: [orders.xml] lub [orderSync.xml]");
            return;
        }

        try {
//      ### Get root to fix missing XmlRootElement in jaxb class when unmarshalling
            JAXBElement<CaOrders> root =
                    jaxb.unmarshal(new StreamSource(plik), CaOrders.class);
            CaOrders customerOrders = root.getValue();

//      #### Zapisujemy customerDataList jako pojedyńczy obiekt bez zagnieżdzonych obiektów
            CaOrders list = new CaOrders();
            list.setId(customerOrders.getId());
            list.setDateItem(customerOrders.getDateItem());
            list.setSynchronizeNo(customerOrders.getSynchronizeNo());
            ordersRepo.save(list);

//      #### Zapisujemy liste klientów wraz z zagnieżdzonymi elementami (adres, konta, itd.)
            List<CaOrder> orderList = customerOrders.getOrder();
//            orderRepoImpl.saveOrders(orderList);
            for (CaOrder order : orderList){
                CaCustomerData sender = customerRepoImpl.findCustomerById(order.getSender().getId());
                CaCustomerData recipient = customerRepoImpl.findCustomerById(order.getRecipient().getId());

                if (sender == null) {
                    System.err.println("[Sender] Nie ma takiego klienta w bazie:\n" +
                            order.getSender().getName() +
                            order.getSender().getSurname() );
                    continue;
                }
                if (recipient == null) {
                    System.err.println("[Recipient] Nie ma takiego klienta w bazie:\n" +
                            order.getRecipient().getName() +
                            order.getRecipient().getSurname() );
                    continue;
                }

                boolean validAccount = false;
                for (CaAccount acc : recipient.getAccount()){
                    if (acc.getNumber().equals(order.getAccountNumber())) {
                        validAccount = true;
                        break;
                    }
                }
                if (validAccount){
                    System.out.println("\n\nZamowienie wyglada okej!\n\n");
                    orderCustomerRepo.save(order.getSender());
                    orderCustomerRepo.save(order.getRecipient());
                    orderRepoImpl.saveOrder(order);
                } else {
                    System.err.println(
                            "Odbiorca nie posiada podanego numeru konta" +
                            "\n[Numer konta]: " + order.getAccountNumber() +
                            "\n[Odbiorca] " + recipient.getName() + recipient.getSurname()
                    );
                }
            }

        } catch (JAXBException e) {
            System.out.println("Exception: " + e);
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