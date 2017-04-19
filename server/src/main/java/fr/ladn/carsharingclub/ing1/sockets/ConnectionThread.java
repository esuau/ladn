package fr.ladn.carsharingclub.ing1.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.db.PartDAO;
import fr.ladn.carsharingclub.ing1.utils.XML;
import fr.ladn.carsharingclub.ing1.model.Part;
import fr.ladn.carsharingclub.ing1.utils.Container;

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

    /** The output stream. */
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
            clientSocket.close();
        } catch (IOException e) {
            logger.error("Server error: " + e.getMessage());
        }
    }

    /**
     * Turns the XML sent by the client into a 
     * @see Part
     */
    private void getData(String str) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Container c = XML.parse(str);
            PartDAO partDAO = new PartDAO(connectionPool);
            partDAO.create(part);
            logger.info("Part " + part + " has been added.");
            String operation = c.getOperation();
            Part p = c.getObject();
            
            switch (operation) {
                case "CREATE":
                    PartDAO.create(p);
                    break;
                case "READ":
                    PartDAO.read(p.getId());
                    break;
                case "UPDATE":
                    PartDAO.update(p);
                    break;
                case "DELETE";
                    PartDAO.delete(p);
                    break;
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
     * @see WriteXMLFile
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