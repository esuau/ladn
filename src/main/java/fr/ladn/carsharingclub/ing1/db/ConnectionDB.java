
package fr.ladn.carsharingclub.ing1.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/deposit?useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "guest";
        String password = "password";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
    }
}
