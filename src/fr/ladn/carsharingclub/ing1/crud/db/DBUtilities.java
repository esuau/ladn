package fr.ladn.carsharingclub.ing1.crud.db;

/**
 * Created by evan_suau on 04/01/2017.
 */

import java.sql.*;

public class DBUtilities {

    // database URL
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/deposit?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DBUtilities() throws SQLException {
        // establish connection to database
        try {
            connection = DriverManager.getConnection(DATABASE_URL, "guest", "password");
        } catch (SQLException ex) {
            System.out.println("The following error has occurred: " + ex.getMessage());
        }
    }

    public void DisconnectFromDB() {

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } // end try
        catch (Exception ex) {
            System.out.println("The following error has occurred: " + ex.getMessage());
        } // end catch
    }

    public ResultSet ReadRecords(String sql_stmt) {
        try {

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql_stmt);

            return resultSet;

        } catch (SQLException ex) {
            System.out.println("The following error has occurred: " + ex.getMessage());
        }

        return resultSet;
    }

    public void ExecuteSQLStatement(String sql_stmt) {
        try {
            statement = connection.createStatement();

            statement.executeUpdate(sql_stmt);
        } catch (SQLException ex) {
            System.out.println("The following error has occurred: " + ex.getMessage());
        }
    }
}
