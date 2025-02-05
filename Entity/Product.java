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

    // Constructor
    public Product(String productName, String addedDate, int productQty, String expiredDate) {
        this.productId = ++counter; // Assigns unique ID
        this.productName = productName;
        this.addedDate = addedDate;
        this.productQty = productQty;
        this.expiredDate = expiredDate;
    }

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

    public boolean isProductStatus() {
        return productStatus;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    // Setters (with validation where needed)
    public void setProductName(String productName) {
        if (!productName.isEmpty()) {
            this.productName = productName;
        }
    }

    public void setProductPrice(double productPrice) {
        if (productPrice >= 0) {
            this.productPrice = productPrice;
        }
    }

    public void setProductQty(int productQty) {
        if (productQty >= 0) {
            this.productQty = productQty;
        }
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
}
