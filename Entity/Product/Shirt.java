package Entity.Product;

import java.util.Objects;

public class Shirt extends Product {
    private String size;
    private String color;
    private String material;

    // Constructor
    public Shirt(String productName, double productPrice, int productQty,
            String productDescription,
            String size, String color, String material) {
        super(productName, productPrice, productQty);
        this.size = size;
        this.color = color;
        this.material = material;
    }

    // Implementing abstract method from Product
    @Override
    public String getProductType() {
        return "Shirt";
    }

    // Getters
    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    // Secure Setters with If-Else Logic
    public boolean setSize(String size, String password) {
        if (!isAuthenticated(password)) {
            System.out.println("Access Denied: Unauthorized to change size.");
            return false;
        } else if (size == null || size.isEmpty()) {
            System.out.println("Invalid size. It cannot be empty.");
            return false;
        } else {
            this.size = size;
            return true;
        }
    }

    public boolean setColor(String color, String password) {
        if (!isAuthenticated(password)) {
            System.out.println("Access Denied: Unauthorized to change color.");
            return false;
        } else if (color == null || color.isEmpty()) {
            System.out.println("Invalid color. It cannot be empty.");
            return false;
        } else {
            this.color = color;
            return true;
        }
    }

    public boolean setMaterial(String material, String password) {
        if (!isAuthenticated(password)) {
            System.out.println("Access Denied: Unauthorized to change material.");
            return false;
        } else if (material == null || material.isEmpty()) {
            System.out.println("Invalid material. It cannot be empty.");
            return false;
        } else {
            this.material = material;
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
        Shirt shirt = (Shirt) obj;
        return Double.compare(shirt.getProductPrice(), getProductPrice()) == 0 &&
                getProductQty() == shirt.getProductQty() &&
                Objects.equals(getProductName(), shirt.getProductName()) &&
                Objects.equals(size, shirt.size) &&
                Objects.equals(color, shirt.color) &&
                Objects.equals(material, shirt.material);
    }

    @Override
    public String toString() {
        return super.toString() + "\nShirt Details:" +
                "\n  - Size: " + size +
                "\n  - Color: " + color +
                "\n  - Material: " + material;
    }
}
