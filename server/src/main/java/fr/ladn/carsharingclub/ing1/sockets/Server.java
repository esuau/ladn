package fr.ladn.carsharingclub.ing1.sockets;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.*;
import java.net.*;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.xml.ReadXMLFile;
import fr.ladn.carsharingclub.ing1.xml.WriteXMLFile;
import fr.ladn.carsharingclub.ing1.model.Part;

/**
 * Server class.
 * <p>
 * The server communicates with the clients.
 * Its is connected to the database server via the connection pool.
 * In this test version, the server stops after a single connection.
 * </p>
 *
 * @see ConnectionPool
 * @see ServerMain
 */
public class Server {
    private BufferedReader in;
    private PrintWriter out;
    ConnectionPool pool = new ConnectionPool();

    /**
     * Server constructor.
     * <p>
     * When starting, the server initializes the connection pool.
     * Then, the server waits for a message from a client. The server stops when getting a connection.
     * </p>
     *
     * @see ConnectionPool
     */
    public Server() {
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
            System.out.println("Server started on port " + serverPort);
            ServerSocket serverSocket = new ServerSocket(serverPort);

            Socket socketClient = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            System.out.println("Client " + socketClient.getInetAddress() + " connected");
            Part p = getData(in.readLine());
            System.out.println(p.toString() + " added");
            socketClient.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Turns the XML sent by the client into a Part object
     *
     * @param str the string (XML) sent by the client
     * @return a part
     * @see ReadXMLFile
     * @see Part
     */
    public Part getData(String str) {
        try {
            return ReadXMLFile.parserXML(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sends object data to a client via the socket connection
     *
     * @param p the Part object to be send
     * @see WriteXMLFile
     */
    public void sendData(Part p) {
        out.println(WriteXMLFile.factoryXML(p));
    }

}