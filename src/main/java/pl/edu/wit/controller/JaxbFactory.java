package pl.edu.wit.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.*;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import java.io.IOException;


public class JaxbFactory {

    public Marshaller getMarshaller(Class cls) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(cls);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        return mar;
    }

    public Unmarshaller getUnmarshaller(Class cls) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(cls);
        Unmarshaller mar = context.createUnmarshaller();
        mar.setEventHandler(new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent event) {
                System.err.println("Niepoprawne dane!");
                ValidationEventLocator loc = event.getLocator();
                int line = loc.getLineNumber();
                int column = loc.getColumnNumber();
                String msgSubString = event.getMessage().substring(
                        event.getMessage().indexOf("{"),
                        event.getMessage().indexOf("}")+1
                );

                System.err.print("Linia: " +line+ "\nKolumna: " +column  );

                System.err.println("\nBrakuje " + msgSubString);
                return false;
            }

        });
        return mar;
    }

    public JaxbFactory(){

    }
}
