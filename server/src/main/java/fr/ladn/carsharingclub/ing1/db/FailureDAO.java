package fr.ladn.carsharingclub.ing1.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.model.Failure;
import fr.ladn.carsharingclub.ing1.model.Part;

/**
 * DOA object for Failure.
 * Contains SQL statements for CRUD on table <tt>reparer</tt> in database.
 *
 * @see Failure
 */
public class FailureDAO {

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
    public FailureDAO(ConnectionPool p) {
        logger.info("Creating OperationDAO object...");
        pool = p;
        logger.info("Established link with connection pool " + pool + ".");
    }
    
    /**
     * Gets the data on all the existing failures in the database.
     *
     * @return the list of all the existing failures.
     * @throws SQLException if a database request issue is encountered.
     */
    public ArrayList<Failure> readAll() throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for all existing failures reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM panne");
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        ArrayList<Failure> failure = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id_panne");
            String intitule = rs.getString("intitule");

            logger.info("Successfully get failure #" + id + " information from database.");
            failure.add(new Failure(id,intitule,null,"",null));
        }
        return failure;
    }

}
