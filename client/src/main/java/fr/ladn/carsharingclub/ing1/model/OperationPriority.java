package fr.ladn.carsharingclub.ing1.model;

/**
 * The priority levels of an operation.
 *
 * @see Operation
 */
public enum OperationPriority {

    /**
     * The highest priority level.
     * The operation must be executed immediately.
     */
    URGENT(1),

    /**
     * An important priority level.
     * Must be repaired as soon a possible.
     */
    CRITICAL(2),

    /**
     * A major priority level.
     */
    MAJOR(3),

    /**
     * A normal priority level.
     */
    NORMAL(4),

    /**
     * A minor priority level.
     * The repair work can be delayed.
     */
    MINOR(5);

    /**
     * The numeric priority level, rated from 1 to 5.
     * 1 corresponds to a urgent priority level.
     * 5 corresponds to a minor priority level.
     */
    private final int priorityLevel;

    /**
     * The french name of the priority level.
     */
    private final String priorityLabel;

    /**
     * Instantiates on operation priority based on its numeric priority level.
     *
     * @param priorityLevel the numeric priority level.
     */
    OperationPriority(int priorityLevel) {
        this.priorityLevel = priorityLevel;
        switch (priorityLevel) {
            case 1:
                this.priorityLabel = "Urgent";
                break;
            case 2:
                this.priorityLabel = "Critique";
                break;
            case 3:
                this.priorityLabel = "Majeur";
                break;
            case 4:
                this.priorityLabel = "Normal";
                break;
            case 5:
                this.priorityLabel = "Mineur";
                break;
            default:
                this.priorityLabel = "";
        }
    }

    /**
     * Getter for the numeric priority level.
     *
     * @return the corresponding numeric priority level.
     */
    public int getPriorityLevel() {
        return priorityLevel;
    }

    /**
     * The Getter fot the priority level name.
     *
     * @return the French name of the priority level.
     */
    public String getPriorityLabel() {
        return priorityLabel;
    }
}
