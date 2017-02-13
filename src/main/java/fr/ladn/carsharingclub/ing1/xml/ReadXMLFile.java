package com.mkyong.seo;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile2 {

  public static void main(String argv[]) {

    try {

	File fXmlFile = new File("repertoire.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);

	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("piece");

	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);

		System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			System.out.println("id : " + eElement.getAttribute("id"));
			System.out.println("libelle_piece : " + eElement.getElementsByTagName("libelle_piece").item(0).getTextContent());
			System.out.println("fabricant : " + eElement.getElementsByTagName("fabricant").item(0).getTextContent());
			System.out.println("qte_dispo : " + eElement.getElementsByTagName("qte_dispo").item(0).getTextContent());
			System.out.println("valeur_piece : " + eElement.getElementsByTagName("valeur_piece").item(0).getTextContent());

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
  }

}