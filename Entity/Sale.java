package Entity;

public class Sale {
    private static int counter = 0; // Static counter for unique IDs
    private final int saleID;  // Unique sale ID (cannot be changed)
    private int customerID; 
    private int productID;
    private int amountOfProduct;
    private int totalPrice;
    private String saleDate;

    // Constructor
    public Sale(int customerID, int productID, int amountOfProduct) {
        if (customerID <= 0 || productID <= 0) {
            throw new IllegalArgumentException("Customer ID and Product ID must be valid positive numbers.");
        }
        if (amountOfProduct <= 0) {
            throw new IllegalArgumentException("Amount of product must be greater than zero.");
        }

        this.saleID = ++counter; // Assign unique ID to each instance
        this.customerID = customerID;
        this.productID = productID;
        this.amountOfProduct = amountOfProduct;
    }

    // Getters
    public int getSaleID() {
        return saleID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getProductID() {
        return productID;
    }

    public int getAmountOfProduct() {
        return amountOfProduct;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    // Setters with Access Control
    public void setAmountOfProduct(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount of product must be greater than zero.");
        }
        this.amountOfProduct = amount;
    }

    public void setTotalPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Total price cannot be negative.");
        }
        this.totalPrice = price;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    // Display sale details
    public void displaySaleInfo() {
        System.out.println("Sale ID: " + saleID);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Product ID: " + productID);
        System.out.println("Amount: " + amountOfProduct);
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Sale Date: " + saleDate);
    }
}
