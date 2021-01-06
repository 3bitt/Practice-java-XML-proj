package pl.edu.wit.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import pl.edu.wit.controller.JaxbFactory;
import pl.edu.wit.jpa.dao.companyA.model.CaAccount;
import pl.edu.wit.jpa.dao.companyA.model.CaCountryCode;
import pl.edu.wit.jpa.dao.companyA.model.ObjectFactory;
import pl.edu.wit.jpa.repository.AccountRepository;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@ShellComponent
public class XMLControllerCommands {

    @Autowired
    private JaxbFactory jaxbFactory;

    @Autowired
    private AccountRepository accountRepo;

    @ShellMethod("Generate dummy xml")
    public void addClient() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(CaAccount.class);

        Unmarshaller mar = jaxbFactory.getUnmarshaller(CaAccount.class);
        CaAccount account = (CaAccount) mar.unmarshal(new FileReader("account.xml"));
        System.out.println(account.toString());
        accountRepo.save(account);

    }

    @ShellMethod("Generate dummy xml")
    public void marshal() throws JAXBException, IOException {
        CaAccount account = new CaAccount();
        account.setName("Account name");
        account.setNumber("111111155577");
        account.setCountryCode(CaCountryCode.AD);

        Marshaller mar = jaxbFactory.getMarshaller(CaAccount.class);
        mar.marshal(new JAXBElement<CaAccount>(new QName("firmaB", "account"),CaAccount.class, account),new File("./account.xml"));
//        mar.marshal(account, new File("./account.xml"));
    }

}
