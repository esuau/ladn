public class WriteXMLFile {
    public String factoryXML(Part p) {
    	String xml="<?xml version=\"1.0\" encoding=\"TF-8\" standalone=\"yes\"?>";
        xml+="<piece id="+p.getId()+">";
        xml+="<libelle_piece>"+p.getReference()+"</libelle_piece>";
        xml+="<fabricant>"+p.getProvider()+"</fabricant>";
        xml+="<qte_dispo>"+p.getAvailableQuantity()+"</qte_dispo>";
        xml+="<valeur_piece>"+p.getPrice()+"</valeur_piece>";
        xml+="</piece>";
        
	return xml;			
    }

    
}
