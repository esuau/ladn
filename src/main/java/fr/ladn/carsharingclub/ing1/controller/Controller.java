package fr.ladn.carsharingclub.ing1.controller;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.db.ConnectionPoolImpl;
import fr.ladn.carsharingclub.ing1.view.AppView;
import fr.ladn.carsharingclub.ing1.view.Authentification_view;
import javax.swing.JFrame;

/**
 *
 */
public class Controller {
    public static void main(String[] args) {
          ConnectionPool pool = new ConnectionPoolImpl();
        JFrame frame = new JFrame("Authentification");
        new AppView(pool,frame).display();
        //new  Authentification_view(pool);
        
    }
}
