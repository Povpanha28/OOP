package Entity;

// Purpose: Manages the details of items available for sale

public class Product {
    private static int counter = 0; // Static counter for unique IDs
    public int productId; // Instance variable for unique ID
    String productName;
    double productPrice;
    int productQty;
    String productDescription;
    boolean productStatus;
    String addedDate;
    String expiredDate;

    // Constructor
    public Product(String productName, String addedDate, int productQty, String expiredDate) {
        this.productId = ++counter; // Assign unique ID to each instance
        this.productName = productName;
        this.addedDate = addedDate;
        this.productQty = productQty;
        this.expiredDate = expiredDate;
    }


    // Getters
    public double getPrice() {
        return productPrice;
    }

    // Setters
    public double setPrice(double price) {
        return this.productPrice = price;
    }


}