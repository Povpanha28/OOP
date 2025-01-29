package Entity;

// Purpose: Manages the details of items available for sale

public class Product {

    private String name;
    private int id;
    private double price;
    private int qty;
    private String description;
    private boolean status;
    private String addedDate;
    private String expiredDate;

    // Constructor
    Product(String name, int id, String addedDate, int qty, String expiredDate) {
        this.name = name;
        this.id = id;
        this.addedDate = addedDate;
        this.qty = qty;
        this.expiredDate = expiredDate;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return status;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
}
