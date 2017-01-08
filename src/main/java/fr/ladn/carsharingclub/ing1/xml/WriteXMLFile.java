package fr.ladn.carsharingclub.ing1.xml;

import java.io.File;

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

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class WriteXMLFile {
    public static void main(final String[] args) {
        /*
	 * Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
	 */
	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
	try {
	    /*
	     * Etape 2 : création d'un parseur
	     */
	    final DocumentBuilder builder = factory.newDocumentBuilder();
	    		
	    /*
	     * Etape 3 : création d'un Document
	     */
	    final Document document= builder.newDocument();
					
	    /*
	     * Etape 4 : création de l'Element racine
	     */
	    final Element racine = document.createElement("repertoire");
	    document.appendChild(racine);			
			
	    /*
	     * Etape 5 : création d'une personne
	     */
	    final Comment commentaire = document.createComment("John DOE");
	    racine.appendChild(commentaire);
			
	    final Element personne = document.createElement("personne");
	    personne.setAttribute("sexe", "masculin");
	    racine.appendChild(personne);
			
	    /*
	     * Etape 6 : création du nom et du prénom
	     */
	    final Element nom = document.createElement("nom");
	    nom.appendChild(document.createTextNode("DOE"));
			
	    final Element prenom = document.createElement("prenom");
	    prenom.appendChild(document.createTextNode("John"));
			
	    personne.appendChild(nom);
	    personne.appendChild(prenom);			
							
	    /*
	     * Etape 7 : récupération des numéros de téléphone
	     */
	    final Element telephones = document.createElement("telephones");
	    
	    final Element fixe = document.createElement("telephone");
	    fixe.appendChild(document.createTextNode("01 02 03 04 05"));
	    fixe.setAttribute("type", "fixe");
			
	    final Element portable = document.createElement("telephone");
	    portable.appendChild(document.createTextNode("06 07 08 09 10"));
	    portable.setAttribute("type", "portable");
			
	    telephones.appendChild(fixe);
	    telephones.appendChild(portable);
	    personne.appendChild(telephones);
			
	    /*
	     * Etape 8 : affichage
	     */
	    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    final Transformer transformer = transformerFactory.newTransformer();
	    final DOMSource source = new DOMSource(document);
	    final StreamResult sortie = new StreamResult(new File("F:\\file.xml"));
	    final StreamResult result = new StreamResult(System.out);
			
	    //prologue
	    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");			
	    		
	    //formatage
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
	    //sortie
	    transformer.transform(source, result);	// Result = affichage / sortie = fichiers
	}
	catch (final ParserConfigurationException e) {
	    e.printStackTrace();
	}
	catch (TransformerConfigurationException e) {
	    e.printStackTrace();
	}
	catch (TransformerException e) {
	    e.printStackTrace();
	}			
    }
}
