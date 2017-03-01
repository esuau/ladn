package fr.ladn.carsharingclub.ing1.controller;

import javax.swing.JFrame;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.db.ConnectionPoolImpl;
import fr.ladn.carsharingclub.ing1.view.AppView;

/**
 *
 */
public class Controller {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPoolImpl();
        JFrame frame = new JFrame("MAINTENANCE WORLD");
        AppView view = new AppView(pool,frame);
        view.display();
    }
}
