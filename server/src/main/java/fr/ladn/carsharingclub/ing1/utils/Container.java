package fr.ladn.carsharingclub.ing1.utils;

import java.io.Serializable;

/**
 * The serializable object containing the part and the operation to be performed by the server.
 */
public class Container<T> implements Serializable {

    /** The operation. */
    private Operation operation;

    /** The object to transfer. */
    private T object;

    /**
     * The default constructor.
     * Never actually used in the project.
     * Allows serialization.
     */
    public Container() { }

    /**
     * The custom constructor.
     * @param o   the operation to be performed by the server.
     * @param obj the object to be transferred to the server.
     */
    public Container(Operation o, T obj) {
        this.operation = o;
        this.object = obj;
    }

    /**
     * Gets the operator.
     * @return the operation to be performed.
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Sets the operation.
     * @param operation the new operation.
     */
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    /**
     * Gets the object.
     * @return the object.
     */
    public T getObject() {
        return object;
    }

    /**
     * Sets the object.
     * @param object the new object.
     */
    public void setObject(T object) {
        this.object = object;
    }

}
