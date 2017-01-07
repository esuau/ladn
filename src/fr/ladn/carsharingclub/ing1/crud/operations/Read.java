package fr.ladn.carsharingclub.ing1.crud.operations;

/**
 * Created by evan_suau on 04/01/2017.
 */

import fr.ladn.carsharingclub.ing1.crud.Main;
import fr.ladn.carsharingclub.ing1.crud.db.DBUtilities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Read {

    public Read() throws SQLException {
        System.out.println("You selected option 2: Read database record\n");
        DisplayResults();
    }

    private void DisplayResults() throws SQLException {
        try {
            DBUtilities dbUtilities = new DBUtilities();

            String sql_stmt = "SELECT * FROM pieces";
            ResultSet resultSet = dbUtilities.ReadRecords(sql_stmt);

            // process query results
            if (resultSet.next()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                System.out.print("Database Records Listing\n");

                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("%-20s\t", metaData.getColumnName(i));
                }
                System.out.println();

                do {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        System.out.printf("%-20s\t", resultSet.getObject(i));
                    }
                    System.out.println();
                } while (resultSet.next());

                System.out.println();

            } else {
                System.out.println("No database records found\n");
            }

            //close db connection
            dbUtilities.DisconnectFromDB();
        } catch (SQLException ex) {
            System.out.println("The following error has occurred: " + ex.getMessage());
        } finally {
            Main.DisplayMenu();
        }
    }
}
