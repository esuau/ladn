package fr.ladn.carsharingclub.ing1.sockets;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.*;
import java.net.*;
import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.xml.ReadXMLFile;
import fr.ladn.carsharingclub.ing1.xml.WriteXMLFile;
import fr.ladn.carsharingclub.ing1.model.Part;

/**
 * The Connect class.
 * This class provides the listening part of the server.
 * It manages the connection attempts to the server.
 *
 * @see Server
 */
public class Connect extends Thread {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Connect.class.getName());

    /** The socket of the server. */
    private ServerSocket serverSocket;

    /** The output stream. */
    private PrintWriter out;

    /** The connection pool. */
    ConnectionPool pool = new ConnectionPool();

    /**
     * Gets the socket initialized by the server.
     *
     * @param serverSocket the socket provided by the server
     * @see Server
     */
    Connect(ServerSocket serverSocket) {
        logger.info("Initializing connection.");
        this.serverSocket = serverSocket;
    }

    /**
     * Listens for connection attempts to the server.
     */
    public void run() {
        try {
            while (true) {
                Socket socketClient = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                logger.info("Client " + socketClient.getInetAddress() + " connected.");
                Part p = getData(in.readLine());
                assert p != null;
                logger.info(p.toString() + " has been added.");
                socketClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Turns the XML sent by the client into a Part object.
     *
     * @param str the string (XML) sent by the client.
     * @return a part
     * @see ReadXMLFile
     * @see Part
     */
    private Part getData(String str) {
        try {
            return ReadXMLFile.parserXML(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sends object data to a client via the socket connection.
     *
     * @param p the Part object to be send.
     * @see WriteXMLFile
     */
    public void sendData(Part p) {
        out.println(WriteXMLFile.factoryXML(p));
    }
}