package Entity.Product;

public class DiscountProduct extends Product {
    private double discountPercentage; // Discount percentage (0-100)

    // Constructor
    public DiscountProduct(String productName, double productPrice, int productQty, 
                           String productDescription, String addedDate, String supplierID, 
                           double discountPercentage, String password) {
        super(productName, productPrice, productQty, addedDate,supplierID);

        if (isAuthenticated(password)) {
            setProductDescription(productDescription, password);
            setDiscountPercentage(discountPercentage, password); // Ensures validation
        } else {
            throw new SecurityException("Access Denied: Unauthorized to create DiscountProduct.");
        }
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

    public double getTotalPrice(){
        return getFinalPrice() * getProductQty() ;
    }
    @Override
    public String toString() {
        return super.toString() + "\nDiscount Product Details:" +
               "\n  - Discount: " + discountPercentage + "%" +
               "\n  - Final Price: $" + getFinalPrice() + 
               "\n  - Total Price: $" + getTotalPrice();
    }
}
