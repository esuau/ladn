package fr.ladn.carsharingclub.ing1.sockets;

import java.io.*;
import java.util.Properties;
import java.net.*;

import fr.ladn.carsharingclub.ing1.controller.Starter;
import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import org.apache.log4j.Logger;

/**
 * The Server class.
 * The server communicates with the clients.
 * It is connected to the database server via the connection pool.
 *
 * @see Starter
 */
public class Server {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Server.class.getName());

    /** The connection pool. */
    private ConnectionPool connectionPool = new ConnectionPool();

    /**
     * Server constructor.
     * When starting, the server initializes the connection pool.
     * Then, the server waits for a message from a client. The server stops when getting a connection.
     *
     * @see ConnectionThread
     */
    public Server() {
        logger.info("Server starting.");
        Properties properties = new Properties();

        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("configServer.properties");
            properties.load(input);
            input.close();
            logger.error("Successfully loaded configuration file.");
        } catch (IOException e) {
            logger.error("Failed to load configuration file: " + e.getMessage());
        }

        int serverPort = Integer.parseInt(properties.getProperty("serverPort"));

        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            Thread connect = new Thread(new ConnectionThread(serverSocket, connectionPool));
            connect.start();
            logger.info("Server started on port " + serverPort + ".");
        } catch (IOException e) {
            logger.error("Failed to start connection. " + e.getMessage());
        }
    }
}