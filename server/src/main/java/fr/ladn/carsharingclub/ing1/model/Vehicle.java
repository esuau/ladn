package fr.ladn.carsharingclub.ing1.model;

import java.io.Serializable;

/**
 * The class Vehicle.
 * Includes data on the vehicle entering the workshop.
 */
public class Vehicle implements Serializable {

    /** The ID of the vehicle. */
    private int id;

    /** The number of the license plate of the vehicle. */
    private String registrationNumber;

    /** The branf of the vehicle. */
    private String brand;

    /** The name of the vehicle manufacturer. */
    private String manufacturer;

    /** The version of the vehicle. */
    private String version;

    /**
     * The default constructor of the vehicle.
     * Allows serialization.
     */
    public Vehicle() { }

    /**
     * Instantiates a new vehicle.
     *
     * @param registrationNumber the license plate number.
     * @param brand              the brand of the vehicle.
     * @param manufacturer       the manufacturer of the vehicle.
     * @param version            the version of the vehicle.
     */
    public Vehicle(String registrationNumber, String brand, String manufacturer, String version) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.manufacturer = manufacturer;
        this.version = version;
    }
    
     public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Instantiates a new vehicle with ID.
     *
     * @param id                 the identifier of the vehicle.
     * @param registrationNumber the license plate number.
     * @param brand              the brand of the vehicle.
     * @param manufacturer       the manufacturer of the vehicle.
     * @param version            the version of the vehicle.
     */
    public Vehicle(int id, String registrationNumber, String brand, String manufacturer, String version) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.manufacturer = manufacturer;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
