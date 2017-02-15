package fr.ladn.carsharingclub.ing1.controller;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.db.ConnectionPoolImpl;
import fr.ladn.carsharingclub.ing1.view.AppView;

/**
 *
 */
public class Controller {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPoolImpl();
        AppView view = new AppView(pool);
        view.display();
    }
}
