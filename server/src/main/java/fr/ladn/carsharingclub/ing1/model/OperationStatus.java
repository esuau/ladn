package fr.ladn.carsharingclub.ing1.model;

/**
 * The status of an operation on a vehicle.
 */
public enum OperationStatus {
    UNDIAGNOSED("non diagnostiqué"),
    DIAGNOSED("diagnostiqué"),
    INPROGRESS("réparation en cours"),
    REPARED("réparé");

    /** The status as a string. */
    private final String status;

    /**
     * @param status the corresponding status in French.
     */
    OperationStatus(final String status) {
        this.status = status;
    }

    /**
     * @return the string of the status.
     */
    @Override
    public String toString() {
        return status;
    }
}
