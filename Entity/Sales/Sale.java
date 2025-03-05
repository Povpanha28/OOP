package Entity.Sales;

public class Sale {
    private static int counter = 0;
    private int saleID;
    private int customerID;
    private int productID;
    private int amountOfProduct;
    protected double totalPrice;
    private String saleDate;

    private static final String ADMIN_PASSWORD = "admin123";

    public Sale(int customerID, int productID, int amountOfProduct, double totalPrice) {
        if (customerID <= 0 || productID <= 0) {
            System.out.println("Customer ID and Product ID must be greater than zero.");
            return; // Stop object creation
        }
        if (amountOfProduct <= 0) {
            System.out.println("Amount of product must be greater than zero.");
            return;
        }
        if (amountOfProduct > 100) {
            System.out.println("Amount of product cannot exceed 100.");
            return;
        }

        this.saleID = ++counter;
        this.customerID = customerID;
        this.productID = productID;
        this.amountOfProduct = amountOfProduct;
        this.totalPrice = totalPrice;
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

    // Secure Setters
    public void setAmountOfProduct(int amount, String password) {
        if (!isAuthorized(password)) {
            System.out.println("Unauthorized access: Only admins can change amount of product.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Amount of product must be greater than zero.");
            return;
        }
        this.amountOfProduct = amount;
    }

    public void setTotalPrice(double price, String password) {
        if (!isAuthorized(password)) {
            System.out.println("Unauthorized access: Only admins can change total price.");
            return;
        }
        if (price < 0) {
            System.out.println("Total price cannot be negative.");
            return;
        }
        this.totalPrice = price;
    }

    public void setSaleDate(String saleDate, String password) {
        if (!isAuthorized(password)) {
            System.out.println("Unauthorized access: Only admins can change sale date.");
            return;
        }
        this.saleDate = saleDate;
    }

    public void setCustomerID(int customerID, String password) {
        if (!isAuthorized(password)) {
            System.out.println("Unauthorized access: Only admins can change customer ID.");
            return;
        }
        this.customerID = customerID;
    }

    public void setProductID(int productID, String password) {
        if (!isAuthorized(password)) {
            System.out.println("Unauthorized access: Only admins can change product ID.");
            return;
        }
        this.productID = productID;
    }

    // Authorization Helper
    private boolean isAuthorized(String password) {
        return ADMIN_PASSWORD.equals(password);
    }

    @Override
    public String toString() {
        return "Sale [saleID=" + saleID + ", customerID=" + customerID + ", productID=" + productID
                + ", amountOfProduct=" + amountOfProduct + ", totalPrice=" + totalPrice + ", saleDate=" + saleDate + "]";
    }

    public void processSale() {
        System.out.println("Processing sale... Total Price: " + calculateTotalPrice());
    }

    public double calculateTotalPrice() {
        return totalPrice; // Default implementation
    }
}
