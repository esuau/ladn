package fr.ladn.carsharingclub.ing1.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import fr.ladn.carsharingclub.ing1.model.Authentification;
import fr.ladn.carsharingclub.ing1.model.Tech;

public class TechDAO {
	
	private ConnectionPool pool;
	public TechDAO(ConnectionPool poolc){
		pool = poolc;
	}
	
	public void create(Tech tech) throws Exception {
        String nom = tech.getNom();
        String prenom = tech.getPrenom();
        String tel = tech.getTel();
        String mdp =tech.getPass();
        String right = tech.getRight();

        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO techniciens ( nom_technicien, prenom_technicien, num_tel_technicien, password_software, rights ) VALUES ( ?, ?, ?, ? ,?)");
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, tel);
        ps.setString(4, mdp);
        ps.setString(5, right);
        ps.execute();
        pool.returnConnection(conn);
        new AuthentificationDAO(pool).create(new Authentification(nom+"."+prenom,"0000"));
    }

    /**
     * Gets information from an existing part
     *
     * @param id of the part to be read
     * @return part information
     * @throws Exception
     */
   
    
    public  String[][] read(int id) throws Exception {
    	
            Connection conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM techniciens WHERE id_technicien = ?");
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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM techniciens");
       
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
        	
        return p;
    }


    /**
     * Updates part information in the stock
     *
     * @param tech to be updated
     * @throws Exception
     */
    public void update_tech(Tech tech) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = pool.getConnection().prepareStatement("UPDATE techniciens SET nom_technicien = ?, prenom_technicien = ?, num_tel_technicien = ?, password_software = ?, rights=? WHERE id_technicien = ?");
        ps.setString(1, tech.getNom());
        ps.setString(2, tech.getPrenom());
        ps.setString(3, tech.getTel());
        ps.setString(4, tech.getPass());
        ps.setString(5, tech.getRight());
        ps.execute();
        pool.returnConnection(conn);
    }

    /**
     * Permanently removes a part from the database
     *
     * @param tech the technician to be removed from the stock
     * @throws Exception
     */
    public void delete(Tech tech) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM techniciens WHERE id_technicien = ?");
        ps.setInt(1, tech.getId());
        ps.execute();
        pool.returnConnection(conn);
    }

}
