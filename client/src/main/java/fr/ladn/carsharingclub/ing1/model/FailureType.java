package fr.ladn.carsharingclub.ing1.model;

import java.io.Serializable;

/**
 * The type of a failure on a vehicle, based on its location.
 */
public class FailureType implements Serializable {

    /** The priority level related to the failure location. */
    private final int priority;

    /** The french name of a failure location. */
    private final String name;

    /**
     * The default constructor.
     * Do not use.
     */
    public FailureType() {
        this(0, "");
    }

    /**
     * Failure type constructor.
     * @param priority the priority level of the failure.
     * @param name     the name of the failure.
     */
    public FailureType(final int priority, final String name) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }
}
