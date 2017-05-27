package fr.ladn.carsharingclub.ing1.model;

/**
 * The status of an operation on a vehicle.
 */
public enum OperationStatus {

    /**
     * The vehicle is waiting for a diagnosis of the failure(s).
     */
    UNDIAGNOSED(1, "non diagnostiqué"),

    /**
     * The failures have been diagnosed.
     * The operation is waiting to be performed.
     */
    DIAGNOSED(2, "diagnostiqué"),

    /**
     * The operation is performed by a technician.
     */
    INPROGRESS(3, "réparation en cours"),

    /**
     * The operation is temporally pending after being started.
     * Next status is always "INPROGRESS"
     */
    PENDING(4, "réparation suspendue"),

    /**
     * The operation is completed.
     */
    REPARED(5, "réparé");

    /** The status order. */
    private final int step;

    /** The status as a string. */
    private final String status;

    /**
     * The constructor of OperationStatus.
     * @param status the corresponding status in French.
     */
    OperationStatus(final int step, final String status) {
        this.step = step;
        this.status = status;
    }

    /**
     * @return the string of the status.
     */
    @Override
    public String toString() {
        return status;
    }

    /**
     * @return the status order.
     */
    public int toInt() {
        return step;
    }
}
