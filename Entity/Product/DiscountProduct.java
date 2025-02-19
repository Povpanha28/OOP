package Entity.Product;

public class DiscountProduct extends Product {
    private double discountPercentage; // Discount percentage (0-100)

    // Constructor
    public DiscountProduct(String productName, double productPrice, int productQty, 
                           String addedDate, String expiredDate, String supplierID, 
                           double discountPercentage) {
        super(productName, productPrice, productQty, addedDate, expiredDate, supplierID);
        this.setDiscountPercentage(discountPercentage, "admin123"); // Ensures validation
    }

    // Implement abstract method from Product class
    @Override
    public String getProductType() {
        return "DiscountProduct";
    }

    // Get Discount Percentage
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    // Set Discount Percentage with authentication
    public void setDiscountPercentage(double discountPercentage, String password) {
        if (isAuthenticated(password)) {
            if (discountPercentage >= 0 && discountPercentage <= 100) {
                this.discountPercentage = discountPercentage;
            } else {
                System.out.println("Invalid discount. It must be between 0 and 100.");
            }
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    // Get Final Price after discount
    public double getFinalPrice() {
        return getProductPrice() * (1 - discountPercentage / 100);
    }

    // Override setter to ensure price updates properly
    @Override
    public void setProductPrice(double productPrice, String password) {
        if (isAuthenticated(password) && productPrice >= 0) {
            super.setProductPrice(productPrice, password);
        } else {
            System.out.println("Access Denied: Unauthorized or invalid price.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Discount=" + discountPercentage + "%, FinalPrice=" + getFinalPrice();
    }
}
