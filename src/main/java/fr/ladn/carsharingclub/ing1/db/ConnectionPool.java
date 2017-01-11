package fr.ladn.carsharingclub.ing1.db;

import java.sql.Connection;

/**
 * Created by evan_suau on 09/01/2017.
 */
public interface ConnectionPool {
    public Connection getConnection();

    public void returnConnection(Connection connection);

    public int getSize();
}
