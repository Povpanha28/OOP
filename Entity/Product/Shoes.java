package Entity.Product;

import java.util.Objects;

public class Shoes extends Product {
    private String size;
    private String color;
    private String brand;

    // Constructor
    public Shoes(String productName, double productPrice, int productQty,
            String productDescription,
            String size, String color, String brand) {
        super(productName, productPrice, productQty);
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

    // Setters with If-Else Logic (instead of exception handling)
    public boolean setSize(String size, String password) {
        if (!isAuthenticated(password)) {
            System.out.println("Access Denied: Unauthorized modification of size.");
            return false;
        } else if (size == null || size.isEmpty()) {
            System.out.println("Invalid Size: Size cannot be null or empty.");
            return false;
        } else {
            this.size = size;
            return true;
        }
    }

    public boolean setColor(String color, String password) {
        if (!isAuthenticated(password)) {
            System.out.println("Access Denied: Unauthorized modification of color.");
            return false;
        } else if (color == null || color.isEmpty()) {
            System.out.println("Invalid Color: Color cannot be null or empty.");
            return false;
        } else {
            this.color = color;
            return true;
        }
    }

    public boolean setBrand(String brand, String password) {
        if (!isAuthenticated(password)) {
            System.out.println("Access Denied: Unauthorized modification of brand.");
            return false;
        } else if (brand == null || brand.isEmpty()) {
            System.out.println("Invalid Brand: Brand cannot be null or empty.");
            return false;
        } else {
            this.brand = brand;
            return true;
        }
    }

    // Overriding equals() to compare objects based on field values
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Shoes shoes = (Shoes) obj;
        return Double.compare(shoes.getProductPrice(), getProductPrice()) == 0 &&
                getProductQty() == shoes.getProductQty() &&
                Objects.equals(getProductName(), shoes.getProductName()) &&
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
