package fr.ladn.carsharingclub.ing1.utils;

import java.io.Serializable;

/**
 * The serializable object containing the part and the CRUD to be performed by the server.
 */
public class Container<T> implements Serializable {

    /** The CRUD. */
    private CRUD CRUD;

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
     * @param o   the CRUD to be performed by the server.
     * @param obj the object to be transferred to the server.
     */
    public Container(CRUD o, T obj) {
        this.CRUD = o;
        this.object = obj;
    }

    /**
     * Gets the operator.
     * @return the CRUD to be performed.
     */
    public CRUD getCRUD() {
        return CRUD;
    }

    /**
     * Sets the CRUD.
     * @param CRUD the new CRUD.
     */
    public void setCRUD(CRUD CRUD) {
        this.CRUD = CRUD;
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
