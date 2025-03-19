package Entity.Product;

import java.util.Objects;

public abstract class Product {
    private static int counter = 0;
    private final int productId;
    private String productName;
    private double productPrice;
    private int productQty;
    private String productDescription;
    private String addedDate;
    private String supplierID;

    private static final String ADMIN_PASSWORD = "admin123"; // Secure this in a real system

    // Constructor
    public Product(String productName, double productPrice, int productQty,
                   String addedDate, String supplierID) {
        this.productId = ++counter;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.addedDate = addedDate;
        this.supplierID = supplierID;
    }

    // Abstract method (forcing subclasses to define product type)
    public abstract String getProductType();

    // Getters
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

    public String getAddedDate() {
        return addedDate;
    }

    public String getSupplierID() {
        return supplierID;
    }

    // Secure Setters with Exception Handling
    public void setProductName(String productName, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized modification of product name.");
        }
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Invalid Product Name: Name cannot be empty.");
        }
        this.productName = productName;
    }

    public void setProductPrice(double productPrice, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized modification of product price.");
        }
        if (productPrice < 0) {
            throw new IllegalArgumentException("Invalid Product Price: Price cannot be negative.");
        }
        this.productPrice = productPrice;
    }

    public void setProductQty(int productQty, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized modification of product quantity.");
        }
        if (productQty < 0) {
            throw new IllegalArgumentException("Invalid Product Quantity: Quantity cannot be negative.");
        }
        this.productQty = productQty;
    }

    protected void setProductDescription(String productDescription, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized modification of product description.");
        }
        this.productDescription = productDescription;
    }

    public void setSupplierID(String supplierID, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized modification of supplier ID.");
        }
        this.supplierID = supplierID;
    }

    // Authentication method
    protected boolean isAuthenticated(String password) {
        return ADMIN_PASSWORD.equals(password);
    }

    // Overriding equals() to compare products based on essential attributes
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Double.compare(product.productPrice, productPrice) == 0 &&
               productQty == product.productQty &&
               Objects.equals(productName, product.productName) &&
               Objects.equals(addedDate, product.addedDate) &&
               Objects.equals(supplierID, product.supplierID);
    }


    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName +
               ", productPrice=" + productPrice + ", productQty=" + productQty +
               ", productDescription=" + productDescription + ", addedDate=" + addedDate +
               ", supplierID=" + supplierID + "]";
    }
}
