package fr.ladn.carsharingclub.ing1.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The list of the operations
 */
public class OperationList implements Serializable {

    /**
     * The sorting mode.
     * A null sorting mode triggers the default sorting method.
     */
    private OperationSortingMode mode;

    /** The list of operations. */
    private SortedSet<Operation> elements = new TreeSet<>(new Comparator<Operation>() {

        /**
         * The sorting method.
         *
         * @param o1 an operation.
         * @param o2 an other operation.
         * @return the comparator value, negative if less, positive if greater.
         */
        public int compare(Operation o1, Operation o2) {
            switch (mode) {
                case FAILURE_TYPE:
                    return compareFailureTypes(o1, o2);
                case PRIORITY_LEVEL:
                    return comparePriorityLevels(o1, o2);
                case ESTIMATED_TIME:
                    return compareEstimatedTimes(o1, o2);
                case ENTRY_DATE:
                    return compareEntryDates(o2, o1);
                default:
                    // The default sorting method.
                    if (compareFailureTypes(o1, o2) == 0) {
                        if (comparePriorityLevels(o1, o2) == 0) {
                            if (compareEstimatedTimes(o1, o2) == 0) {
                                return compareEntryDates(o2, o1);
                            }
                            return compareEstimatedTimes(o1, o2);
                        }
                        return comparePriorityLevels(o1, o2);
                    }
                    return compareFailureTypes(o1, o2);
            }
        }
    });

    /**
     * Default constructor.
     * Initializes the sorting mode to default.
     */
    public OperationList() {
        this.mode = OperationSortingMode.DEFAULT;
    }

    /**
     * Initializes the operation list with default sorting method.
     *
     * @param elements list of operations.
     */
    public OperationList(SortedSet<Operation> elements) {
        this.elements = elements;
    }

    /**
     * Initializes the list of operations.
     * Activates all sorting criteria.
     *
     * @param elements list of operations.
     */
    public OperationList(OperationSortingMode mode, SortedSet<Operation> elements) {
        this.mode = mode;
        this.elements = elements;
    }

    /**
     * Compares the most important failures of two operations.
     *
     * @param o1 an operation.
     * @param o2 the other operation to compare.
     * @return the comparator value, negative if less, positive if greater.
     */
    private int compareFailureTypes(Operation o1, Operation o2) {
        return Integer.compare(o1.getBiggestFailure().getType().getPriority(), o2.getBiggestFailure().getType().getPriority());
    }

    /**
     * Compares by priority level.
     *
     * @param o1 an operation.
     * @param o2 the other operation to compare.
     * @return the comparator value, negative if less, positive if greater.
     */
    private int comparePriorityLevels(Operation o1, Operation o2) {
        return Integer.compare(o1.getPriority().getPriorityLevel(), o2.getPriority().getPriorityLevel());
    }

    /**
     * Compares the total estimated times of two operations.
     *
     * @param o1 an operation.
     * @param o2 the other operation to compare.
     * @return the comparator value, negative if less, positive if greater.
     */
    private int compareEstimatedTimes(Operation o1, Operation o2) {
        return Integer.compare(o1.getTotalEstimatedDuration(), o2.getTotalEstimatedDuration());
    }

    /**
     * @param o1 an operation.
     * @param o2 the other operation to compare.
     * @return the comparator value, negative if less, positive if greater.
     */
    private int compareEntryDates(Operation o1, Operation o2) {
        return o1.getDateEntry().compareTo(o2.getDateEntry());
    }

    /**
     * Adds operations to the list.
     *
     * @param operation the operation to add.
     */
    public synchronized void add(Operation operation) {
        elements.add(operation);
    }

    public OperationSortingMode getMode() {
        return mode;
    }

    public void setMode(OperationSortingMode mode) {
        this.mode = mode;
    }

    public synchronized SortedSet<Operation> getElements() {
        return elements;
    }

    public synchronized void setElements(SortedSet<Operation> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("\n");
        str.append(String.format("%-5s %-30s %-5s %10s\n", "ID", "Category", "Duration", "Entry date"));
        str.append("--------------------------------------------------\n");
        for (Operation operation : elements) {
            str.append(String.format("%-5s %-30s %-5s %10s\n", operation.getId(), operation.getBiggestFailure().getType().getName(), operation.getTotalEstimatedDuration(), operation.getDateEntry()));
        }
        return str.toString();
    }

}
