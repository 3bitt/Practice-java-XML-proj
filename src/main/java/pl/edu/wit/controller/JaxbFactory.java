package pl.edu.wit.controller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import java.io.IOException;


public class JaxbFactory {


    public Marshaller getMarshaller(Class cls) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(cls);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.setEventHandler(new DefaultValidationEventHandler());
        return mar;
    }

    public Unmarshaller getUnmarshaller(Class cls) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(cls);
        return context.createUnmarshaller();
    }

    public JaxbFactory(){

    }
}
