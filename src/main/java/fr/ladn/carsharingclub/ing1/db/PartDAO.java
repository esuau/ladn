package fr.ladn.carsharingclub.ing1.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import fr.ladn.carsharingclub.ing1.model.Part;

public class PartDAO {

    private ConnectionPool pool;

    public PartDAO(ConnectionPool p) {
        pool = p;
    }

    /**
     * Creates a part in the database.
     * <p>
     * Part appearing into the database are considered available in the stock.
     * </p>
     *
     * @param part to add to the stock
     * @throws Exception
     */
    public void create(Part part) throws Exception {
        String reference = part.getReference();
        String provider = part.getProvider();
        int availableQuantity = part.getAvailableQuantity();
        float price = part.getPrice();

        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO pieces ( libelle_piece, fabricant, qte_dispo, valeur_piece ) VALUES ( ?, ?, ?, ? )");
        ps.setString(1, reference);
        ps.setString(2, provider);
        ps.setInt(3, availableQuantity);
        ps.setFloat(4, price);
        ps.execute();
        pool.returnConnection(conn);
    }

    /**
     * Gets information from an existing part
     *
     * @param id of the part to be read
     * @return part information
     * @throws Exception
     */
   /* public Part read(int id) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM pieces WHERE id_piece = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        pool.returnConnection(conn);

        if (rs.next()) {

            String reference = rs.getString("libelle_piece");
            String provider = rs.getString("fabricant");
            int availableQuantity = rs.getInt("qte_dispo");
            float price = rs.getFloat("valeur_piece");

            return new Part(id, reference, provider, availableQuantity, price);
        } else {
            return null;
        }
    }*/
    
    public  String[][] read(int id) throws Exception {
    	
            Connection conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM pieces WHERE id_piece = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            pool.returnConnection(conn);
            
            	
            	ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            	rs.last(); 
            	int nbLignes = rs.getRow(); 
            	rs.beforeFirst(); 
            	
            	int nbCols = rsmd.getColumnCount();
            	int j=0;
            	String[][] p=new String[nbLignes][nbCols];
            	while (rs.next()) {
       
            	for (int i = 1; i <= nbCols; i++){
            	p[j][i-1] = rs.getString(i);}
            	j=j+1;
            	}
            	
            	rs.close();
            	 return p;
            
        }
    	
    public String[][]  read_t() throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM pieces");
       
        ResultSet rs = ps.executeQuery();
        pool.returnConnection(conn);
        
        	
        	ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
        	rs.last(); 
        	//on récupère le numéro de la ligne
        	int nbLignes = rs.getRow(); 
        	//on replace le curseur avant la première ligne
        	rs.beforeFirst(); 
        	
        	int nbCols = rsmd.getColumnCount();
        	int j=0;
        	String[][] p=new String[nbLignes][nbCols];
        	while (rs.next()) {
   
        	for (int i = 1; i <= nbCols; i++){
        	p[j][i-1] = rs.getString(i);}
        	j=j+1;
        	}
        	
        	rs.close();
        	/*for (int i =j ; i < p[0].length; i++){
        		for (int k = nbCols+1; k < p.length; k++){
        			p[k][i] = " ";}
        		}*/
            	
            	
        	
       /* Object[][] p=new String[10][10];
        int i=0;
        if (rs.next()) {
        	p[i][0] = rs.getInt("id_piece");
        	p[i][1] = rs.getString("libelle_piece");
        	p[i][2] = rs.getString("fabricant");
        	p[i][3] = rs.getInt("qte_dispo");
        	p[i][4] = rs.getFloat("valeur_piece");
            i=i++;
        }
        System.out.println("coucoubhjjvhvjh"+p[0][0]);*/
        return p;
    }


    /**
     * Updates part information in the stock
     *
     * @param part to be updated
     * @throws Exception
     */
    public void update(Part part) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = pool.getConnection().prepareStatement("UPDATE pieces SET libelle_piece = ?, fabricant = ?, qte_dispo = ?, valeur_piece = ? WHERE id_piece = ?");
        ps.setString(1, part.getReference());
        ps.setString(2, part.getProvider());
        ps.setInt(3, part.getAvailableQuantity());
        ps.setFloat(4, part.getPrice());
        ps.setInt(5, part.getId());
        ps.execute();
        pool.returnConnection(conn);
    }

    /**
     * Permanently removes a part from the database
     *
     * @param part to be removed from the stock
     * @throws Exception
     */
    public void delete(Part part) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM pieces WHERE id_piece = ?");
        ps.setInt(1, part.getId());
        ps.execute();
        pool.returnConnection(conn);
    }
}
