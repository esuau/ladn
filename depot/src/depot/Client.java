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
		
		try {
			Socket socketClient = new Socket(serverAdress, serverPort);
			System.out.println("Connected to server : " + serverAdress + " on port : " + serverPort);
			in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			out = new PrintWriter(socketClient.getOutputStream(), true);
			socketClient.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getData() {
		try {
			return classeDeLectureXMLdeNoel.fonctionDeLecture(in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}