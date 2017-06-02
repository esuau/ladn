package fr.ladn.carsharingclub.ing1.model;

import org.apache.log4j.Logger;
import java.io.Serializable;

/**
 * Part business object
 */
public class Part implements Serializable {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Part.class.getName());

    /** The identifier of the part. */
    private int id;

    /** The name or reference of the part. */
    private String reference;

    /** The name of the privoder or supplier of the part. */
    private String provider;

    /** The number of part corresponding to this reference in the stock. */
    private int availableQuantity;

    /** The unit price or cost of the part. */
    private float price;

    /**
     * The default constructor.
     * Never actually used in the project.
     * Allows serialization.
     */
    public Part() { }

    /**
     * Custom constructor.
     * Used to create a part with defined ID.
     *
     * @param id                The identifier of the part.
     * @param reference         The name/reference of the part
     * @param provider          The name of the provider or supplier of the part.
     * @param availableQuantity The number of this part reference in the stock.
     * @param price             The unit cost of the part.
     */
    public Part(int id, String reference, String provider, int availableQuantity, float price) {
        logger.info("Creating new part instance with custom ID...");
        this.id = id;
        this.reference = reference;
        this.provider = provider;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    /**
     * Custom constructor.
     * Used with undefined ID.
     *
     * @param reference         (name) of the part
     * @param provider          or supplier of the part
     * @param availableQuantity of this part reference in the stock
     * @param price             or cost of the part
     */
    public Part(String reference, String provider, int availableQuantity, float price) {
        logger.info("Creating new part instance...");
        this.reference = reference;
        this.provider = provider;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }
    
    
    public Part(int id, int quantity) {
        logger.info("Creating new part instance with the quantity needed for a specific operation");
        this.id = id;
        this.availableQuantity = quantity;
    }

    /**
     * Gets part ID.
     *
     * @return the identifier of the part.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets part ID.
     *
     * @param id the new ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets part name.
     *
     * @return the reference of the part.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets part reference or name.
     *
     * @param reference the new reference.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Gets part provider or supplier.
     *
     * @return the provider name of a part.
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Set provider name.
     *
     * @param provider the provider of the part.
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * Gets the available quantity of the part.
     *
     * @return the number of available items for a given part.
     */
    public int getAvailableQuantity() {
        return availableQuantity;
    }

    /**
     * Updates the available quantity of the part.
     *
     * @param availableQuantity the updated available quantity of the part.
     */
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    /**
     * Gets part cost or price.
     *
     * @return the cost of a part.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets part price.
     *
     * @param price the new price.
     */
    public void setPrice(float price) {
        this.price = price;
    }

}
