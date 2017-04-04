package fr.ladn.carsharingclub.ing1.controller;

import fr.ladn.carsharingclub.ing1.view.AppView;

/**
 * Starts the client
 */
class Controller {

    /**
     * Generates the app view.
     * Initializes the connection to the server.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        AppView app = new AppView();
        app.display();
        // TODO Implement data transmission with class Client
    }
}