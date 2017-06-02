package fr.ladn.carsharingclub.ing1.db;

import fr.ladn.carsharingclub.ing1.model.Part;

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
public class PartDAO {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(PartDAO.class.getName());

    /** The connection pool. */
    private ConnectionPool pool;

    /**
     * Constructor. References connection pool.
     *
     * @param p The connection pool.
     * @see ConnectionPool
     */
    public PartDAO(ConnectionPool p) {
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
    public void create(Part part) throws Exception {
        String reference = part.getReference();
        String provider = part.getProvider();
        float price = part.getPrice();

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for part #" + part.getId() + " creation...");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO piece ( libelle_piece, fabricant, valeur_piece ) VALUES ( ?, ?, ? )");
        ps.setString(1, reference);
        ps.setString(2, provider);
        ps.setFloat(3, price);
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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM piece WHERE id_piece = ?");
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
    
    public ArrayList<Part> failurePartsReadAll(int idFailure) throws Exception {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for id operation #" + idFailure + " reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT id_piece, qte_necessaire FROM necessiter WHERE id_panne = ?");
        ps.setInt(1, idFailure);
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");
        
        ArrayList<Part> parts = new ArrayList<>();

        while (rs.next()) {
            int idPart = rs.getInt("id_piece");
            int qty = rs.getInt("qte_necessaire");
            logger.info("Successfully get part #" + idPart + " information from database.");
            parts.add(new Part(idPart, qty));
        }
        return parts;
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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM piece");
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
        PreparedStatement ps = pool.getConnection().prepareStatement("UPDATE piece SET libelle_piece = ?, fabricant = ?, valeur_piece = ? WHERE id_piece = ?");
        ps.setString(1, part.getReference());
        ps.setString(2, part.getProvider());
        ps.setFloat(3, part.getPrice());
        ps.setInt(4, part.getId());
        // TODO update availale quantity
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

        PreparedStatement ps = conn.prepareStatement("DELETE FROM piece WHERE id_piece = ?");
        ps.setInt(1, part.getId());
        ps.execute();
        logger.info("Database request has been executed. The part #" + part.getId() + " has been removed from database");

        pool.returnConnection(conn);
    }
}
