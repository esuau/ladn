package fr.ladn.carsharingclub.ing1.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.db.PartDAO;
import fr.ladn.carsharingclub.ing1.utils.ReadXMLFile;
import fr.ladn.carsharingclub.ing1.utils.WriteXMLFile;
import fr.ladn.carsharingclub.ing1.model.Part;

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
     * Turns the XML sent by the client into a Part object.
     *
     * @param str the string (XML) sent by the client.
     * @see ReadXMLFile
     * @see Part
     */
    private void getData(String str) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Part part = ReadXMLFile.parserXML(str);
            PartDAO partDAO = new PartDAO(connectionPool);
            partDAO.create(part);
            logger.info("Part " + part + " has been added.");
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
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(WriteXMLFile.factoryXML(p));
            logger.info("Part " + p + " has been sent back to the client.");
        } catch (IOException e) {
            logger.error("Failed to  " + e.getMessage());
        }
    }
}