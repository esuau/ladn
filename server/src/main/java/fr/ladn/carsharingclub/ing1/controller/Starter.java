package fr.ladn.carsharingclub.ing1.controller;

import fr.ladn.carsharingclub.ing1.sockets.Server;

/**
 * Starts the server
 */
public class Starter {
    public static void main(String[] args) {
        new Server();
    }
}