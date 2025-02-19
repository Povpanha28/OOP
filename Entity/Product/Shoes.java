package Entity.Product;

public class Shoes extends Product {
    private String size;
    private String color;
    private String brand;

    // Constructor
    public Shoes(String productName, double productPrice, int productQty, 
                 String productDescription, String addedDate, String supplierID, 
                 String size, String color, String brand) {
        super(productName, productPrice, productQty, addedDate, "", supplierID);
        setProductDescription(productDescription, "admin123"); // Ensuring proper description
        this.size = size;
        this.color = color;
        this.brand = brand;
    }

    // Implementing abstract method from Product
    @Override
    public String getProductType() {
        return "Shoes";
    }

    // Getters
    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    // Secure Setters
    public void setSize(String size, String password) {
        if (isAuthenticated(password) && size != null && !size.isEmpty()) {
            this.size = size;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid size.");
        }
    }

    public void setColor(String color, String password) {
        if (isAuthenticated(password) && color != null && !color.isEmpty()) {
            this.color = color;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid color.");
        }
    }

    public void setBrand(String brand, String password) {
        if (isAuthenticated(password) && brand != null && !brand.isEmpty()) {
            this.brand = brand;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid brand.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nShoes Details: " +
               "\n  - Size: " + size + 
               "\n  - Color: " + color + 
               "\n  - Brand: " + brand;
    }
}

