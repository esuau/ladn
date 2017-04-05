package fr.ladn.carsharingclub.ing1.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Brings essential utilities for connection pooling.
 * Allows performance enhancement when accessing database.
 */
public class ConnectionPool {

    /** Maximum connection possible in connection pool. */
    private static final int MAX_POOL_SIZE = 10;

    /** The list of available opened connections. */
    private ArrayList<Connection> connectionsList = new ArrayList<>();

    /**
     * The constructor initializes the filling of the connection pool.
     */
    public ConnectionPool() {
        initialize();
    }

    /**
     * Initializes all connections depending on a given size.
     * Adds new connection instances until the pool is full.
     */
    private void initialize() {
        while (!isFull()) {
            System.out.println("Connection Pool is NOT full. Proceeding with adding new connections");
            connectionsList.add(createConnection());
        }
        System.out.println("Connection Pool is full. " + connectionsList.size() + " connections created");
    }

    /**
     * Indicates the filling state of the pool.
     *
     * @return true if the number of connections reaches the pool size.
     */
    private synchronized boolean isFull() {
        return !(connectionsList.size() < MAX_POOL_SIZE);
    }

    /**
     * Creates a connection to the database.
     * This function uses JDBC API to connect to a MySQL database.
     * Credentials and other parameters are located in a file <tt>dbconnection.properties</tt> to be easily modified.
     *
     * @return an opened database connection ready to be added to the connection pool list.
     */
    private Connection createConnection() {
        Properties properties = new Properties();

        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("configServer.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection;
        String url = properties.getProperty("db");
        String driver = properties.getProperty("dbDriver");
        String user = properties.getProperty("dbUsername");
        String password = properties.getProperty("dbPassword");

        try {
            Class.forName(driver);
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
     * First, checks if there is a connection available.
     * There are times when all the connections in the pool may be used up.
     * Then it allocates the connection from the connection pool.
     *
     * @return a connection from the pool
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
     * Once a connection has been used, it is reintegrated inside the connection pool for further reuse.
     *
     * @param connection The connection to be reintegrated within the pool.
     */
    public synchronized void returnConnection(Connection connection) {
        connectionsList.add(connection);
    }

    /**
     * Gets the size of the connection pool.
     *
     * @return the maximum number of connections in the pool.
     */
    public synchronized int getSize() {
        return connectionsList.size();
    }

}
