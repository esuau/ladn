package fr.ladn.carsharingclub.ing1.model;

import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * Part business object
 */
public class Technician implements Serializable {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Technician.class.getName());

    /** The identifier of the user. */
    private int id;

    /** The password of the user. */
    private String password, lastName, phone, rights;

    /** The first name of the technician. */
    private String firstName;

    /**
     * The default constructor.
     * Allows serialization.
     * Never actually used.
     */
    public Technician() { }

    public Technician(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public Technician(String firstName, String lastName, String phone, String password, String rights) {
        logger.info("Creating new instance of Technician...");
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
        this.rights = rights;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getRights() {
        return rights;
    }
}