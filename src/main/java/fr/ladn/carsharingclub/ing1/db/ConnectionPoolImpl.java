package fr.ladn.carsharingclub.ing1.db;

/**
 * Created by evan_suau on 09/01/2017.
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ConnectionPoolImpl implements ConnectionPool {
    final int MAX_POOL_SIZE = 10;
    ArrayList<Connection> connectionsList = new ArrayList<Connection>();

    public ConnectionPoolImpl() {
        initialize();
    }

    private void initialize() {
        while (!isFull()) {
            System.out.println("Connection Pool is NOT full. Proceeding with adding new connections");
            // Adding new connection instance until the pool is full
            connectionsList.add(createConnection());
        }
        System.out.println("Connection Pool is full. " + connectionsList.size() + " connections created");
    }

    private synchronized boolean isFull() {
        return !(connectionsList.size() < MAX_POOL_SIZE);
    }

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

    public synchronized void returnConnection(Connection connection) {
        //Adding the connection from the client back to the connection pool
        connectionsList.add(connection);
    }

    public synchronized int getSize() {
        return connectionsList.size();
    }

}
