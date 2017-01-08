package fr.ladn.carsharingclub.ing1.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLFile {
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
	    final Document document= builder.parse(new File("src/resources/repertoire.xml"));
			
	    //Affiche du prologue
	    System.out.println("*************PROLOGUE************");
	    System.out.println("version : " + document.getXmlVersion());
	    System.out.println("encodage : " + document.getXmlEncoding());		
            System.out.println("standalone : " + document.getXmlStandalone());
					
	    /*
	     * Etape 4 : récupération de l'Element racine
	     */
	    final Element racine = document.getDocumentElement();
		
	    //Affichage de l'élément racine
	    System.out.println("\n*************RACINE************");
	    System.out.println(racine.getNodeName());
		
	    /*
	     * Etape 5 : récupération des pieces
	     */
	    final NodeList racineNoeuds = racine.getChildNodes();
	    final int nbRacineNoeuds = racineNoeuds.getLength();
			
	    for (int i = 0; i<nbRacineNoeuds; i++) {
	        if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
	        	final Element piece = (Element) racineNoeuds.item(i);
				
		    //Affichage d'une piece
		    System.out.println("\n*************PIECE************");
		    System.out.println("id : " + piece.getAttribute("id"));
			
	    	    /*
		     * Etape 6 : récupération du libelle_piece et du prénom
		     */
		    final Element libelle_piece = (Element) piece.getElementsByTagName("libelle_piece").item(0);
		    final Element fabricant = (Element) piece.getElementsByTagName("fabricant").item(0);
					
		    //Affichage du libelle_piece et du prénom
		    System.out.println("libelle_piece : " + libelle_piece.getTextContent());
		    System.out.println("fabricant : " + fabricant.getTextContent());
					
		    /*
		     * Etape 7 : récupération de la quantité dispo et de la valeur
		     */
		    
		    final Element qte_dispo = (Element) piece.getElementsByTagName("qte_dispo").item(0);
		    final Element valeur_piece = (Element) piece.getElementsByTagName("valeur_piece").item(0);
		    
		    //Affichage du qte_dispo et du prénom
		    System.out.println("qte_dispo : " + qte_dispo.getTextContent());
		    System.out.println("valeur_piece : " + valeur_piece.getTextContent());
		    
	        }				
	    }			
        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }		
    }
}
