package fr.ladn.carsharingclub.ing1.crud.operations;

/**
 * Created by evan_suau on 04/01/2017.
 */

import fr.ladn.carsharingclub.ing1.crud.Main;
import fr.ladn.carsharingclub.ing1.crud.db.DBUtilities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {

    public Delete() throws SQLException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("You selected option 4: Delete database record: ");

        System.out.println("Enter part id to delete: ");
        String id_piece = userInput.next();

        //retrieve record to update
        DisplayRecord(id_piece);

        System.out.println("Enter Y to confirm deletion: ");
        String confirm_delete = userInput.next();

        if ("Y".equals(confirm_delete)) {
            DBUtilities dbUtilities = new DBUtilities();

            String sql_stmt = "DELETE FROM pieces WHERE id_piece = " + id_piece;

            dbUtilities.ExecuteSQLStatement(sql_stmt);

            System.out.println("The record has successfully been deleted");
        }

        Main.DisplayMenu();
    }

    private void DisplayRecord(String id_piece) throws SQLException {
        try {
            DBUtilities dbUtilities = new DBUtilities();

            String sql_stmt = "SELECT * FROM pieces WHERE id_piece = " + id_piece;
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
        }
    }
}