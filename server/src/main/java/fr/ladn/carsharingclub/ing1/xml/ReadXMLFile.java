package fr.ladn.carsharingclub.ing1.xml;

import fr.ladn.carsharingclub.ing1.model.Part;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class ReadXMLFile {

    public static Part parserXML(String xml) {
        try {
            //String xmlString="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?><piece id=\"11402907\"><libelle_piece>Pneu</libelle_piece><fabricant>Michelin</fabricant><qte_dispo >235</qte_dispo><valeur_piece >60</valeur_piece></piece>";
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
                    System.out.println("La piece a bien été retournée \n");
                    return p;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}