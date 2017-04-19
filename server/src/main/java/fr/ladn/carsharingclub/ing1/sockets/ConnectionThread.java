package fr.ladn.carsharingclub.ing1.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.db.PartDAO;
import fr.ladn.carsharingclub.ing1.model.Part;
import fr.ladn.carsharingclub.ing1.utils.Container;
import fr.ladn.carsharingclub.ing1.utils.Operation;
import fr.ladn.carsharingclub.ing1.utils.XML;

/**
 * The ConnectionThread class.
 * This class provides the listening part of the server.
 * It manages the connection attempts to the server.
 *
 * @see Server
 */
public class ConnectionThread extends Thread {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(ConnectionThread.class.getName());

    /** The client socket. */
    private Socket clientSocket;

    /** The connection pool. */
    private ConnectionPool connectionPool;

    /**
     * Gets the socket initialized by the server.
     *
     * @param clientSocket the socket provided by the server
     * @see Server
     */
    ConnectionThread(Socket clientSocket, ConnectionPool connectionPool) {
        logger.info("Initializing connection.");
        this.clientSocket = clientSocket;
        this.connectionPool = connectionPool;
    }

    /**
     * Listens for connection attempts to the server.
     */
    public void run() {
        try {
            logger.info("Client " + clientSocket.getInetAddress() + " connected.");
            getData();
            clientSocket.close();
        } catch (IOException e) {
            logger.error("Server error: " + e.getMessage());
        }
    }

    /**
     * Turns the XML sent by the client into a 
     * @see Part
     */
    private void getData() {
        try {
            PartDAO partDAO = new PartDAO(connectionPool);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String str = in.readLine();
            logger.info("Received " + str);
            if (str != null) {
                Container container = XML.parse(str);
                Operation operation = container.getOperation();
                Part p = (Part) container.getObject();
                switch (operation) {
                    case CREATE:
                        logger.info("Attempt to create part in database.");
                        partDAO.create(p);
                        break;
                    case READ:
                        logger.info("Attempt to read part from database.");
                        sendData(partDAO.read(p.getId()));
                        break;
                    case UPDATE:
                        logger.info("Attempt to update part in database.");
                        partDAO.update(p);
                        break;
                    case DELETE:
                        logger.info("Attempt to delete part in database.");
                        partDAO.delete(p);
                        break;
                    default:
                        System.out.println("The server was successfully pinged from client.");
                }
            } else {
                logger.info("Server was successfully pinged from client " + clientSocket.getInetAddress());
            }
        } catch (IOException e) {
            logger.error("Failed to get data from client: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to execute database operation: " + e.getMessage());
        }
    }

    /**
     * Sends object data to a client via the socket connection.
     *
     * @param p the Part object to be send.
     * @see XML
     */
    private void sendData(Part p) {
        Container<Part> container = new Container<>(null, p);
        
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(XML.stringify(container));
            logger.info("Part " + p + " has been sent back to the client.");
        } catch (IOException e) {
            logger.error("Failed to send data to client: " + e.getMessage());
        }
    }
}