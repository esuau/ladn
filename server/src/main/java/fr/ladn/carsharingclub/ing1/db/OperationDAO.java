package fr.ladn.carsharingclub.ing1.db;

import fr.ladn.carsharingclub.ing1.model.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        PreparedStatement ps ;
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
        PreparedStatement ps = pool.getConnection().prepareStatement("UPDATE reparation SET comment = ?, status = ? WHERE id_reparation = ?");
        ps.setString(1, o.getComment());
        //ps.setString(2, o.getStatus().getStep());
        ps.setInt(3, o.getId());
        PreparedStatement ps2 = pool.getConnection().prepareStatement("UPDATE reparation_histo_temps SET statut = ?, date_debut = ?, date_fin = ? WHERE id_reparation = ?");
        //ps2.setString(1, o.getStatus().getStep());
        
        
        // TODO update availale quantity
        ps.execute();
        logger.info("Database request has been executed. The operation #" + o.getId() + " has been updated in database.");

        pool.returnConnection(conn);
    }
    
    
}
