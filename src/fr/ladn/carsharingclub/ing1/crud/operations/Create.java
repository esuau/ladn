package fr.ladn.carsharingclub.ing1.crud.operations;

/**
 * Created by evan_suau on 04/01/2017.
 */

import fr.ladn.carsharingclub.ing1.crud.Main;
import fr.ladn.carsharingclub.ing1.crud.db.DBUtilities;

import java.sql.SQLException;
import java.util.Scanner;

public class Create {

    public Create() throws SQLException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("You selected option 1: Create database record: ");

        System.out.println("Enter part reference: ");
        String libelle_piece = userInput.nextLine();

        System.out.println("Enter provider: ");
        String fabricant = userInput.nextLine();

        System.out.println("Enter available quantity: ");
        String qte_dispo = userInput.next();

        System.out.println("Enter part price: ");
        userInput.nextLine();
        String valeur_piece = userInput.next();

        DBUtilities dbUtilities = new DBUtilities();

        // Tables use AUTO_INCREMENT
        String sql_stmt = "INSERT INTO pieces (libelle_piece, fabricant, qte_dispo, valeur_piece) VALUES ('" + libelle_piece + "','" + fabricant + "','" + qte_dispo + "','" + valeur_piece + "')";

        dbUtilities.ExecuteSQLStatement(sql_stmt);

        System.out.println("The record has successfully been created.");

        Main.DisplayMenu();
    }
}
