package fr.ladn.carsharingclub.ing1.model;

import java.io.Serializable;

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

     /** The priority level of the operation. */
    private int priorityStr;

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

    /** The old status of the operation to be used when searching the operation status in reparation_histo_temps */
    private OperationStatus oldStatus;

    /** The old status of the operation to be used when searching the operation status in reparation_histo_temps */
    private String statusStr;

    /**
     * Default constructor.
     * Allows serialization.
     */
    public Operation() { }

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
     * Instantiates an operation.
     * Used when getting operation by status.
     *
     * @param id              the identifier of the operation.
     * @param status          the operation status.
     * @param priority        the priority of the vehicle.
     * @param dateEntry       the entry date of the vehicle.
     * @param dateExit        the exit date of the vehicle.
     * @param technician      the technician in charge.
     * @param vehicleId       the identifier of the vehicle.
     * @param parkingSpaceId  the identifier of the parking space.
     */
    public Operation(int id, String status, int priority, java.sql.Timestamp dateEntry, java.sql.Timestamp dateExit, Technician technician, Vehicle vehicleId, int parkingSpaceId) {
        this.id = id;
        this.dateEntry = dateEntry;
        this.priorityStr = priority;
        this.statusStr = status ;
        this.dateExit = dateExit;
        this.technician = technician;
        this.vehicle = vehicleId;
        this.parkingSpace = parkingSpaceId;
    }


    /**
     * Constructor of the operation.
     *
     * @param vehicle  the corresponding vehicle.
     * @param failures the failures.
     * @param status   the status of the operation.
     */
    public Operation(int id, Vehicle vehicle, Failure[] failures, OperationStatus status) {
        this.id = id;
        this.vehicle = vehicle;
        this.failures = failures;
        this.status = status;
    }

    /**
     * Instantiates a vehicle.
     * Used when initializing the OperationList.
     *
     * @param id           the identifier of the operation.
     * @param vehicle      the vehicle corresponding to the operation.
     * @param failures     the failures leading the operations.
     * @param status       the operation status (normally DIAGNOSED).
     * @param priority     the operation priority.
     * @param parkingSpace the parking space of the vehicle.
     * @param dateEntry    the entry date of the vehicle.
     * @param comment      a comment leaved during the diagnosis.
     */
    public Operation(int id, Vehicle vehicle, Failure[] failures, OperationStatus status, OperationPriority priority, int parkingSpace, java.sql.Timestamp dateEntry, String comment) {
        this.id = id;
        this.vehicle = vehicle;
        this.failures = failures;
        this.status = status;
        this.priority = priority;
        this.parkingSpace = parkingSpace;
        this.dateEntry = dateEntry;
        this.comment = comment;
    }

    /**
     * Constructor of the operation.
     *
     * To be used only in RepairView to update reparation_histo_temps.
     *
     * @param dateBS the Timestamp date of the beginning of the status.
     * @param dateES the Timestamp date of the end of the status.
     */
    public Operation(int id, java.sql.Timestamp dateBS, java.sql.Timestamp dateES, OperationStatus status) {
        this.id = id;
        this.dateBS = dateBS;
        this.dateES = dateES;
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
    Failure getBiggestFailure() {
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
    int getTotalEstimatedDuration() {
        int totalEstimatedDuration = 0;
        for (Failure failure : failures) {
            totalEstimatedDuration += failure.getEstimatedTime();
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

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setPriority(OperationPriority priority) {
        this.priority = priority;
    }

    public java.sql.Timestamp getDateBS() {
        return dateBS;
    }

    public void setDateBS(java.sql.Timestamp dateBS) {
        this.dateBS = dateBS;
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

    public OperationStatus getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(OperationStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public void setPriorityStr(int priorityStr) {
        this.priorityStr = priorityStr;
    }

    public int getPriorityStr() {
        return priorityStr;
    }
}