package pl.edu.wit.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.xml.sax.SAXException;
import pl.edu.wit.controller.JaxbFactory;
import pl.edu.wit.jpa.dao.companyA.model.*;
import pl.edu.wit.jpa.repository.firmaA.customer.CustomerDataListRepositoryImpl;
import pl.edu.wit.jpa.repository.firmaA.customer.CustomerDataRepositoryImpl;
import pl.edu.wit.jpa.repository.firmaA.customer.CustomerDataListRepository;
import pl.edu.wit.jpa.repository.firmaA.order.*;
import pl.edu.wit.jpa.dao.companyA.model.CaOrders;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private CustomerDataListRepositoryImpl customerListImpl;
    @Autowired
    private CustomerDataRepositoryImpl customerRepoImpl;

    @Autowired
    private OrderReporistory orderRepo;
    @Autowired
    private OrdersRepository ordersRepo;
    @Autowired
    private OrderCustomerRepository orderCustomerRepo;
    @Autowired
    private OrderImpl orderRepoImpl;
    @Autowired
    private OrderMasterImpl orderMaster;

    public static <T> T unmarshalXml(Class<T> clazz, StreamSource queryResults,
                                     String contextNamespace) {
        T resultObject = null;
        try {
            //Create instance of the JAXBContext from the class-name
            JAXBContext jc;
            jc = JAXBContext.newInstance(Class.forName(clazz.getName()));
            Unmarshaller u = jc.createUnmarshaller();
            resultObject = clazz.cast(u.unmarshal(queryResults));
        }
        //Put your own error-handling here.
        catch (JAXBException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz.cast(resultObject);
    }

    @ShellMethod("Załaduj plik z danymi klientów podając nazwe pliku jako argument")
    public void laduj(
            @ShellOption(defaultValue = "customerDatas.xml") String plik) throws JAXBException, IOException, SAXException {

//        JAXBContext context = JAXBContext.newInstance();
//        Unmarshaller jaxb = context.createUnmarshaller();
//
//        if (plik.equals("customerDatas.xml")) {
//            jaxb = jaxbFactory.getUnmarshaller(ObjectFactory.class, "Firma_A.xsd");
//
//        } else if (plik.equals("customerSync.xml") ) {
//            jaxb = jaxbFactory.getUnmarshaller(ObjectFactory.class, "Firma_B.xsd");
//        } else {
//            System.err.println("Niepoprawna nazwa pliku: " + plik);
//            System.err.println("Możliwe nazwy: [customerDatas.xml] lub [customerSync.xml]");
//            return;
//        }


        try {
//      ### Get root to fix missing XmlRootElement in jaxb class when unmarshalling
//            JAXBElement<CaCustomerDataList> root =
//                    jaxb.unmarshal(new StreamSource(plik), CaCustomerDataList.class);
//            CaCustomerDataList customerData = root.getValue();

            Unmarshaller jaxb = jaxbFactory.getUnmarshaller(CaCustomerDataList.class, "FIRMA_A.xsd", plik);
            CaCustomerDataList customerData = (CaCustomerDataList) jaxb.unmarshal(new File(plik));


            customerListImpl.saveParent(customerData);

        } catch (UnmarshalException ignored){ }

    }


    @ShellMethod("Zaladuj zamowienia klientow")
    public void ladujZamowienia(
            @ShellOption(defaultValue = "orders2.xml") String plik) throws JAXBException, IOException, SAXException, ClassNotFoundException {

        JAXBContext context = JAXBContext.newInstance(CaOrders.class);
        Unmarshaller jaxb = context.createUnmarshaller();

//        try {
//      ### Get root to fix missing XmlRootElement in jaxb class when unmarshalling
        JAXBElement<CaOrders> root =
                jaxb.unmarshal(new StreamSource(plik), CaOrders.class);
        CaOrders orders = root.getValue();

//        Marshaller marsh = jaxbFactory.getMarshaller(CaOrders.class);
//        marsh.marshal(orders, new File("Test.xml"));
//
//        if (plik.equals("orders.xml")) {
//            jaxb = jaxbFactory.getUnmarshaller(ObjectFactory.class, "Firma_A.xsd");
//        } else if (plik.equals("orderSync.xml") ) {
//            jaxb = jaxbFactory.getUnmarshaller(ObjectFactory.class, "Firma_B.xsd");
//        } else {
//            System.err.println("Niepoprawna nazwa pliku: " + plik);
//            System.err.println("Możliwe nazwy: [orders.xml] lub [orderSync.xml]");
//            return;
//        }

//        for (CaOrder order : orders.getOrder()){
//            CaOrderCustomerData sender = order.getSender();
//        }

        Unmarshaller un = jaxbFactory.getUnmarshaller(CaOrders.class, "FIRMA_A.xsd", plik);
        CaOrders or = (CaOrders) un.unmarshal(new File(plik));


        orderMaster.processOrders(or);


    }

    @ShellMethod("Pokaz klientow")
    public void pokazKlientow() {

        List<CaCustomerData> customers = customerRepoImpl.findCustomer();
        if (customers.isEmpty()){
            System.err.println("\n\n----- Brak klientów ----- \n" +
                    "----- Załaduj dane -----\n");
        } else {
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.printf("%4s %12s %15s %13s %13s %12s %13s %13s", "ID", "Comp. NAME", "NAME", "SURNAME", "PESEL", "NIP","ADDRESS_ID","ACCOUNT_ID");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------------");


            for (CaCustomerData customer : customers) {
                List<Long> addressIds = new ArrayList<>();
                List<Long> accountIds = new ArrayList<>();
                for (CaAddress address : customer.getAddress()){
                    addressIds.add(address.getId());
                }
                for (CaAccount account : customer.getAccount()){
                    accountIds.add(account.getId());
                }
                System.out.format("%4s %12s %15s %13s %13s %12s %10s %13s",
                        customer.getId(), customer.getCompanyName(), customer.getName(), customer.getSurname(), customer.getPesel(), customer.getNip(), addressIds.toString(),accountIds.toString());
                System.out.println();
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
    }

    @ShellMethod("Pokaz adresy")
    public void pokazAdresy(){
        List<CaAddress> addresses = customerRepoImpl.findAddresses();

        if (addresses.isEmpty()){
            System.err.println("\n\n----- Brak adresów ----- \n" +
                    "----- Załaduj dane -----\n");
        } else {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.printf("%4s %14s %15s %13s %13s %13s %12s", "ID", "STREET", "STREET NO", "HOME NO", "CITY", "POSTAL CODE", "COUNTRY");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------");


            for (CaAddress address : addresses) {
                System.out.format("%4s %14s %15s %13s %13s %12s %8s",
                        address.getId(), address.getStreet(), address.getStreetNo(), address.getHomeNo(), address.getCity(), address.getPostalCode(),address.getCountryCode());
                System.out.println();
            }
            System.out.println("-----------------------------------------------------------------------------------------");
        }
    }

    @ShellMethod("Pokaz konta")
    public void pokazKonta(){
        List<CaAccount> accounts = customerRepoImpl.findAccounts();
        if (accounts.isEmpty()){
            System.err.println("\n\n----- Brak kont ----- \n" +
                    "----- Załaduj dane -----\n");
        } else {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.printf("%4s %23s %27s %20s", "ID", "NAME", "NUMBER", "COUNTRY");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------");


            for (CaAccount account : accounts) {
                System.out.format("%4s %23s %27s %20s",
                        account.getId(), account.getName(), account.getNumber(), account.getCountryCode());
                System.out.println();
            }
            System.out.println("-----------------------------------------------------------------------------------------");
        }

    }
}