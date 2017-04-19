package fr.ladn.carsharingclub.ing1.sockets;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.*;

import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.utils.Operation;
import fr.ladn.carsharingclub.ing1.model.Part;
import fr.ladn.carsharingclub.ing1.utils.XML;

/**
 * The class Client.
 */
public class Client extends Thread {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Client.class.getName());

    /** The XML serialization tool. */
    private XML<Part> xml = new XML<>();

    /** The input stream reader. */
    private BufferedReader in;

    /** The output stream writer. */
    private PrintWriter out;

    /** The server address. */
    private String serverAddress = "";

    /** The server port. */
    private int serverPort = 0;

    /**
     * Client default constructor.
     * When starting, the client uses the file <tt>configClient.properties</tt> stored in <tt>src/main/resources/</tt>.
     * Once the configuration loaded, the client sends a ping to the server to check the validity of the connection.
     */
    public Client() {
        logger.info("Initialize connection with server...");
        Properties properties = new Properties();

        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("configClient.properties");
            properties.load(input);
            input.close();
            logger.info("Client configuration successfully loaded.");
        } catch (IOException e) {
            logger.error("Failed to load client configuration file: " + e.getMessage());
        }

        serverAddress = properties.getProperty("serverAddress");
        serverPort = Integer.parseInt(properties.getProperty("serverPort"));

        // Pings the server to check connection.
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            logger.info("Successfully pinged server " + serverAddress + " on port " + serverPort + ".");
            socketClient.close();
        } catch (IOException e) {
            logger.error("Failed to establish connection with server: " + e.getMessage());
        }
    }

    /**
     * Converts the text (XML) data sent by the server into an Part object.
     *
     * @return a Part object.
     * @see XML
     */
    public Part getData() {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            Part part = xml.parse(in.readLine());
            logger.info("Successfully get data from server " + serverAddress + " on port " + serverPort + ".");
            socketClient.close();
            return part;
        } catch (IOException e) {
            logger.error("Failed to get data from server: " + e.getMessage());
        }
        return null;
    }

    /**
     * Converts an object into a XML formatted string and sends it to the server.
     *
     * @param p The part object to be turned into XML.
     * @see XML
     */
    public void sendData(Operation operation, Part p) {
        String serializedPart = xml.stringify(p);
        logger.info("Send data to server: " + xml.stringify(p));
        Map<String, Enum> message = new HashMap<>();
        message.put(serializedPart, operation);
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socketClient.getOutputStream(), true);
            out.println(message);
            socketClient.close();
            logger.info("Successfully sent data to server " + serverAddress + " on port " + serverPort + ".");
        } catch (IOException e) {
            logger.error("Failed to send data to the server: " + e.getMessage());
        }
    }

}
