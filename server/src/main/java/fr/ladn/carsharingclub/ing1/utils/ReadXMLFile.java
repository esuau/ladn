package fr.ladn.carsharingclub.ing1.utils;

import fr.ladn.carsharingclub.ing1.model.Part;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * The XML parser.
 */
public class ReadXMLFile {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(ReadXMLFile.class.getName());

    /**
     * The XML parser.
     * This function parses the string received under XML format via the input stream.
     *
     * @param xml The string to be parsed.
     * @return a new Part object.
     */
    public static Part parserXML(String xml) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("piece");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Part p = new Part(Integer.parseInt(eElement.getAttribute("id")), eElement.getElementsByTagName("libelle_piece").item(0).getTextContent(), eElement.getElementsByTagName("fabricant").item(0).getTextContent(), Integer.parseInt(eElement.getElementsByTagName("qte_dispo").item(0).getTextContent()), Float.valueOf(eElement.getElementsByTagName("valeur_piece").item(0).getTextContent()));
                    logger.info("The part " + p.getId() + " was successfully parsed.");
                    return p;
                }
            }
        } catch (Exception e) {
            logger.info("Failed to parse: " + xml);
        }
        return null;
    }

}