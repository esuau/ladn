package fr.ladn.carsharingclub.ing1.model;

import java.io.Serializable;

/**
 * A mocking class for Operation.
 */
public class OperationMock implements Serializable {

    private int id;
    private String failure;
    private int vehicle;
    private Priority priority;
    private double estimatedDuration;

    private OperationMock() { }

    public OperationMock(int id, String failure, int vehicle, Priority priority, double estimatedDuration) {
        this.id = id;
        this.failure = failure;
        this.vehicle = vehicle;
        this.priority = priority;
        this.estimatedDuration = estimatedDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public int getVehicle() {
        return vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(double estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-35s %-10s %-15s %-10s", id, failure, vehicle, priority.toString(), estimatedDuration);
    }

}
