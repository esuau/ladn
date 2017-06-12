package fr.ladn.carsharingclub.ing1.model;

/**
 * The custom sorting modes for the operation list.
 */
public enum OperationSortingMode {

    /**
     * The default sorting manner.
     */
    DEFAULT,

    /**
     * Sorting by failure type only.
     */
    FAILURE_TYPE,

    /**
     * Sorting by priority level only.
     */
    PRIORITY_LEVEL,

    /**
     * Sorting by total estimated time only.
     */
    ESTIMATED_TIME,

    /**
     * Sorting by vehicle entry date only.
     */
    ENTRY_DATE
}
