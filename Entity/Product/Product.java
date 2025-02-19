package Entity.Product;

import java.util.HashMap;

public class Product {
    private static int counter = 0;
    private final int productId;
    private String productName;
    private double productPrice;
    private int productQty;
    // private boolean productStatus;
    private String productDescription;
    private String addedDate;
    private String expiredDate;
    private String supplierID;
    
    private static final String ADMIN_PASSWORD = "admin123";

    // A HashMap to store all products with productId as the key
    private static HashMap<Integer, Product> productDatabase = new HashMap<>();
    
    // Constructor
    public Product(String productName, int productQty,String addedDate, String expiredDate,String supplierID) {
        this.productId = ++counter;
        this.productName = productName;
        this.addedDate = addedDate;
        this.productQty = productQty;
        this.expiredDate = expiredDate;
        this.supplierID = supplierID;

        // Add product to the HashMap automatically upon creation
        productDatabase.put(this.productId, this);
    }

    // Static method to get a product by ID
    public static Product getProductById(int id) {
        return productDatabase.get(id);
    }

    // Static method to remove a product
    public static void removeProduct(int id, String password) {
        if (authenticate(password)) {
            productDatabase.remove(id);
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    // Getters
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getProductPrice() { return productPrice; }
    public int getProductQty() { return productQty; }
    public String getProductDescription() { return productDescription; }
    // public boolean isProductStatus() { return productStatus; }
    public String getAddedDate() { return addedDate; }
    public String getExpiredDate() { return expiredDate; }
    public String getSupplierID() { return supplierID; }

    // Setters with authentication
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

    // public void setProductStatus(boolean productStatus, String password) {
    //     if (authenticate(password)) {
    //         this.productStatus = productStatus;
    //     } else {
    //         System.out.println("Access Denied: Unauthorized.");
    //     }
    // }

    public void setExpiredDate(String expiredDate, String password) {
        if (authenticate(password)) {
            this.expiredDate = expiredDate;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    public void setSupplierID(String supplierID, String password) {
        if (authenticate(password)) {
            this.supplierID = supplierID;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    // Authentication Method
    private static boolean authenticate(String password) {
        return ADMIN_PASSWORD.equals(password);
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
                + ", productQty=" + productQty + ", productDescription=" + productDescription + ", addedDate=" + addedDate + ", expiredDate=" + expiredDate + ", supplierID="
                + supplierID + "]";
    }

}

// ✅ Fast Lookup: O(1) time complexity for retrieving a product.
// ✅ Efficient Removal: O(1) for deleting a product using its ID.
// ✅ Uniqueness: Ensures no duplicate productId exists.
