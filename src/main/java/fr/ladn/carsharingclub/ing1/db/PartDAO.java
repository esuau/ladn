
package fr.ladn.carsharingclub.ing1.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.ladn.carsharingclub.ing1.model.Part;

public class PartDAO {
    public void create(Part part) throws Exception{
        String reference = part.getReference();
        String provider = part.getProvider();
        int availableQuantity = part.getAvailableQuantity();
        float price  = part.getPrice();
        
        PreparedStatement ps = ConnectionDB.getConexao().prepareStatement("INSERT INTO pieces ( libelle_piece, fabricant, qte_dispo, valeur_piece ) VALUES ( ?, ?, ?, ? )");
        ps.setString(1, reference);
        ps.setString(2, provider);
        ps.setInt(3, availableQuantity);
        ps.setFloat(4, price);
        ps.execute();
    }
    
    public Part read(int id) throws Exception{
        PreparedStatement ps = ConnectionDB.getConexao().prepareStatement("SELECT * FROM pieces WHERE id_piece = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            
            String reference = rs.getString("reference");
            String provider = rs.getString("provider");
            int availableQuantity = rs.getInt("available quantity");
            float price = rs.getFloat("price");
            
            return new Part(id, reference, provider, availableQuantity, price);
            
        }else{
            return null;
        }
        
    }
    
    public void update(Part part) throws Exception{
        PreparedStatement ps = ConnectionDB.getConexao().prepareStatement("UPDATE pieces SET libelle_piece = ?, fabricant = ?, qte_dispo = ?, valeur_piece = ? WHERE id_piece = ?");
        ps.setString(1, part.getReference());
        ps.setString(2, part.getProvider());
        ps.setInt(3, part.getAvailableQuantity());
        ps.setFloat(4, part.getPrice());
        ps.setInt(5, part.getId());
        ps.execute();
    }
    public void delete(Part part) throws Exception{
        PreparedStatement ps = ConnectionDB.getConexao().prepareStatement("DELETE FROM pieces WHERE id_piece = ?");
        ps.setInt(1, part.getId());
        ps.execute();
    }
    
    
}
