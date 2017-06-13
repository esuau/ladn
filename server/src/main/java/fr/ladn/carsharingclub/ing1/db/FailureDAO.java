package fr.ladn.carsharingclub.ing1.db;

import fr.ladn.carsharingclub.ing1.model.Failure;
import fr.ladn.carsharingclub.ing1.model.FailureType;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;

/**
 * DOA object for failures.
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
        logger.info("Creating FailureDAO object...");
        pool = p;
        logger.info("Established link with connection pool " + pool + ".");
    }

    /**
     * Gets the data on all the existing failures in the database.
     *
     * @return the list of all the existing failures.
     * @throws SQLException if a database request issue is encountered.
     */
    public ArrayList<Failure> getAllFailures() throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for all existing failures reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM panne");
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        ArrayList<Failure> failures = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id_panne");
            String intitule = rs.getString("intitule");
            int failureTypeId = rs.getInt("type_panne");
            String instructions = rs.getString("descriptif_protocole");
            int duration = rs.getInt("temps_estime");

            logger.info("Successfully get failure #" + id + " information from database.");
            failures.add(new Failure(id, intitule, this.getFailureType(failureTypeId), instructions, duration));
        }
        return failures;
    }

    /**
     * Gets the data on all the existing failures in the database.
     *
     * @return the list of all the existing failures.
     * @throws SQLException if a database request issue is encountered.
     */
    public ArrayList<Failure> getFailures() throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for all existing failures reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM panne, assoc_reparation_panne, reparer WHERE panne.id_panne = assoc_reparation_panne.id_panne AND reparer.id_reparation = assoc_reparation_panne.id_reparation");
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        ArrayList<Failure> failures = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id_panne");
            String intitule = rs.getString("intitule");
            int failureTypeId = rs.getInt("type_panne");
            String instructions = rs.getString("descriptif_protocole");
            int duration = rs.getInt("temps_estime");

            logger.info("Successfully get failure #" + id + " information from database.");
            failures.add(new Failure(id, intitule, this.getFailureType(failureTypeId), instructions, duration));
        }
        return failures;
    }

    /**
     * Gets a failure type by its identifier.
     *
     * @param id the identifier of the failure type.
     * @return the corresponding fr.ladn.carsharingclub.ing1.definitions.FailureType object.
     * @throws SQLException in case of issue with the database request.
     */
    private FailureType getFailureType(int id) throws SQLException {
        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for all existing failures reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT priorite, libelle FROM type_panne WHERE id_type_panne = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        if (rs.next()) {
            int priorityLevel = rs.getInt("priorite");
            String label = rs.getString("libelle");

            logger.info("Successfully get failure type #" + id + " information from database.");

            return new FailureType(priorityLevel, label);
        }

        logger.error("Database request did not return any failure type.");
        return null;
    }

    /**
     * Gets failures related to a given operation ID.
     *
     * @param operationId the id of the operation.
     * @return the array of corresponding failures.
     * @throws SQLException when a request issue is encountered.
     */
    Failure[] getFailuresByOperation(int operationId) throws SQLException {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");

        logger.info("Preparing SQL statement for all existing failures reading...");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM panne, assoc_reparation_panne, reparer WHERE panne.id_panne = assoc_reparation_panne.id_panne AND reparer.id_reparation = assoc_reparation_panne.id_reparation AND reparer.id_reparation = ?");
        ps.setInt(1, operationId);
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        ArrayList<Failure> failures = new ArrayList<>();

        while (rs.next()) {
            int failureId = rs.getInt("id_panne");
            String name = rs.getString("intitule");
            int failureType = rs.getInt("type_panne");
            String instructions = rs.getString("descriptif_protocole");
            int estimatedTime = rs.getInt("temps_estime");

            failures.add(new Failure(failureId, name, getFailureType(failureType), instructions, estimatedTime));
            logger.info("Successfully get failure failure #" + failureId + " information from database.");

        }

        Failure[] failuresTable = new Failure[failures.size()];
        return failures.toArray(failuresTable);
    }

}
