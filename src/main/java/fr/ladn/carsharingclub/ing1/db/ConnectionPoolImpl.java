package fr.ladn.carsharingclub.ing1.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Brings essential utilities for connection pooling
 * <br>
 * Implements ConnectionPool interface
 *
 * @see ConnectionPool
 */
public class ConnectionPoolImpl implements ConnectionPool {
    private final int MAX_POOL_SIZE = 10;
    private ArrayList<Connection> connectionsList = new ArrayList<>();

    private final static Logger logger = Logger.getLogger(ConnectionPoolImpl.class.getName());

    public ConnectionPoolImpl() {
        logger.info("Initializing connection pool...");
        initialize();
    }

    /**
     * Initialize all connections depending on a given size.
     */
    private void initialize() {
        while (!isFull()) {
            logger.info("Adding new connection...");
            connectionsList.add(createConnection());
        }
        logger.info("Connection pool filled successfully. " + connectionsList.size() + "/" + MAX_POOL_SIZE + " connections created.");
    }

    /**
     * Indicates the filling state of the pool.
     *
     * @return true if the number of connections reaches the pool size
     */
    private synchronized boolean isFull() {
        return !(connectionsList.size() < MAX_POOL_SIZE);
    }

    /**
     * Creates a connection to the database.
     * <p>
     * This function uses JDBC API to connect to a MySQL database.
     * Database server information and Credentials are located in a file <tt>dbconfig.properties</tt> to be easily modified.
     * </p>
     *
     * @return a new connection in the pool
     */
    private Connection createConnection() {

        Properties properties = new Properties();

        try {
            logger.info("Loading dbconfig.properties...");
            FileInputStream input = new FileInputStream("dbconfig.properties");
            properties.load(input);
            input.close();
            logger.info("Configuration file dbconfig.properties successfully loaded.");
        } catch (IOException e) {
            logger.error("IOException: " + e.getMessage());
        }

        Connection connection;

        String url = properties.getProperty("dbHost");
        String driver = properties.getProperty("dbDriver");
        String user = properties.getProperty("dbUsername");
        String password = properties.getProperty("dbPassword");

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            logger.info("New connection successfully added: " + connection);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException: " + e.getMessage());
            return null;
        }
        return connection;
    }

    /**
     * Calls a connection from the pool in order to be used by a method.
     *
     * @return a connection of the pool
     */
    public synchronized Connection getConnection() {
        Connection connection = null;

        if (connectionsList.size() > 0) {
            connection = connectionsList.get(0);
            connectionsList.remove(0);
        }
        return connection;
    }

    /**
     * Give back the connection to the pool.
     * <p>
     * Once a connection has been used, it is reintegrated inside the connection pool for further reuse.
     * </p>
     *
     * @param connection to be reintegrated within the pool
     */
    public synchronized void returnConnection(Connection connection) {
        connectionsList.add(connection);
    }

    /**
     * Gets the size of the connection pool
     *
     * @return the maximum number of connections in the pool
     */
    public synchronized int getSize() {
        return connectionsList.size();
    }

}
