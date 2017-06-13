package fr.ladn.carsharingclub.ing1.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.*;

import fr.ladn.carsharingclub.ing1.utils.CRUD;
import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.db.*;
import fr.ladn.carsharingclub.ing1.model.*;
import fr.ladn.carsharingclub.ing1.utils.*;

/**
 * The ConnectionThread class.
 * This class provides the listening part of the server.
 * It manages the connection attempts to the server.
 *
 * @see Server
 */
public class ConnectionThread extends Thread {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(ConnectionThread.class.getName());

    /** The client socket. */
    private Socket clientSocket;

    /** The Data Access Object for parts */
    private PartDAO partDAO;
    private OperationDAO repDAO;
    private VehicleDAO vecDAO;
    private TechnicianDAO techDAO;
    private WorkFlowDAO flowDAO;
    private FailureDAO failDAO;


    /** The list of diagnosed operations. */
    private OperationList operations;

    /**
     * Gets the socket initialized by the server.
     *
     * @param clientSocket the socket provided by the server
     * @see Server
     */
    ConnectionThread(Socket clientSocket, ConnectionPool connectionPool, OperationList operations) {
        logger.info("Initializing connection.");
        this.clientSocket = clientSocket;
        this.partDAO = new PartDAO(connectionPool);
        this.repDAO = new OperationDAO(connectionPool);
        this.vecDAO = new VehicleDAO(connectionPool);
        this.techDAO = new TechnicianDAO(connectionPool);
        this.flowDAO = new WorkFlowDAO(connectionPool);
        this.failDAO = new FailureDAO(connectionPool);
        this.operations = operations;
    }

    /**
     * Listens for connection attempts to the server.
     */
    public void run() {
        try {
            logger.info("Client " + clientSocket.getInetAddress() + " connected.");
            getData();
            clientSocket.close();
        } catch (IOException e) {
            logger.error("Server error: " + e.getMessage());
        }
    }

    /**
     * Operates actions depending on the instructions received from the client.
     *
     * @see CRUD
     * @see Container
     */
    private void getData() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String str = in.readLine();
            logger.info("Received " + str);
            Container container = XML.parse(str);
            logger.info("Operations: " + container.getCRUD());
            switch (container.getCRUD()) {
                case PING:
                    logger.info("The server was successfully pinged from the client " + clientSocket.getInetAddress() + ".");
                    break;
                case CREATE:
                    logger.info("Attempt to create part in database.");
                    partDAO.create((Part) container.getObject());
                    break;
                case READ:
                    logger.info("Attempt to read part from database.");
                    int id = ((Part) container.getObject()).getId();
                    if (id > 0) sendData(new Container<>(CRUD.PING, partDAO.read(id)));
                    else sendData(new Container<>(CRUD.PING, partDAO.readAll()));
                    break;
                case READ_TECHNICIAN:
                	logger.info("Attempt to read technician from database.");
                    int id_tech = ((Technician) container.getObject()).getId();
                    if (id_tech > 0) sendData(new Container<>(CRUD.PING, techDAO.read(id_tech)));
                    else sendData(new Container<>(CRUD.PING, techDAO.readAll()));
                    break;
                    
                case UPDATE:
                    logger.info("Attempt to update part in database.");
                    partDAO.update((Part) container.getObject());
                    break;
                case DELETE:
                    logger.info("Attempt to delete part in database.");
                    partDAO.delete((Part) container.getObject());
                    break;
                case READ_OPERATION_S:
                    logger.info("Attempt to read operation in database.");
                    String status=((Operation) (container.getObject())).getStatusStr();
                    sendData(new Container<>(CRUD.PING, repDAO.getOperationsByStatus(status)));
                    break;
                case READ_PARTS_FAILURE:
                    logger.info("Attempt to read assoc_reparation_pannes.");
                    int idPart = ((Part) container.getObject()).getId();
                    sendData(new Container<>(CRUD.PING, partDAO.failurePartsReadAll(idPart)));
                    break;
                case READ_EMPTY_SPACE:
                    logger.info("Attempt to read empty place from database.");
                    sendData(new Container<>(CRUD.PING, repDAO.readEmptySpace()));
                    break;
                case READ_CAR:
                    logger.info("Attempt to read a car from database.");
                    int id_vehicle = ((Vehicle) container.getObject()).getId();
                    sendData(new Container<>(CRUD.PING, vecDAO.getVehicleById(id_vehicle)));
                case UPDATE_OPERATION:
                    logger.info("Attempt to update operation in database.");
                    repDAO.updateOperation((Operation) container.getObject());
                    break;
                case UPDATE_WORKFLOW:
                    logger.info("Attempt to update operation status in database > reparation_histo_temps.");
                    repDAO.updateWorkflow((Operation) container.getObject());
                    break;
                case CREATE_WORKFLOW:
                    logger.info("Attempt to update operation status in database > reparation_histo_temps.");
                    repDAO.createWorkflow((Operation) container.getObject());
                    break;
                case READ_CAR_F:
                    logger.info("Attempt to read cars in the workflow database.");
                    int v=((WorkFlowRep) (container.getObject())).getId_vehicule();
                    if(v<0)sendData(new Container<>(CRUD.PING, flowDAO.displayCars()));
                    else sendData(new Container<>(CRUD.PING, flowDAO.WorkflowCar(v)));
                    break;
                case READ_STAT:
                    logger.info("Attempt to read cars in the workflow database.");
                    int choix=((WorkFlowRep) (container.getObject())).getId_reparation();
                    sendData(new Container<>(CRUD.PING, flowDAO.calculStats(choix)));
                    break;
                case CREATE_REPARATION:
                    logger.info("Attempt to create operation in database.");
                    Operation operation = (Operation) container.getObject();
                    operation.setId(repDAO.createOperation(operation));
                    sendData(new Container<>(CRUD.PING, operation));
                    break;
                case READ_FAILURES:
                    logger.info("Attempt to read all failures from database.");
                    sendData(new Container<>(CRUD.PING, failDAO.getAllFailures()));
                    break;
                case GET_NEW_OPERATION:
                    logger.info("Attempt to get most urgent operation.");
                    sendData(new Container<>(CRUD.PING, operations.getElements().first()));
                    break;
                default:
                    logger.info("Sorry. This operation is not covered yet.");
            }
            logger.info("Finished operation.");
        } catch (IOException e) {
            logger.error("Failed to get data from client: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends object data to a client via the socket connection.
     *
     * @param container the Container object to be sent.
     * @see XML
     */
    private void sendData(Container container) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(XML.stringify(container));
            logger.info("Data has been sent back to the client.");
            logger.info(XML.stringify(container));
        } catch (IOException e) {
            logger.error("Failed to send data to client: " + e.getMessage());
        }
    }
}