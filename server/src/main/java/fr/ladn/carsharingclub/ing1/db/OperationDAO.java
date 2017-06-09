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
    public ArrayList<Reparation> displayVehicleByStatus(ArrayList<String> status) throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");
        PreparedStatement ps;
        switch (status.size()) {
            case 4:
                ps = conn.prepareStatement("SELECT * FROM reparer WHERE statut_reparation='?' OR statut_reparation='?' OR statut_reparation='?' OR statut_reparation='?'");
                break;
            case 3:
                ps = conn.prepareStatement("SELECT * FROM reparer WHERE statut_reparation='?' OR statut_reparation='?' OR statut_reparation='?'");
                break;
            case 2:
                ps = conn.prepareStatement("SELECT * FROM reparer WHERE statut_reparation='?' OR statut_reparation='?'");
                break;
            default:
                ps = conn.prepareStatement("SELECT * FROM reparer WHERE statut_reparation='?'");
                break;
        }

        // Creating the status to display.
        Iterator<String> it = status.iterator();
        int i = 1;

        // String s="(\'\'";
        for (int j = i; j < 5; j++) {
            ps.setString(j, "");
        }

        //ps.setString(1, s);
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        ArrayList<Reparation> reparation = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id_reparation");
            String statut = rs.getString("statut_reparation");
            int priorite = rs.getInt("priorite_reparation");
            java.sql.Date dtentre = rs.getDate("Date_entrée_vehicule");
            java.sql.Date dtsortie = rs.getDate("date_sortie");
            int technicien = rs.getInt("id_technicien");
            int panne = rs.getInt("id_panne");
            String vehicule = rs.getString("id_vehicule");
            int place = rs.getInt("id_place");

            logger.info("Successfully get part #" + id + " information from database.");
            reparation.add(new Reparation(id, statut, priorite, dtentre, dtsortie, technicien, panne, vehicule, place));
        }
        return reparation;
    }

    /**
     * Gets the data on all the existing operations in the database.
     *
     * @return the list of all the existing operations.
     * @throws SQLException if a database request issue is encountered.
     */
    public ArrayList<Reparation> getOperations() throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for all existing parts reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM reparer");
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        ArrayList<Reparation> reparation = new ArrayList<>();
        //java.sql.Date dtentre = new java.sql.Date(System.currentTimeMillis()) ;

        while (rs.next()) {
            int id = rs.getInt("id_reparation");
            String statut = rs.getString("statut_reparation");
            int priorite = rs.getInt("priorite_reparation");
            java.sql.Date dtentre = rs.getDate("date_entrée_vehicule");
            java.sql.Date dtsortie = rs.getDate("date_sortie");
            int technicien = rs.getInt("id_technicien");
            int panne = rs.getInt("id_panne");
            String vehicule = rs.getString("id_vehicule");
            int place = rs.getInt("id_place");

            logger.info("Successfully get part #" + id + " information from database.");
            reparation.add(new Reparation(id, statut, priorite, dtentre, dtsortie, technicien, panne, vehicule, place));
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
    public Integer readEmptySpace() throws Exception {

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
     * @return the identifier of an empty parking spot.
     * @throws SQLException in case of issue with the SQL request.
     */
    public int createOperation(Operation operation) throws Exception {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");
        
        int spaceId = this.readEmptySpace();

        logger.info("Preparing SQL statement to create operation in database...");
        PreparedStatement ps = pool.getConnection().prepareStatement("INSERT INTO reparer (id_place, id_vehicle, statut_reparation, priorite_reparation, date_entree_vehicule) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, spaceId);
        ps.setInt(2, operation.getVehicle().getId());
        ps.setString(3, OperationStatus.DIAGNOSED.toString());
        ps.setInt(4, operation.getPriority().getPriorityLevel());
        ps.setTimestamp(5, operation.getDateEntry());
        ps.executeUpdate();
        logger.info("Database request has been executed.");

        ResultSet rs = ps.getGeneratedKeys();
        
        if (rs.next()) { 
        	int lastInsertedId = rs.getInt(1);
        }
        
        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        if (rs.next()) {
            Integer idEmptySpace = rs.getInt("id_place");
            logger.info("Successfully get empty place #" + idEmptySpace + " from database.");
            return idEmptySpace;
        } else {
            logger.error("Database request did not return any space information.");
            return -1;
        }
    }
    
}
