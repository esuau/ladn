package fr.ladn.carsharingclub.ing1.model;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

/**
 * The class Operation.
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

    /** The starting date of repairs. */
    private Date start;

    /** The real end date of the repair work. */
    private Date end;

    /**
     * Default constructor.
     * Allows serialization.
     */
    Operation() { }

    /**
     * Constructor of the operation.
     *
     * @param vehicle  the corresponding vehicle.
     * @param failures the failures.
     * @param status   the status of the operation.
     */
    public Operation(Vehicle vehicle, Failure[] failures, OperationStatus status) {
        this.vehicle = vehicle;
        this.failures = failures;
        this.status = status;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public OperationPriority getPriority() {
        return priority;
    }

    public void setPriority(OperationPriority priority) {
        this.priority = priority;
    }
}
