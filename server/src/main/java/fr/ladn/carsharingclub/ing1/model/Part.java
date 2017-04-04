package fr.ladn.carsharingclub.ing1.model;

import org.apache.log4j.Logger;

/**
 * Part business object
 */
public class Part {

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
     * Default constructor.
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

    /**
     * Gets part ID.
     *
     * @return the identifier of the part.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets part name.
     *
     * @return the reference of the part.
     */
    public String getReference() {
        return reference;
    }

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
     * @param provider The provider of the part.
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * Gets part cost or price.
     *
     * @return cost of a part.
     */
    public float getPrice() {
        return price;
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
     * @param availableQuantity The updated available quantity of the part.
     */
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

}
