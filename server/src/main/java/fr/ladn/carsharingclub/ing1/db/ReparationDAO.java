package fr.ladn.carsharingclub.ing1.db;

import fr.ladn.carsharingclub.ing1.model.Part;
import fr.ladn.carsharingclub.ing1.model.Reparation;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
     * Creates a part in the database.
     * Part appearing into the database are considered available in the stock.
     *
     * @param part to add to the stock
     * @throws Exception if connection issue encountered
     */
    public void create(Reparation rep) throws Exception {
        String reference = part.getReference();
        String provider = part.getProvider();
        int availableQuantity = part.getAvailableQuantity();
        float price = part.getPrice();

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for part #" + part.getId() + " creation...");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Pieces ( libelle_piece, fabricant, qte_dispo, valeur_piece ) VALUES ( ?, ?, ?, ? )");
        ps.setString(1, reference);
        ps.setString(2, provider);
        ps.setInt(3, availableQuantity);
        ps.setFloat(4, price);
        ps.execute();
        logger.info("Database request has been executed. The part #" + part.getId() + " has been created in database.");

        pool.returnConnection(conn);
    }

    /**
     * Gets information from an existing part by its ID.
     *
     * @param id of the part to be read.
     * @return the information on the part.
     * @throws Exception if a connection issue is encountered.
     */
    public Part read(int id) throws Exception {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for part #" + id + " reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pieces WHERE id_piece = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        if (rs.next()) {
            String reference = rs.getString("libelle_piece");
            String provider = rs.getString("fabricant");
            int availableQuantity = rs.getInt("qte_dispo");
            float price = rs.getFloat("valeur_piece");

            logger.info("Successfully get part #" + id + " information from database.");
            return new Part(id, reference, provider, availableQuantity, price);
        } else {
            logger.error("Database request did not return any information. The part #" + id + " may not exist.");
            return null;
        }
    }

    /**
     * Gets the data on all the existing parts in the database.
     *
     * @return the list of all the existing parts.
     * @throws SQLException if a database request issue is encountered.
     */
    public ArrayList<Part> readAll() throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for all existing parts reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pieces");
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        ArrayList<Part> parts = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id_piece");
            String reference = rs.getString("libelle_piece");
            String provider = rs.getString("fabricant");
            int availableQuantity = rs.getInt("qte_dispo");
            float price = rs.getFloat("valeur_piece");

            logger.info("Successfully get part #" + id + " information from database.");
            parts.add(new Part(id, reference, provider, availableQuantity, price));
        }
        return parts;
    }

    /**
     * Updates part information in the stock.
     *
     * @param part to be updated
     * @throws Exception if connection issue encountered
     */
    public void update(Part part) throws Exception {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for part #" + part.getId() + " update...");
        PreparedStatement ps = pool.getConnection().prepareStatement("UPDATE Pieces SET libelle_piece = ?, fabricant = ?, qte_dispo = ?, valeur_piece = ? WHERE id_piece = ?");
        ps.setString(1, part.getReference());
        ps.setString(2, part.getProvider());
        ps.setInt(3, part.getAvailableQuantity());
        ps.setFloat(4, part.getPrice());
        ps.setInt(5, part.getId());
        ps.execute();
        logger.info("Database request has been executed. The part #" + part.getId() + " has been updated in database.");

        pool.returnConnection(conn);
    }

    /**
     * Permanently removes a part from the database.
     *
     * @param part to be removed from the stock
     * @throws Exception if connection issue encountered
     */
    public void delete(Part part) throws Exception {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        PreparedStatement ps = conn.prepareStatement("DELETE FROM Pieces WHERE id_piece = ?");
        ps.setInt(1, part.getId());
        ps.execute();
        logger.info("Database request has been executed. The part #" + part.getId() + " has been removed from database");

        pool.returnConnection(conn);
    }
}
