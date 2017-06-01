package fr.ladn.carsharingclub.ing1.model;

import java.io.Serializable;
import java.time.Duration;

/**
 * The class Failure.
 * Includes data on a failure.
 */
public class Failure implements Serializable {

    /** The identifier of the failure. */
    private int id;

    /** The common name of the failure. */
    private String name;

    /** The type of the failure on the vehicle. */
    private FailureType type;

    /** The instructions related to the repair work. */
    private String instructions;

    /** The estimated time for the repair work. */
    private Duration estimatedTime;

    /** Default constructor */
    Failure() { }

    /**
     * The commonly used failure constructor.
     * @param name          the name of the failure.
     * @param type          the type of failure, based on its location.
     * @param instructions  the instructions corresponding to the repair work.
     * @param estimatedTime the estimated time to complete the repair work.
     */
    public Failure(String name, FailureType type, String instructions, Duration estimatedTime) {
        this.name = name;
        this.type = type;
        this.instructions = instructions;
        this.estimatedTime = estimatedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FailureType getType() {
        return type;
    }

    public void setType(FailureType type) {
        this.type = type;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Duration getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
