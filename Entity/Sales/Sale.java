package Entity.Sales;

import java.util.HashMap;

public class Sale {
    private static int counter = 0;
    private final int saleID;
    private int customerID;
    private int productID;
    private int amountOfProduct;
    protected double totalPrice;
    private String saleDate;

    // HashMap to store all sales by saleID for fast lookup
    private static HashMap<Integer, Sale> salesMap = new HashMap<>();

    private static final String ADMIN_PASSWORD = "admin123";

    public Sale(int customerID, int productID, int amountOfProduct, double totalPrice){
        if (customerID <= 0 || productID <= 0) {
            System.out.println("Customer ID and Product ID must be greater than zero.");
        }
        if (amountOfProduct <= 0) {
            System.out.println("Amount of product must be greater than zero.");
        }
        if (amountOfProduct > 100) { // Example stock limit
            System.out.println("Amount of product cannot exceed 100.");
        }
        this.saleID = ++counter;
        this.customerID = customerID;
        this.productID = productID;
        this.amountOfProduct = amountOfProduct;
        this.totalPrice = totalPrice;

        // Add the new sale to the HashMap
        salesMap.put(this.saleID, this);
    }

    // Static method to retrieve a sale by ID
    public static Sale getSaleByID(int saleID){
        if (!salesMap.containsKey(saleID)) {
            System.out.println("Sale with ID " + saleID + " not found.");
        }
        return salesMap.get(saleID);
    }

    // Static method to get all sales
    public static HashMap<Integer, Sale> getAllSales() {
        return salesMap;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    // Secure Setters - Only Admins Can Modify Data
    public void setAmountOfProduct(int amount, String password){
        if (!isAuthorized(password)) {
            System.out.println("Unauthorized access: Only admins can change amount of product.");
        }
        if (amount <= 0) {
            System.out.println("Amount of product must be greater than zero.");
        }
        this.amountOfProduct = amount;
    }

    public void setTotalPrice(int price, String password){
        if (!isAuthorized(password)) {
            System.out.println("Unauthorized access: Only admins can change total price.");
        }
        if (price < 0) {
            System.out.println("Total price cannot be negative.");
        }
        this.totalPrice = price;
    }

    public void setSaleDate(String saleDate, String password){
        if (!isAuthorized(password)) {
            System.out.println("Unauthorized access: Only admins can change sale date.");
        }
        this.saleDate = saleDate;
    }

    // Helper method to verify authorization
    private boolean isAuthorized(String password) {
        return ADMIN_PASSWORD.equals(password);
    }

    public void setCustomerID(int customerID, String password){
        if (!isAuthorized(password)) {
            System.out.println("Unauthorized access: Only admins can change customer ID.");
        }
        this.customerID = customerID;
    }

    public void setProductID(int productID, String password){
        if (!isAuthorized(password)) {
            System.out.println("Unauthorized access: Only admins can change product ID.");
        }
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "Sale [saleID=" + saleID + ", customerID=" + customerID + ", productID=" + productID
                + ", amountOfProduct=" + amountOfProduct + ", totalPrice=" + totalPrice + ", saleDate=" + saleDate
                + "]";
    }

    public void processSale() {
        System.out.println("Processing sale. Total price: " + calculateTotalPrice());
    }

    public double calculateTotalPrice() {
        return totalPrice; // Default implementation
    }

    public void setDiscountRate(double d) {
        throw new UnsupportedOperationException("Unimplemented method 'setDiscountRate'");
    }
}
