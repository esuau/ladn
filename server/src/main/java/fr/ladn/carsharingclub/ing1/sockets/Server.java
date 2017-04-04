package fr.ladn.carsharingclub.ing1.sockets;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;
import java.net.*;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.xml.ReadXMLFile;
import fr.ladn.carsharingclub.ing1.xml.WriteXMLFile;
import fr.ladn.carsharingclub.ing1.model.Part;

/**
 * Server class.
 * The server communicates with the clients.
 * Its is connected to the database server via the connection pool.
 * In this test version, the server stops after a single connection.
 *
 * @see ConnectionPool
 * @see ServerMain
 */
class Server {
    /**
     * Server constructor.
     * When starting, the server initializes the connection pool.
     * Then, the server waits for a message from a client. The server stops when getting a connection.
     *
     * @see ConnectionPool
     */
    Server() {
        Properties properties = new Properties();

        try {
            FileInputStream input = new FileInputStream("configServer.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String db = properties.getProperty("db");
        String dbDriver = properties.getProperty("dbDriver");
        String dbUsername = properties.getProperty("dbUsername");
        String dbPassword = properties.getProperty("dbPassword");
        int serverPort = Integer.parseInt(properties.getProperty("serverPort"));

        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            Thread connect = new Thread(new Connect(serverSocket));
            connect.start();
             System.out.println("Server started on port " + serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}