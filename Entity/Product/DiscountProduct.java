package Entity.Product;

public class DiscountProduct extends Product {
    private double discountPercentage; // Discount percentage (0-100)

    // Constructor
    public DiscountProduct(String productName, double productPrice, int productQty, 
                           String productDescription, String addedDate, String supplierID, 
                           double discountPercentage, String password) throws UnauthorizedAccessException {
        super(productName, productPrice, productQty, addedDate, supplierID);
        this.discountPercentage = discountPercentage;
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
    public void setDiscountPercentage(double discountPercentage, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            System.out.println("Unauthorized access. Please provide the correct password.");
            return;
        }
        if (discountPercentage < 0 || discountPercentage > 100) {
            System.out.println("Invalid discount. It must be between 0 and 100.");
            return;
        }
        this.discountPercentage = discountPercentage;
    }

    // Get Final Price after discount
    public double getFinalPrice() {
        return getProductPrice() * (1 - discountPercentage / 100);
    }

    // Get Total Price after discount
    public double getTotalPrice() {
        return getFinalPrice() * getProductQty();
    }

    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Same reference
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Null or different class
        }
        DiscountProduct that = (DiscountProduct) obj;
        return Double.compare(that.discountPercentage, discountPercentage) == 0 &&
               super.equals(obj); // Ensure that superclass attributes are also compared
    }

    @Override
    public String toString() {
        return super.toString() + "\nDiscount Product Details:" +
               "\n  - Discount: " + discountPercentage + "%" +
               "\n  - Final Price: $" + getFinalPrice() + 
               "\n  - Total Price: $" + getTotalPrice();
    }
}
