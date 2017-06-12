package fr.ladn.carsharingclub.ing1.sockets;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;
import java.net.*;

import fr.ladn.carsharingclub.ing1.controller.Starter;
import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.db.OperationDAO;
import fr.ladn.carsharingclub.ing1.model.OperationList;
import fr.ladn.carsharingclub.ing1.model.OperationStatus;
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
    private ConnectionPool connectionPool;

    /** The operation list. */
    private OperationList operationList;

    /**
     * Server constructor.
     * When starting, the server initializes the connection pool.
     * Then, the server waits for a message from a client. The server stops when getting a connection.
     *
     * @see ConnectionThread
     */
    public Server() {
        logger.info("Server starting.");

        // Connection pool initialization.
        connectionPool = new ConnectionPool();

        // Operation list initialization.
        initializeOperationList();

        Properties properties = new Properties();

        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("configServer.properties");
            properties.load(input);
            input.close();
            logger.info("Successfully loaded configuration file.");
        } catch (IOException e) {
            logger.error("Failed to load configuration file: " + e.getMessage());
        }

        int serverPort = Integer.parseInt(properties.getProperty("serverPort"));

        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            logger.info("Server started on port " + serverPort + ".");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Launching new connection thread.");
                Thread connectionThread = new ConnectionThread(clientSocket, connectionPool, operationList);
                connectionThread.start();
                updateOperationList();
            }
        } catch (IOException e) {
            logger.error("Failed to start connection. " + e.getMessage());
        }
    }

    /**
     * Initializes the list of operations not started.
     *
     * @see OperationList
     */
    private void initializeOperationList() {
        try {
            OperationDAO operationDAO = new OperationDAO(connectionPool);
            operationList = operationDAO.getOperationListByStatus(OperationStatus.DIAGNOSED);
            logger.info(operationList.toString());
        } catch (SQLException e) {
            logger.error("Failed to initialize the operation list: " + e.getMessage());
        }
    }

    /**
     * Initializes the list of operations not started.
     *
     * @see OperationList
     */
    private void updateOperationList() {
        try {
            OperationDAO operationDAO = new OperationDAO(connectionPool);
            operationList = operationDAO.getOperationListByStatus(OperationStatus.DIAGNOSED);
            logger.info(operationList.toString());
        } catch (SQLException e) {
            logger.error("Failed to update the operation list: " + e.getMessage());
        }
    }

    public synchronized OperationList getOperationList() {
        return operationList;
    }

    public synchronized void setOperationList(OperationList operationList) {
        this.operationList = operationList;
    }
}