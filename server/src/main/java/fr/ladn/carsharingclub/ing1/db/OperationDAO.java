package fr.ladn.carsharingclub.ing1.db;

import fr.ladn.carsharingclub.ing1.model.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * DOA object for Operations.
 * Contains SQL statements for CRUD on table <tt>reparer</tt> in database.
 *
 * @see Part
 */
public class OperationDAO {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(OperationDAO.class.getName());

    /** The connection pool. */
    private ConnectionPool pool;

    /**
     * Constructor. References connection pool.
     *
     * @param p The connection pool.
     * @see ConnectionPool
     */
    public OperationDAO(ConnectionPool p) {
        logger.info("Creating OperationDAO object...");
        pool = p;
        logger.info("Established link with connection pool " + pool + ".");
    }


    /**
     * Gets a filtered list of vehicles relatively to their status.
     *
     * @param status the list of selected status.
     * @return the list of corresponding vehicles.
     * @throws SQLException if a database request issue is encountered.
     */
    public ArrayList<Operation> displayVehicleByStatus(String status) throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");
        PreparedStatement ps ;
        ps = conn.prepareStatement("SELECT* FROM reparer WHERE statut_reparation = ? ORDER BY priorite_reparation");
        ps.setString(1, status);
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

       ArrayList<Operation> reparation = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id_reparation");
            String statut = rs.getString("statut_reparation");
            int priorite = rs.getInt("priorite_reparation");
            java.sql.Timestamp dtentre = rs.getTimestamp("date_entree_vehicule");
            java.sql.Timestamp dtsortie = rs.getTimestamp("date_sortie_vehicule");
            int technicien = rs.getInt("id_technicien");
            String vehicule = rs.getString("id_vehicule");
            int place = rs.getInt("id_place");
            
            logger.info("Successfully get part #" + id + " information from database.");
            reparation.add(new Operation(id,statut,priorite,dtentre,dtsortie,new Technician(technicien),new Vehicle(vehicule),place));
        }
        return reparation;

    }


    public void updateOperation(Operation o) throws Exception {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for operation #" + o.getId() + " update...");
        PreparedStatement ps = pool.getConnection().prepareStatement("UPDATE reparer SET id_technicien = ?, id_place = ?, statut_reparation = ?, commentaire = ? WHERE id_reparation = ?");
        ps.setInt(1, o.getTechnician().getId());
        ps.setInt(2, o.getParkingSpace());
        ps.setString(3, o.getStatus().toString());
        ps.setString(4, o.getComment());
        ps.setInt(5, o.getId());

        ps.execute();
        logger.info("Database request has been executed. The operation #" + o.getId() + " has been updated in database.");

        pool.returnConnection(conn);
    }
    
    public void updateWorkflow(Operation o) throws Exception {
        
        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");
        
        logger.info("Preparing SQL statement to update operation #" + o.getId() + " in reparation_histo_temps");
        PreparedStatement ps = pool.getConnection().prepareStatement("UPDATE reparation_histo_temps SET date_fin = ? WHERE id_reparation = ? AND statut = ?");
        ps.setTimestamp(1, o.getDateBS());
        ps.setInt(2, o.getId());
        ps.setString(3, o.getOldStatus().toString());
        System.out.println(o.getOldStatus() + " " + o.getDateBS());
        
        ps.execute();
        logger.info("Database request has been executed. The operation #" + o.getId() + " has been updated in database > reparation_histo_temps.");
    }
    
    public void createWorkflow(Operation o) throws Exception {
        int id = o.getId();
        String status = o.getStatus().toString();
        java.sql.Timestamp date = o.getDateBS();
        int parkingSpace = o.getParkingSpace();
        
        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");
        
        logger.info("Preparing SQL statement for operation #" + o.getId() + " new status creation in reparation_histo_temps...");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO reparation_histo_temps ( id_reparation, statut, date_debut, id_place ) VALUES ( ?, ?, ?, ? )");
        ps.setInt(1, id);
        ps.setString(2, status);
        ps.setTimestamp(3, date);
        ps.setInt(4, parkingSpace);
        ps.execute();
        logger.info("Database request has been executed. A new status for operation #" + o.getId() + " has been created in database > reparation_histo_temps.");

        pool.returnConnection(conn);
    }

    /**
     * Gets an empty parking space.
     *
     * @return the identifier of an empty parking spot.
     * @throws SQLException in case of issue with the SQL request.
     */
    public Integer readEmptySpace() throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement to get empty space from database...");
        PreparedStatement ps = pool.getConnection().prepareStatement("SELECT id_place FROM place WHERE id_place NOT IN (SELECT id_place FROM reparer) AND id_place != -1 LIMIT 1");
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been executed. The empty space has been returned.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        if (rs.next()) {
            Integer idEmptySpace = rs.getInt("id_place");
            logger.info("Successfully get empty place #" + idEmptySpace + " from database.");
            return idEmptySpace;
        } else {
            logger.error("Database request did not return any space information.");
            return null;
        }
    }
    
    /**
     * Creates a new operation in database.
     * 
     * @return the identifier of the newly created operation.
     * @throws SQLException in case of issue with the SQL request.
     */
    public int createOperation(Operation operation) throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        // Get a free parking space to store the added vehicle
        int spaceId = this.readEmptySpace();

        logger.info("Preparing SQL statement to create operation in database...");
        PreparedStatement ps = pool.getConnection().prepareStatement("INSERT INTO reparer (id_place, id_vehicule, statut_reparation, priorite_reparation, date_entree_vehicule) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, spaceId);
        ps.setInt(2, operation.getVehicle().getId());
        ps.setString(3, OperationStatus.DIAGNOSED.toString());
        ps.setInt(4, operation.getPriority().getPriorityLevel());
        ps.setTimestamp(5, operation.getDateEntry());
        ps.executeUpdate();
        logger.info("The database request has been executed.");

        ResultSet rs = ps.getGeneratedKeys();

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");
        
        if (rs.next()) { 
        	int lastInsertedId = rs.getInt(1);
            logger.info("Successfully created new operation with id #" + lastInsertedId + ".");
            return lastInsertedId;
        }

        logger.error("Database request did not return any information about the newly created operation.");
        return -1;

    }
    
}
