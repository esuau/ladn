package fr.ladn.carsharingclub.ing1.db;

import fr.ladn.carsharingclub.ing1.model.Vehicle;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DAO object for Vehicles.
 * Contains SQL statements for CRUD in database.
 *
 * @see Vehicle
 */
public class VehicleDAO {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(VehicleDAO.class.getName());

    /** The connection pool. */
    private ConnectionPool pool;

    /**
     * Constructor. References connection pool.
     *
     * @param p The connection pool.
     * @see ConnectionPool
     */
    public VehicleDAO(ConnectionPool p) {
        logger.info("Instantiating Vehicle DAO object...");
        pool = p;
        logger.info("Established link with connection pool " + pool + ".");
    }

    /**
     * Gets a vehicle related to a given operation.
     *
     * @param operationId the ID of the operation.
     * @return the corresponding vehicle.
     * @throws SQLException when a request issue is encountered.
     */
    public Vehicle getVehiclesByOperation(int operationId) throws SQLException {

        Connection conn = pool.getConnection();

        logger.info("Preparing SQL statement vehicle corresponding to operation #" + operationId + "...");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM vehicule WHERE id_vehicule = (SELECT id_vehicule FROM reparer WHERE id_reparation = ?)");
        ps.setInt(1, operationId);
        ResultSet rs = ps.executeQuery();
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);

        if (rs.next()) {
            int vehicleId = rs.getInt("id_vehicule");
            String registrationNumber = rs.getString("immatriculation");
            String brand = rs.getString("marque");
            String manufacturer = rs.getString("constructeur");
            String version = rs.getString("version");
            logger.info("Successfully get part #" + vehicleId + " information from database.");
            return new Vehicle(vehicleId, registrationNumber, brand, manufacturer, version);
        } else {
            logger.error("Database request did not return any information.");
            return null;
        }
    }

}
