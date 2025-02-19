package Entity.Sales;

import java.util.HashMap;
import java.util.Collection;

public class Sale {
    private static int counter = 0;
    private final int saleID;
    private int customerID;
    private int productID;
    private int amountOfProduct;
    private int totalPrice;
    private String saleDate;

    // HashMap to store all sales by saleID for fast lookup
    private static HashMap<Integer, Sale> salesMap = new HashMap<>();

    private static final String ADMIN_PASSWORD = "admin123";

    public Sale(int customerID, int productID, int amountOfProduct) {
        if (customerID <= 0 || productID <= 0) {
            throw new IllegalArgumentException("Customer ID and Product ID must be valid positive numbers.");
        }
        if (amountOfProduct <= 0) {
            throw new IllegalArgumentException("Amount of product must be greater than zero.");
        }
        this.saleID = ++counter;
        this.customerID = customerID;
        this.productID = productID;
        this.amountOfProduct = amountOfProduct;

        // Add the new sale to the HashMap
        salesMap.put(this.saleID, this);
    }

    // Static method to retrieve a sale by ID
    public static Sale getSaleByID(int saleID) {
        return salesMap.get(saleID);
    }

    // Static method to get all sales
    public static Collection<Sale> getAllSales() {
        return salesMap.values();
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

    // Secure Setters - Only Admins Can Modify Data
    public void setAmountOfProduct(int amount, String password) {
        if (!isAuthorized(password)) {
            throw new SecurityException("Unauthorized access: Only admins can change amount of product.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount of product must be greater than zero.");
        }
        this.amountOfProduct = amount;
    }

    public void setTotalPrice(int price, String password) {
        if (!isAuthorized(password)) {
            throw new SecurityException("Unauthorized access: Only admins can change total price.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Total price cannot be negative.");
        }
        this.totalPrice = price;
    }

    public void setSaleDate(String saleDate, String password) {
        if (!isAuthorized(password)) {
            throw new SecurityException("Unauthorized access: Only admins can change sale date.");
        }
        this.saleDate = saleDate;
    }

    // Helper method to verify authorization
    private boolean isAuthorized(String password) {
        return ADMIN_PASSWORD.equals(password);
    }


    @Override
    public String toString() {
        return "Sale [saleID=" + saleID + ", customerID=" + customerID + ", productID=" + productID
                + ", amountOfProduct=" + amountOfProduct + ", totalPrice=" + totalPrice + ", saleDate=" + saleDate
                + "]";
    }
    
}