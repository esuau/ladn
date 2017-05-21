package fr.ladn.carsharingclub.ing1.model;

/**
 * The class Failure.
 * Includes data on a failure.
 */
public class Failure {

    /** The identifier of the failure. */
    private int id;

    /** The common name of the failure. */
    private String name;

    /** The type of the failure. */
    private String type;

    /** The location of the failure on the vehicle. */
    private FailureLocation location;

    /** Default constructor */
    public Failure() {
    }

    /**
     * The commonly used failure constructor.
     * @param name     the name of the failure.
     * @param type     the type of failure.
     * @param location the location of the failure.
     */
    public Failure(String name, String type, FailureLocation location) {
        this.name = name;
        this.type = type;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FailureLocation getLocation() {
        return location;
    }

    public void setLocation(FailureLocation location) {
        this.location = location;
    }
}
