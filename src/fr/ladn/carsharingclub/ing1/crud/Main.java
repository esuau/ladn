package fr.ladn.carsharingclub.ing1.crud;

/**
 * Created by evan_suau on 04/01/2017.
 */

import fr.ladn.carsharingclub.ing1.crud.operations.Create;
import fr.ladn.carsharingclub.ing1.crud.operations.Delete;
import fr.ladn.carsharingclub.ing1.crud.operations.Read;
import fr.ladn.carsharingclub.ing1.crud.operations.Update;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        DisplayMenu();
    }

    public static void DisplayMenu() throws SQLException {
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;

        // Display menu graphics
        System.out.println("*****************************************");
        System.out.println("|       L'ADN | JAVA CRUD CONSOLE       |");
        System.out.println("*****************************************");
        System.out.println("| Options:                              |");
        System.out.println("|        1. Create Database Records     |");
        System.out.println("|        2. Read Database Records       |");
        System.out.println("|        3. Update Database Records     |");
        System.out.println("|        4. Delete Database Records     |");
        System.out.println("|        5. Exit                        |");
        System.out.println("*****************************************");

        System.out.print("Select option: ");

        READ_MENU = userInput.next();

//display menu based on user selection
        switch (READ_MENU) {
            case "1":
                new Create();
                break;
            case "2":
                new Read();
                break;
            case "3":
                new Update();
                break;
            case "4":
                new Delete();
                break;
            case "5":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }
    }
}
