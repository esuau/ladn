package fr.ladn.carsharingclub.ing1.sockets;

import fr.ladn.carsharingclub.ing1.model.Part;

/**
 * Starts the client
 */
class ClientMain {
    public static void main(String[] args) {
        Part part = new Part(0, "reference", "provider", 3, 10);
        Client client = new Client(part);
        System.out.println("Data sent.");
    }
}