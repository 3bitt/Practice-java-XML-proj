package pl.edu.wit.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;


public class JaxbFactory {

    private final String firmaA_schema = "Firma_A.xsd";
    private final String firmaB_schema = "Firma_B.xsd";

    public Marshaller getMarshaller(Class cls) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(cls);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        return mar;
    }

    public Unmarshaller getUnmarshaller(Class cls, String validationSchemaXSD) throws JAXBException, IOException, SAXException {
        JAXBContext context = JAXBContext.newInstance(cls);
        Unmarshaller mar = context.createUnmarshaller();
        mar.setEventHandler(event -> {
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
        });

        //  #### Set Schema so marshaller can validate XML file basing on XSD schema file
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(validationSchemaXSD));
        mar.setSchema(schema);
        return mar;
    }

    public JaxbFactory(){

    }
}
