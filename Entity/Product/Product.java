package Entity.Product;

public abstract class Product {
    private static int counter = 0;
    private final int productId;
    private String productName;
    private double productPrice;
    private int productQty;
    private String productDescription;
    private String addedDate;
    private String expiredDate;
    private String supplierID;

    // Constructor
    public Product(String productName, double productPrice, int productQty,
            String addedDate, String expiredDate, String supplierID) {
        this.productId = ++counter;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.addedDate = addedDate;
        this.expiredDate = expiredDate;
        this.supplierID = supplierID;
    }

    private static final String ADMIN_PASSWORD = "admin123"; // Secure this in a real system

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

    public String getExpiredDate() {
        return expiredDate;
    }

    public String getSupplierID() {
        return supplierID;
    }
    // Setters with authentication
    public void setProductName(String productName, String password) {
        if (isAuthenticated(password) && !productName.isEmpty()) {
            this.productName = productName;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid input.");
        }
    }

    public void setProductPrice(double productPrice, String password) {
        if (isAuthenticated(password) && productPrice >= 0) {
            this.productPrice = productPrice;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid price.");
        }
    }

    public void setProductQty(int productQty, String password) {
        if (isAuthenticated(password) && productQty >= 0) {
            this.productQty = productQty;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid quantity.");
        }
    }

    public void setProductDescription(String productDescription, String password) {
        if (isAuthenticated(password)) {
            this.productDescription = productDescription;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    public void setExpiredDate(String expiredDate, String password) {
        if (isAuthenticated(password)) {
            this.expiredDate = expiredDate;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    public void setSupplierID(String supplierID, String password) {
        if (isAuthenticated(password)) {
            this.supplierID = supplierID;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    // Authentication method
    protected boolean isAuthenticated(String password) {
        return ADMIN_PASSWORD.equals(password);
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName +
                ", productPrice=" + productPrice + ", productQty=" + productQty +
                ", productDescription=" + productDescription + ", addedDate=" + addedDate +
                ", expiredDate=" + expiredDate + ", supplierID=" + supplierID + "]";
    }
}
