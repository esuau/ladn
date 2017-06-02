package fr.ladn.carsharingclub.ing1.model;

import java.io.Serializable;
import java.time.Duration;

/**
 * The class CRUD.
 * Includes data about the upcoming, ongoing and finished operations.
 */
public class Operation implements Serializable {

    /** The identifier of the operation. */
    private int id;

    /** The vehicle to be repaired. */
    private Vehicle vehicle;

    /** The corresponding failures to repair. */
    private Failure[] failures;

    /** The current status of the operation. */
    private OperationStatus status;

    /** The technician in charge of the repair work. */
    private Technician technician;

    /** The priority level of the operation. */
    private OperationPriority priority;

    /** The date of entry of the vehicle in the deposit. */
    private java.sql.Timestamp dateEntry;

    /** The actual exit date of the vehicle. */
    private java.sql.Timestamp dateExit;
    
     /** The actual exit date of the vehicle. */
    private String comment;
    
    /** The begin date of the actual status of the operation. */
    private java.sql.Timestamp dateBS;
    
    /** The end date of the actual status of the operation. */
    private java.sql.Timestamp dateES;

    /** The identifier of the parking space. */
    private int parkingSpace;

    /**
     * Default constructor.
     * Allows serialization.
     */
    Operation() { }

    /**
     * Instantiates the operation with all the fields.
     *
     * @param id           the identifier of the operation.
     * @param vehicle      the vehicle corresponding to the operation.
     * @param failures     the failures corresponding to the operation.
     * @param status       the status of the operation.
     * @param technician   the technician in charge of the operation.
     * @param priority     the priority level of the operation.
     * @param dateEntry    the entry date of the vehicle.
     * @param dateExit     the exit date of the vehicle.
     * @param parkingSpace the ID of the parking spot.
     *
     * @see Vehicle
     * @see Failure
     * @see OperationStatus
     * @see OperationPriority
     */
    public Operation(int id, Vehicle vehicle, Failure[] failures, OperationStatus status, Technician technician, OperationPriority priority, java.sql.Timestamp dateEntry, java.sql.Timestamp dateExit, int parkingSpace) {
        this.id = id;
        this.vehicle = vehicle;
        this.failures = failures;
        this.status = status;
        this.technician = technician;
        this.priority = priority;
        this.dateEntry = dateEntry;
        this.dateExit = dateExit;
        this.parkingSpace = parkingSpace;
    }

    /**
     * Instantiates an operation without defined ID.
     *
     * @param vehicle      the vehicle corresponding to the operation.
     * @param failures     the failures corresponding to the operation.
     * @param status       the status of the operation.
     * @param technician   the technician in charge of the operation.
     * @param priority     the priority level of the operation.
     * @param dateEntry    the entry date of the vehicle.
     * @param dateExit     the exit date of the vehicle.
     * @param parkingSpace the ID of the parking spot.
     *
     * @see Vehicle
     * @see Failure
     * @see OperationStatus
     * @see Technician
     */
    public Operation(Vehicle vehicle, Failure[] failures, OperationStatus status, Technician technician, OperationPriority priority, java.sql.Timestamp dateEntry, java.sql.Timestamp dateExit, int parkingSpace) {
        this.vehicle = vehicle;
        this.failures = failures;
        this.status = status;
        this.technician = technician;
        this.priority = priority;
        this.dateEntry = dateEntry;
        this.dateExit = dateExit;
        this.parkingSpace = parkingSpace;
    }

    /**
     * Constructor of the operation.
     *
     * @param vehicle  the corresponding vehicle.
     * @param failures the failures.
     * @param status   the status of the operation.
     */
    public Operation(int id, Vehicle vehicle, Failure[] failures, OperationStatus status) {
        this.vehicle = vehicle;
        this.failures = failures;
        this.status = status;
    }
    
    public Operation(int id) {
        this.id = id;
    }

    /**
     * Gets the biggest and most urgent failure to repair for the operation.
     *
     * @return the most urgent failure to repair.
     */
    public Failure getBiggestFailure() {
        int index = 0;
        for (int i = 0; i < failures.length; i++) {
            if (failures[i].getType().getPriority() < failures[index].getType().getPriority()) {
                index = i;
            }
        }
        return failures[index];
    }

    /**
     * Gets the total estimated duration of the operation given all the failures.
     *
     * @return the total theoretical duration of the operation.
     */
    public Duration getTotalEstimatedDuration() {
        Duration totalEstimatedDuration = Duration.ofMinutes(0);
        for (Failure failure : failures) {
            totalEstimatedDuration = totalEstimatedDuration.plus(failure.getEstimatedTime());
        }
        return totalEstimatedDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Failure[] getFailures() {
        return failures;
    }

    public void setFailures(Failure[] failures) {
        this.failures = failures;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public java.sql.Timestamp getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(java.sql.Timestamp dateEntry) {
        this.dateEntry = dateEntry;
    }

    public java.sql.Timestamp getDateExit() {
        return dateExit;
    }

    public void setDateExit(java.sql.Timestamp dateExit) {
        this.dateExit = dateExit;
    }

    public OperationPriority getPriority() {
        return priority;
    }

    public void setPriority(OperationPriority priority) {
        this.priority = priority;
    }
    
    public java.sql.Timestamp getDateBS() {
        return dateBS;
    }
    
    public void setDateBS(java.sql.Timestamp dateBS) {
        this.dateBS = dateES;
    }
    
    public java.sql.Timestamp getDateES() {
        return dateES;
    }
    
    public void setDateES(java.sql.Timestamp dateES) {
        this.dateES = dateES;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

}
