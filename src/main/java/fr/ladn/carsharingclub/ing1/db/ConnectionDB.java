
package fr.ladn.carsharingclub.ing1.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classic database connection class
 * <p>
 * This class will be replaced by <tt>ConnectionPool</tt> and <tt>ConnectionPoolImpl</tt> to allow connection pooling.
 * </p>
 *
 * @see ConnectionPool
 * @see ConnectionPoolImpl
 */
public class ConnectionDB {

    /**
     * Creates a standalone connection to the MySQL database
     *
     * @return a database connection
     * @throws Exception
     */
    static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/deposit?useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
       String password = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
    }
}
