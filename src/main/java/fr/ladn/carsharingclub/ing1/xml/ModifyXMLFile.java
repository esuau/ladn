package fr.ladn.carsharingclub.ing1.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ModifyXMLFile {
    public static void main(final String[] args) {
        // Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            // Etape 2 : création d'un parseur
            final DocumentBuilder builder = factory.newDocumentBuilder();

            // Etape 3 : création d'un Document
            final Document document = builder.parse(new File("repertoire.xml"));
            final Element racine = document.getDocumentElement();

            // Etape 5 : récupération des pieces
            //final NodeList racineNoeuds = racine.getChildNodes();

            final Element voiture = document.createElement("voiture");
            voiture.setAttribute("numSerie", "1234567890");
            racine.appendChild(voiture);
            final Element marque = document.createElement("marque");
            final Element année = document.createElement("année");
            final Element prix = document.createElement("prix");

            marque.appendChild(document.createTextNode("Mercedes"));
            année.appendChild(document.createTextNode("2016"));
            prix.appendChild(document.createTextNode("125000"));

            voiture.appendChild(marque);
            voiture.appendChild(année);
            voiture.appendChild(prix);

            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer();
            final DOMSource source = new DOMSource(document);
            final StreamResult sortie = new StreamResult(new File("repertoire.xml"));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, sortie);

        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    // On enregistre notre nouvelle arborescence dans le fichier d'origine dans un format classique.
}
