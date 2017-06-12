package fr.ladn.carsharingclub.ing1.sockets;


import fr.ladn.carsharingclub.ing1.model.*;

import fr.ladn.carsharingclub.ing1.model.Operation;
import fr.ladn.carsharingclub.ing1.model.Part;

import fr.ladn.carsharingclub.ing1.model.Technician;
import fr.ladn.carsharingclub.ing1.model.Vehicle;
import fr.ladn.carsharingclub.ing1.model.WorkFlowRep;

import fr.ladn.carsharingclub.ing1.utils.CRUD;
import fr.ladn.carsharingclub.ing1.utils.Container;
import fr.ladn.carsharingclub.ing1.utils.XML;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

/**
 * The class Client.
 * Allows every operation for client communication with the server.
 */
public class Client extends Thread {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Client.class.getName());

    /** The server address. */
    private String serverAddress = "";

    /** The server port. */
    private int serverPort = 0;

    /**
     * Client default constructor.
     * When starting, the client uses the file <tt>configClient.properties</tt> stored in <tt>src/main/resources/</tt>.
     * Once the configuration loaded, the client sends an empty container to the server to check the validity of the connection.
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
            sendData(socketClient, new Container<>(CRUD.PING, new Part()));
            socketClient.close();
            logger.info("Successfully pinged server " + serverAddress + " on port " + serverPort + ".");
        } catch (IOException e) {
            logger.error("Failed to ping the server " + serverAddress + " on port " + serverPort + ": " + e.getMessage());
        }
    }

    /**
     * Gets a container object from the server.
     *
     * @return a Container object.
     */
    private Container getData(Socket clientSocket) {
        logger.info("Getting data from the server.");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Container container = XML.parse(in.readLine());
            logger.info("Successfully get data from server " + serverAddress + " on port " + serverPort + ".");
            return container;
        } catch (IOException e) {
            logger.error("Failed to get data from server: " + e.getMessage());
        }
        return null;
    }

    /**
     * Sends a container object to the server.
     *
     * @param container The container to be sent to the server.
     */
    private void sendData(Socket clientSocket, Container container) {
        String message = XML.stringify(container);
        logger.info("Sending data to server: " + message);
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);
            logger.info("Successfully sent data to server " + serverAddress + " on port " + serverPort + ".");
        } catch (IOException e) {
            logger.error("Failed to send data to the server: " + e.getMessage());
        }
    }

    /**
     * Sends a new part to the server in order to create it in database.
     *
     * @param part the part to create.
     */
    public void createPart(Part part) {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.CREATE, part));
            socketClient.close();
        } catch (IOException e) {
            logger.error("Failed to send part #" + part.getId() + " to the server: " + e.getMessage());
        }
    }

    /**
     * Gets a part object by ID from the server.
     *
     * @param id the id of the part to be get from the server.
     */
    public Part getPart(int id) {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.READ, new Part(id, "", "", 0, 0)));
            Part part = (Part) getData(socketClient).getObject();
            socketClient.close();
            return part;
        } catch (IOException e) {
            logger.error("Failed to get part #" + id + " from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No part was returned from the server.");
        }
        return null;
    }

    /**
     * Gets all the existing parts transmitted by the server.
     *
     * @return the list of all the existing parts.
     */
    public ArrayList<Part> getParts() {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.READ, new Part(-1, "", "", 0, 0)));
            Container<ArrayList<Part>> receivedContainer = getData(socketClient);
            ArrayList<Part> parts = receivedContainer.getObject();
            socketClient.close();
            return parts;
        } catch (IOException e) {
            logger.error("Failed to get parts from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No part was returned from the server.");
        }
        return null;
    }
    public Technician getTechnician(int id) {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.READ_TECHNICIAN, new Technician(id)));
            Technician technician = (Technician) getData(socketClient).getObject();
            socketClient.close();
            return technician;
        } catch (IOException e) {
            logger.error("Failed to get technician #" + id + " from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No technician was returned from the server.");
        }
        return null;
    }

    /**
     * Gets all the existing parts transmitted by the server.
     *
     * @return the list of all the existing parts.
     */
    public ArrayList<Technician> getTechnicians() {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.READ_TECHNICIAN, new Technician(-1)));
            Container<ArrayList<Technician>> receivedContainer = getData(socketClient);
            ArrayList<Technician> technicians = receivedContainer.getObject();
            socketClient.close();
            return technicians;
        } catch (IOException e) {
            logger.error("Failed to get technicians from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No technicians was returned from the server.");
        }
        return null;
    }
    
    /**
     * Gets a vehicle object by ID from the server.
     *
     * @param id the id of the vehicle to be get from the server.
     */
    public Vehicle getVehicle(int id) {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.READ_CAR, new Vehicle(id, "", "", "", "")));
            Vehicle vehicle = (Vehicle) getData(socketClient).getObject();
            socketClient.close();
            return vehicle;
        } catch (IOException e) {
            logger.error("Failed to get vehicle #" + id + " from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No vehicle was returned from the server.");
        }
        return null;
    }

    /**
     * Gets the operations having the selected status.
     *
     * @param s the status of the operation.
     * @return the corresponding list of operation.
     */
    public ArrayList<Operation> getOperationsStatus(String s) {
        try {
            
            Socket socketClient = new Socket(serverAddress, serverPort);
             sendData(socketClient, new Container<>(CRUD.READ_OPERATION_S, new Operation(0,s,0,null,null,null,null,0)));
             Container<ArrayList<Operation>> receivedContainer = getData(socketClient);
             ArrayList<Operation> reparations=new ArrayList<>();
             reparations.addAll(receivedContainer.getObject());
             socketClient.close();
            return reparations;
        } catch (IOException e) {
            logger.error("Failed to get operations from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No operation was returned from the server.");
        }
        return null;
    }


    /**
     * Removes a part in the database.
     *
     * @param id the id of the part to remove.
     */
    public void removePart(int id) {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.DELETE, new Part(id, "", "", 0, 0)));
            socketClient.close();
        } catch (IOException e) {
            logger.error("Failed to get part #" + id + " from the server: " + e.getMessage());
        }
    }

    /**
     * Updates a part.
     *
     * @param part the part to update.
     */
    public void updatePart(Part part) {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.UPDATE, part));
            socketClient.close();
        } catch (IOException e) {
            logger.error("Failed to get part #" + part.getId() + " from the server: " + e.getMessage());
        }
    }
    
    /**
     * Gets an ArrayList of Parts needed for the operation in parameter.
     *
     * @param idFailure the identifier of failure that needs the parts.
     */
    public ArrayList<Part> getPartsFailure(int idFailure) {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.READ_PARTS_FAILURE, new Part(idFailure, "", "", 0, 0)));
            Container<ArrayList<Part>> receivedContainer = getData(socketClient);
            ArrayList<Part> parts = receivedContainer.getObject();
            socketClient.close();
            return parts;
        } catch (IOException e) {
            logger.error("Failed to get parts needed for the operation from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No part was returned from the server.");
        }
        return null;
    }

    /**
     * Gets an empty parking spaces from the server.
     *
     * @return the identifier of an empty parking spot.
     */
    public int getEmptySpace() {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.READ_EMPTY_SPACE, new Object()));
            Integer i = (Integer) getData(socketClient).getObject();
            socketClient.close();
            return i.intValue();
        } catch (IOException e) {
            logger.error("Failed to get space from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No space was returned from the server.");
        }
        return 0;
    }
    
    public void updateOperation(Operation operation) {
         try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.UPDATE_OPERATION, operation));
            socketClient.close();
        } catch (IOException e) {
            logger.error("Failed to get operation #" + operation.getId() + " from the server: " + e.getMessage());
        }
    }
    
    public void updateWorkflow(Operation operation) {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.UPDATE_WORKFLOW, operation));
            socketClient.close();
        } catch (IOException e) {
            logger.error("Failed to get operation #" + operation.getId() + " from the server: " + e.getMessage());
        }
    }
    
    public void createWorkflow(Operation operation) {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.CREATE_WORKFLOW, operation));
            socketClient.close();
        } catch (IOException e) {
            logger.error("Failed to send operation #" + operation.getId() + " to the server: " + e.getMessage());
        }
    }   
        
    public ArrayList<WorkFlowRep> getCarFlow(int v) {
        try {
            
            Socket socketClient = new Socket(serverAddress, serverPort);
             if(v<0)sendData(socketClient, new Container<>(CRUD.READ_CAR_F, new WorkFlowRep(0,"",null,0,null,-1,"")));
             else sendData(socketClient, new Container<>(CRUD.READ_CAR_F, new WorkFlowRep(0,"",null,0,null,v,"")));
             Container<ArrayList<WorkFlowRep>> receivedContainer = getData(socketClient);
             for (WorkFlowRep r : receivedContainer.getObject()) {
                 System.out.println(r.getId_vehicule()+" test");
            }
             ArrayList<WorkFlowRep> vehicule=new ArrayList<>();
             vehicule.addAll(receivedContainer.getObject());
             socketClient.close();
            return vehicule;
        } catch (IOException e) {
            logger.error("Failed to get cars from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No cars was returned from the server.");
        }
        return null;
    }

    public ArrayList<WorkFlowRep> getCalculStat(int choix) {
        try {
            
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.READ_STAT, new WorkFlowRep(choix,"",null,0,null,0,"")));
            Container<ArrayList<WorkFlowRep>> receivedContainer = getData(socketClient);
            /* for (WorkFlowRep r : receivedContainer.getObject()) {
                 System.out.println(r.getId_vehicule()+" test");
            }*/
             ArrayList<WorkFlowRep> vehicule=new ArrayList<>();
             vehicule.addAll(receivedContainer.getObject());
             socketClient.close();
            return vehicule;
        } catch (IOException e) {
            logger.error("Failed to get stat from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No stats was returned from the server.");
        }
        return null;
    }

    
    public Operation createRepairWork(Operation operation) {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.CREATE_REPARATION, operation));
            Operation operationWithId = (Operation) getData(socketClient).getObject();
            socketClient.close();
            return operationWithId;
        } catch (IOException e) {
            logger.error("Failed to send operation #" + operation.getId() + " to the server: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Gets all the existing Failures transmitted by the server.
     *
     * @return the list of all the existing failures.
     */
    public ArrayList<Failure> getFailures() {
        try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.READ_FAILURES, new Failure(0,"", null, "", null)));
            Container<ArrayList<Failure>> receivedContainer = getData(socketClient);
            ArrayList<Failure> failure = receivedContainer.getObject();
            socketClient.close();
            return failure;
        } catch (IOException e) {
            logger.error("Failed to get failures from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No failure was returned from the server.");
        }
        return null;
    }
    
    public Integer getNbOp(ParamNbOp param) {
		// TODO Auto-generated method stub
		try {
            Socket socketClient = new Socket(serverAddress, serverPort);
            sendData(socketClient, new Container<>(CRUD.GET_NB_OP, param));
            Container<Integer> receivedContainer = getData(socketClient);
            Integer res = receivedContainer.getObject();
            socketClient.close();
            return res;
        } catch (IOException e) {
            logger.error("Failed to get result with that parameters from the server: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("No result was returned from the server with that parameters.");
        }
        return null;
	}

}

