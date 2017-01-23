package fr.ladn.carsharingclub.ing1.db;

import java.sql.Connection;

/**
 * Describes basic method for connection pooling
 * <br>
 * Implemented by ConnectionPoolImpl
 * @see ConnectionPoolImpl
 */
public interface ConnectionPool {

    /**
     * Calls a connection from the pool
     *
     * @return an available connection in the pool
     */
    public Connection getConnection();

    /**
     * Gets connection back to the pool
     * @param connection to be reintegrated within the pool
     */
    public void returnConnection(Connection connection);

    /**
     * Gets the size of the connection pool
     * @return the maximum number of connections in the pool
     */
    public int getSize();
}
