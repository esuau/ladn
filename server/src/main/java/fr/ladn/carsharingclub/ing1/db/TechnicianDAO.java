package fr.ladn.carsharingclub.ing1.db;

import fr.ladn.carsharingclub.ing1.model.Technician;
import fr.ladn.carsharingclub.ing1.model.TechnicianRights;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TechnicianDAO {
	/** The logger. */
    private final static Logger logger = Logger.getLogger(TechnicianDAO.class.getName());

    /** The connection pool. */
    private ConnectionPool pool;

    /**
     * Constructor. References connection pool.
     *
     * @param p The connection pool.
     * @see ConnectionPool
     */
    public TechnicianDAO(ConnectionPool p) {
        logger.info("Creating TechnicianDAO object...");
        pool = p;
        logger.info("Established link with connection pool " + pool + ".");
    }
    
    /**
     * Gets information from an existing technician by its ID.
     *
     * @param id of the technician to be read.
     * @return the information on the technician.
     * @throws Exception if a connection issue is encountered.
     */
    public Technician read(int id) throws Exception {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for part #" + id + " reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM technicien WHERE id_technicien = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        if (rs.next()) {
            String firstName = rs.getString("prenom");
            String lastName = rs.getString("nom");
            String phone_number = rs.getString("num_tel");
            String password = rs.getString("mot_de_passe");
            String rights_inter = rs.getString("rights");            
            TechnicianRights rights = TechnicianRights.valueOf(rights_inter);
            logger.info("Successfully get technician #" + id + " information from database.");
            return new Technician(id, firstName, lastName, phone_number, password, rights);
        } else {
            logger.error("Database request did not return any information. The Technician #" + id + " may not exist.");
            return null;
        }
    }

    public ArrayList<Technician> readAll() throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for all existing technicians reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM technicien");
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        ArrayList<Technician> technicians = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id_technicien");
        	String firstName = rs.getString("prenom");
            String lastName = rs.getString("nom");
            String phone_number = rs.getString("num_tel");
            String password = rs.getString("mot_de_passe");
            String rights_inter = rs.getString("rights");            
            TechnicianRights rights = TechnicianRights.valueOf(rights_inter);

            logger.info("Successfully get technician #" + id + " information from database.");
            technicians.add(new Technician(id, firstName, lastName, phone_number, password, rights));
        }
        return technicians;
    }
    
    

    
    
}
