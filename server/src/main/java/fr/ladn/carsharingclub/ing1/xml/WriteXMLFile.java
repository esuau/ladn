package fr.ladn.carsharingclub.ing1.xml;

import fr.ladn.carsharingclub.ing1.model.Part;

/**
 * The XML generator.
 */
public class WriteXMLFile {

    /**
     * This method transforms a Part object into a XML string.
     *
     * @param p The part to stringify.
     * @return a XML string.
     * @see Part
     */
    public static String factoryXML(Part p) {
        String xml = "<?xml version=\"1.0\" encoding=\"TF-8\" standalone=\"yes\"?>";
        xml += "<piece id=\"" + p.getId() + "\">";
        xml += "<libelle_piece>" + p.getReference() + "</libelle_piece>";
        xml += "<fabricant>" + p.getProvider() + "</fabricant>";
        xml += "<qte_dispo>" + p.getAvailableQuantity() + "</qte_dispo>";
        xml += "<valeur_piece>" + p.getPrice() + "</valeur_piece>";
        xml += "</piece>";
        return xml;
    }
}
