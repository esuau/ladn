package depot;

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

public class Server {
	private BufferedReader in;
	private PrintWriter out;
	ConnectionPool pool = new ConnectionPool();
	
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
		
		//System.out.println("serverAdress : " + serverAdress + " serverPort : " + serverPort);
		
		try {
			System.out.println("Server started on port " + serverPort);
			ServerSocket serverSocket = new ServerSocket(serverPort);
			
			while(true) {
				Socket socketClient = serverSocket.accept();
				System.out.println("Client " + socketClient.getInetAddress() + " connected");
				
				//serverSocket.close();
				//socketClient.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}