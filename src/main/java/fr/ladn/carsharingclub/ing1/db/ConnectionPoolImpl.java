package fr.ladn.carsharingclub.ing1.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Brings essential utilities for connection pooling
 * <br>
 * Implements ConnectionPool interface
 *
 * @see ConnectionPool
 */
public class ConnectionPoolImpl implements ConnectionPool {
    final int MAX_POOL_SIZE = 10;
    ArrayList<Connection> connectionsList = new ArrayList<Connection>();

    public ConnectionPoolImpl() {
        initialize();
    }

    /**
     * Initialize all connections depending on a given size.
     */
    private void initialize() {
        while (!isFull()) {
            System.out.println("Connection Pool is NOT full. Proceeding with adding new connections");
            // Adding new connection instance until the pool is full
            connectionsList.add(createConnection());
        }
        System.out.println("Connection Pool is full. " + connectionsList.size() + " connections created");
    }

    /**
     * Indicates the filling state of the pool.
     * @return true if the number of connections reaches the pool size
     */
    private synchronized boolean isFull() {
        return !(connectionsList.size() < MAX_POOL_SIZE);
    }

    /**
     * Creates a connection to the database.
     * <p>
     *     This function uses JDBC API to connect to a MySQL database.
     *     <br>
     *     Credentials and other parameters are located in a file <tt>dbconnection.properties</tt> to be easily modified.
     * </p>
     * @return connection as created connection in the pool
     */
    private Connection createConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/deposit?useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "guest";
        String password = "password";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection: " + connection);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e);
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e);
            return null;
        }
        return connection;
    }

    /**
     * Calls a connection from the pool in order to be used by a method.
     * @return a connection of the pool
     */
    public synchronized Connection getConnection() {
        Connection connection = null;

        //Check if there is a connection available. There are times when all the connections in the pool may be used up
        if (connectionsList.size() > 0) {
            connection = connectionsList.get(0);
            connectionsList.remove(0);
        }
        //Giving away the connection from the connection pool
        return connection;
    }

    /**
     * Give back the connection to the pool.
     * <p>
     *     Once a connection has been used, it is reintegrated inside the connection pool for further reuse.
     * </p>
     * @param connection to be reintegrated within the pool
     */
    public synchronized void returnConnection(Connection connection) {
        connectionsList.add(connection);
    }

    /**
     * Gets the size of the connection pool
     * @return the maximum number of connections in the pool
     */
    public synchronized int getSize() {
        return connectionsList.size();
    }

}
