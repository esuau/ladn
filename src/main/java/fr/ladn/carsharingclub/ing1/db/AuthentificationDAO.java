package fr.ladn.carsharingclub.ing1.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import fr.ladn.carsharingclub.ing1.model.Authentification;
import fr.ladn.carsharingclub.ing1.model.Tech;

public class AuthentificationDAO {
	ConnectionPool pool;
	public AuthentificationDAO(ConnectionPool poolc){
		pool=poolc;
	}
	
	public void create(Authentification a) throws Exception {
        String id = a.get_id();
        String mdp = a.get_mdp();
      
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO authentification ( id,password ) VALUES ( ?, ?)");
        ps.setString(1, id);
        ps.setString(2, mdp);
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
   
    
    public  String[][] read(String id) throws Exception {
    	
            Connection conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM authentification WHERE id = ?");
            ps.setString(1, id);
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
    	
    

    /**
     * Updates part information in the stock
     *
     * @param part to be updated
     * @throws Exception
     */
    public void update_mdp(Authentification a) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = pool.getConnection().prepareStatement("UPDATE authentification SET password = ? WHERE id = ?");
        ps.setString(1,a.get_mdp());
        ps.setString(2, a.get_id());
        ps.execute();
        pool.returnConnection(conn);
    }

    /**
     * Permanently removes a part from the database
     *
     * @param part to be removed from the stock
     * @throws Exception
     */
    public void delete(Authentification a) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM authentification WHERE id = ?");
        ps.setString(1, a.get_id());
        ps.execute();
        pool.returnConnection(conn);
    }

}
