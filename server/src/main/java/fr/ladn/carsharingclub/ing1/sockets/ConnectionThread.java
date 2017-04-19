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

    /** The socket of the server. */
    private ServerSocket serverSocket;

    /** The client socket. */
    private Socket clientSocket;

    /** The connection pool. */
    private ConnectionPool connectionPool;

    /**
     * Gets the socket initialized by the server.
     *
     * @param serverSocket the socket provided by the server
     * @see Server
     */
    ConnectionThread(ServerSocket serverSocket, ConnectionPool connectionPool) {
        logger.info("Initializing connection.");
        this.serverSocket = serverSocket;
        this.connectionPool = connectionPool;
    }

    /**
     * Listens for connection attempts to the server.
     */
    public void run() {
        try {
            Socket clientSocket = serverSocket.accept();
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
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Container container = XML.parse(in.readLine());
            PartDAO partDAO = new PartDAO(connectionPool);
            Operation operation = container.getOperation();
            Part p = (Part) container.getObject();
            
            switch (operation) {
                case CREATE:
                    partDAO.create(p);
                    break;
                case READ:
                    partDAO.read(p.getId());
                    break;
                case UPDATE:
                    partDAO.update(p);
                    break;
                case DELETE:
                    partDAO.delete(p);
                    break;
                default:
                    System.out.println("The server was successfully pinged from client.");
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
            logger.error("Failed to  " + e.getMessage());
        }
    }
}