
package fr.ladn.carsharingclub.ing1.model;


public class Part {
    private int id;
    private String reference;
    private String provider;
    private int availableQuantity;
    private float price;

    public Part(int id, String reference, String provider, int availableQuantity, float price) {
        this.id = id;
        this.reference = reference;
        this.provider = provider;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    public Part(String reference, String provider, int availableQuantity, float price) {
        this.reference = reference;
        this.provider = provider;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public float getPrice() {
        return price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }


}
