package fr.ladn.carsharingclub.ing1.db;

import fr.ladn.carsharingclub.ing1.model.Part;
import fr.ladn.carsharingclub.ing1.model.Reparation;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * DOA Object for Part.
 * Contains SQL statements for CRUD in database.
 *
 * @see Part
 */
public class ReparationDAO {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(ReparationDAO.class.getName());

    /** The connection pool. */
    private ConnectionPool pool;

    /**
     * Constructor. References connection pool.
     *
     * @param p The connection pool.
     * @see ConnectionPool
     */
    public ReparationDAO(ConnectionPool p) {
        logger.info("Creating PartDAO object...");
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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM reparer WHERE statut_reparation IN ('?','?','?','?')");
        // Include the selected status in the request.
        Iterator<String> it = status.iterator();
        int i = 1;
        // String s="(\'\'";
        while (it.hasNext()) {
            String s = it.next();
            ps.setString(i, s);
            i = i + 1;
            //s = s+", \'"+it.next()+"\'";
        }
        if (i < 5) {
            for (int j = i; j < 5; j++) {
                ps.setString(j, "");
            }
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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pieces");
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
}
