package fr.ladn.carsharingclub.ing1.sockets;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.*;
import java.net.*;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;

public class Server {
    private BufferedReader in;
    private PrintWriter out;

    public Server() {
        Properties properties = new Properties();

        try {
            FileInputStream input = new FileInputStream("configServer.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConnectionPool pool = new ConnectionPool();

        int serverPort = Integer.parseInt(properties.getProperty("serverPort"));

        try {
            System.out.println("Server started on port " + serverPort);
            ServerSocket serverSocket = new ServerSocket(serverPort);

            Socket socketClient = serverSocket.accept();
            System.out.println("Client " + socketClient.getInetAddress() + " connected");

            socketClient.close();
            System.out.println("Closed connection with client");
            serverSocket.close();
            System.out.println("Closed server socket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}