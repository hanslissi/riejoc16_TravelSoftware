/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import Data.Destination;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author johannesriedmueller
 */
public class XMLAccess {
    private static XMLAccess instance;
    private final static String XMLPATH = "xml/destinations.xml";

    private XMLAccess(){}
    
    public static synchronized XMLAccess getInstance() throws IOException, JDOMException {
        if(instance == null) {
            instance = new XMLAccess();
        }
        return instance;
    }
    
    private Document getDocument() throws IOException, JDOMException {
        File xmlFile = new File(XMLPATH);
        SAXBuilder builder = new SAXBuilder();
        return builder.build(xmlFile);
    }
    
    private void persistXML(Document document) throws FileNotFoundException, IOException {
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        
        try (FileOutputStream fos = new FileOutputStream(new File(XMLPATH));){
            outputter.output(document, fos);
        }
    }
    
    public void persistDestinations (ArrayList<Destination> destinations) throws IOException {
        Element rootElement = new Element("destinations");
        for (Destination destination : destinations) {
            Element destinationElem = new Element("destination");
            destinationElem.setText(destination.getCityName());
            rootElement.addContent(destinationElem);
        }
        persistXML(new Document(rootElement));
    }
    
    public ArrayList<String> getDestinations() {
        try{
            Document document = getDocument();
            Element rootElement = document.getRootElement();
            ArrayList<String> destinations = new ArrayList<>();
            for (Element destination : rootElement.getChildren("destination")) {
                destinations.add(destination.getText());
            }
            return destinations;
        } catch (Exception ex) {
            return null;
        }
    }
    
}
