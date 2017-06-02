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

    /** The technician first name. */
    private String firstName;

    /** The technician last name. */
    private String lastName;

    /** The technician phone number. */
    private String phoneNumber;

    /** The password of the user. */
    private String password;

    private TechnicianRights rights;

    /**
     * The default constructor.
     * Allows serialization.
     * Never actually used.
     */
    public Technician() { }

    /**
     * Instantiates a technician with its ID.
     *
     * @param id          the technician ID.
     * @param firstName   the technician first name.
     * @param lastName    the technician last name.
     * @param phoneNumber the technician phone number.
     * @param password    the technician password.
     * @param rights      the technician rights.
     */
    public Technician(int id, String firstName, String lastName, String phoneNumber, String password, TechnicianRights rights) {
        logger.info("Creating new instance of Technician...");
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.rights = rights;
    }

    /**
     * Instantiates a technician without ID and phone number.
     *
     * @param firstName the technician first name.
     * @param lastName  the technician last name.
     * @param password  the technician password.
     * @param rights    the technician rights.
     */
    public Technician(String firstName, String lastName, String password, TechnicianRights rights) {
        logger.info("Creating new instance of Technician...");
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.rights = rights;
    }

    /**
     * Instantiates a technician without ID.
     *
     * @param firstName   the technician first name.
     * @param lastName    the technician last name.
     * @param phoneNumber the technician phone number.
     * @param password    the technician password.
     * @param rights      the technician rights.
     */
    public Technician(String firstName, String lastName, String phoneNumber, String password, TechnicianRights rights) {
        logger.info("Creating new instance of Technician...");
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.rights = rights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public TechnicianRights getRights() {
        return rights;
    }

    public void setRights(TechnicianRights rights) {
        this.rights = rights;
    }
}