package fr.ladn.carsharingclub.ing1.model;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The list of the operations
 */
public class OperationList implements Serializable {

    /** The list of the operations. */
    private SortedSet<Operation> elements = new TreeSet<Operation>() {
        public int compare(Operation o1, Operation o2) {

            // Comparison by failure type.
            if (o1.getBiggestFailure().getType().equals(o2.getBiggestFailure().getType())) {

                // Comparison by priority level.
                if (o1.getPriority().equals(o2.getPriority())) {

                    // Comparison by estimated time of the operation.
                    if (o1.getTotalEstimatedDuration().equals(o2.getTotalEstimatedDuration())) {

                        // Comparison by entry date of the vehicle.
                        return o1.getDateEntry().compareTo(o2.getDateEntry());
                    } else {
                        return o1.getTotalEstimatedDuration().compareTo(o2.getTotalEstimatedDuration());
                    }
                } else {
                    return Integer.compare(o1.getPriority().getPriorityLevel(), o2.getPriority().getPriorityLevel());
                }
            } else {
                return Integer.compare(o1.getBiggestFailure().getType().getPriority(), o2.getBiggestFailure().getType().getPriority());
            }
        }
    };

    /**
     * Default constructor.
     * Allows serialization.
     */
    OperationList() { }

    /**
     * Initializes the list of operations.
     *
     * @param elements the elements list.
     */
    public OperationList(SortedSet<Operation> elements) {
        this.elements = elements;
    }

    public SortedSet<Operation> getElements() {
        return elements;
    }

    public void setElements(SortedSet<Operation> elements) {
        this.elements = elements;
    }

}
