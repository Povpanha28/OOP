package Entity.Product;

import java.util.Objects;

public abstract class Product {
    private static int counter = 0;
    private final int productId;
    private String productName;
    private double productPrice;
    private int productQty;
    private String productDescription;

    private static final String ADMIN_PASSWORD = "admin123"; // Secure this in a real system

    // Constructor
    public Product(String productName, double productPrice, int productQty) {
        this.productId = ++counter;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQty = productQty;
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

    // Secure Setters with Exception Handling
    public void setProductName(String productName, String password){
        if (!isAuthenticated(password)) {
            System.out.println("Wrong!");
        }
        if (productName == null || productName.isEmpty()) {
            System.out.println("Wrong!");
        }
        this.productName = productName;
    }

    public void setProductPrice(double productPrice, String password) {
        if (!isAuthenticated(password)) {
            System.out.println("Wrong!");
        }
        if (productPrice < 0) {
            System.out.println("Wrong!");
        }
        this.productPrice = productPrice;
    }

    public void setProductQty(int productQty, String password){
        if (!isAuthenticated(password)) {
            System.out.println("Wrong!");
        }
        if (productQty < 0) {
            System.out.println("Wrong!");
        }
        this.productQty = productQty;
    }

    protected void setProductDescription(String productDescription, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            System.out.println("Wrong!");
        }
        this.productDescription = productDescription;
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
               Objects.equals(productName, product.productName);
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName +
               ", productPrice=" + productPrice + ", productQty=" + productQty +
               ", productDescription=" + productDescription + "]";
    }
}