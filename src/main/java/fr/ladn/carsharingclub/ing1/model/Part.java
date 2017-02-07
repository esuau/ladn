
package fr.ladn.carsharingclub.ing1.model;

/**
 * Part business object
 */
public class Part {

    private int id;
    private String reference;
    private String provider;
    private int availableQuantity;
    private float price;

    /**
     * Default constructor
     *
     * @param id                of the part
     * @param reference         (name) of the part
     * @param provider          or supplier of the part
     * @param availableQuantity of this part reference in the stock
     * @param price             (cost) of the part
     */
    public Part(int id, String reference, String provider, int availableQuantity, float price) {
        this.id = id;
        this.reference = reference;
        this.provider = provider;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    /**
     * Custom constructor (ID is not defined)
     *
     * @param reference         (name) of the part
     * @param provider          or supplier of the part
     * @param availableQuantity of this part reference in the stock
     * @param price             or cost of the part
     */
    public Part(String reference, String provider, int availableQuantity, float price) {
        this.reference = reference;
        this.provider = provider;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    /**
     * Gets part ID.
     *
     * @return id of a part
     */
    public int getId() {
        return id;
    }

    /**
     * Gets part name.
     *
     * @return reference of a part
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
     * @return the provider name of a part
     */
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * Gets part cost or price.
     *
     * @return cost of a part
     */
    public float getPrice() {
        return price;
    }

    /**
     * Gets the available quantity of a part.
     *
     * @return the number of available items for a given part
     */
    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }


}
