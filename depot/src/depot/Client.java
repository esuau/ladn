package depot;

import java.net.Socket;
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

public class Client {
	private BufferedReader in;
	private PrintWriter out;
	
	public Client() {
		Properties properties = new Properties();
		
		try {
			FileInputStream input = new FileInputStream("configClient.properties");
			properties.load(input);
			input.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		String serverAdress = properties.getProperty("serverAdress");
		int serverPort = Integer.parseInt(properties.getProperty("serverPort"));
		
		System.out.println("serverAdress : " + serverAdress + " serverPort : " + serverPort);
		
		try {
			Socket socketClient = new Socket(serverAdress, serverPort);
			socketClient.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}