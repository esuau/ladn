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

public class Update {

    public Update() throws SQLException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("You selected option 3: Update database record: ");

        System.out.println("Enter part id to update: ");
        String id_piece = userInput.next();

        //retrieve record to update
        DisplayRecord(id_piece);

        System.out.println("Enter part reference: ");
        userInput.nextLine();
        String libelle_piece = userInput.nextLine();

        System.out.println("Enter part provider: ");
        String fabricant = userInput.nextLine();

        System.out.println("Enter available quantity: ");
        String qte_dispo = userInput.next();

        System.out.println("Enter part price: ");
        userInput.nextLine();
        String valeur_piece = userInput.next();

        DBUtilities dbUtilities = new DBUtilities();

        String sql_stmt = "UPDATE pieces SET libelle_piece = '" + libelle_piece + "', fabricant = '" + fabricant + "', qte_dispo = '" + qte_dispo + "', valeur_piece = '" + valeur_piece +"' WHERE id_piece = " + id_piece;

        dbUtilities.ExecuteSQLStatement(sql_stmt);

        System.out.println("The record has successfully been updated.");

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
            System.out.println("The following error has occured: " + ex.getMessage());
        }
    }
}

