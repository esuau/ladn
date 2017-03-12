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

public class Connect extends Thread {
	ServerSocket serverSocket;
	Socket socketClient;
	private BufferedReader in;
	private PrintWriter out;
	ConnectionPool pool = new ConnectionPool();
	
	public Connect(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public void run() {
		try {
			while(true) {
				socketClient = serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				System.out.println("Client " + socketClient.getInetAddress() + " connected");
				Part p = getData(in.readLine());
				System.out.println(p.toString() + " added");
				socketClient.close();
			}
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