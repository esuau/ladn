package fr.ladn.carsharingclub.ing1.model;

/**
 * Defines the priority levels of an operation.
 */
public enum Priority {

    /**
     * Urgent priority level.
     */
    URGENT(5, "URGENT"),

    /**
     * Critical priority level.
     */
    CRITICAL(4, "CRITIQUE"),

    /**
     * Major priority level.
     */
    MAJOR(3, "MAJEUR"),

    /**
     * Normal priority level.
     */
    NORMAL(2, "NORMAL"),

    /**
     * Minor priority level.
     */
    MINOR(1, "MINEUR");

    /** The number value of the priority. */
    private final int value;

    /** The string label of the priority. */
    private final String label;

    Priority(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
