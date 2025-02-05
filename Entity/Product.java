package Entity;

// Purpose: Manages the details of items available for sale

public class Product {
    private static int counter = 0; // Ensures unique IDs cannot be altered
    private final int productId; // Unique, immutable identifier
    private String productName;
    private double productPrice;
    private int productQty;
    private String productDescription;
    private boolean productStatus;
    private String addedDate;
    private String expiredDate;
    
    private static final String ADMIN_PASSWORD = "admin123"; // Hardcoded for demo, should be stored securely.

    // Constructor
    public Product(String productName, String addedDate, int productQty, String expiredDate) {
        this.productId = ++counter; // Assigns unique ID
        this.productName = productName;
        this.addedDate = addedDate;
        this.productQty = productQty;
        this.expiredDate = expiredDate;
    }

    // Getters (No restriction on reading data)
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductQty() {
        return productQty;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    // Setters with Access Control
    public void setProductName(String productName, String password) {
        if (authenticate(password) && !productName.isEmpty()) {
            this.productName = productName;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid input.");
        }
    }

    public void setProductPrice(double productPrice, String password) {
        if (authenticate(password) && productPrice >= 0) {
            this.productPrice = productPrice;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid price.");
        }
    }

    public void setProductQty(int productQty, String password) {
        if (authenticate(password) && productQty >= 0) {
            this.productQty = productQty;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid quantity.");
        }
    }

    public void setProductDescription(String productDescription, String password) {
        if (authenticate(password)) {
            this.productDescription = productDescription;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    public void setProductStatus(boolean productStatus, String password) {
        if (authenticate(password)) {
            this.productStatus = productStatus;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    public void setExpiredDate(String expiredDate, String password) {
        if (authenticate(password)) {
            this.expiredDate = expiredDate;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    // Authentication Method
    private boolean authenticate(String password) {
        return ADMIN_PASSWORD.equals(password);
    }
}
