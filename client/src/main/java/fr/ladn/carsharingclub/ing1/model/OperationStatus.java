package fr.ladn.carsharingclub.ing1.model;

/**
 * The status of an operation on a vehicle.
 */
public enum OperationStatus {

    /**
     * The vehicle is waiting for a diagnosis of the failure(s).
     */
    UNDIAGNOSED(1, "non diagnostiqué"),
    DIAGNOSED(2, "diagnostiqué"),
    INPROGRESS(3, "réparation en cours"),
    PENDING(4, "réparation suspendue"),
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
    public int getStep() {
        return step;
    }
}
