package Entity.Product;

import java.util.Objects;

public class Shoes extends Product {
    private String size;
    private String color;
    private String brand;

    // Constructor
    public Shoes(String productName, double productPrice, int productQty, 
                 String productDescription, String addedDate, String supplierID, 
                 String size, String color, String brand) {
        super(productName, productPrice, productQty, addedDate, supplierID);
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

    // Secure Setters with Exception Handling
    public void setSize(String size, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized modification of size.");
        }
        if (size == null || size.isEmpty()) {
            throw new IllegalArgumentException("Invalid Size: Size cannot be null or empty.");
        }
        this.size = size;
    }

    public void setColor(String color, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized modification of color.");
        }
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Invalid Color: Color cannot be null or empty.");
        }
        this.color = color;
    }

    public void setBrand(String brand, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized modification of brand.");
        }
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("Invalid Brand: Brand cannot be null or empty.");
        }
        this.brand = brand;
    }

    // Overriding equals() to compare objects based on field values
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Shoes shoes = (Shoes) obj;
        return Double.compare(shoes.getProductPrice(), getProductPrice()) == 0 &&
               getProductQty() == shoes.getProductQty() &&
               Objects.equals(getProductName(), shoes.getProductName()) &&
               Objects.equals(getAddedDate(), shoes.getAddedDate()) &&
               Objects.equals(getSupplierID(), shoes.getSupplierID()) &&
               Objects.equals(size, shoes.size) &&
               Objects.equals(color, shoes.color) &&
               Objects.equals(brand, shoes.brand);
    }

    @Override
    public String toString() {
        return super.toString() + "\nShoes Details: " +
               "\n  - Size: " + size + 
               "\n  - Color: " + color + 
               "\n  - Brand: " + brand;
    }
}