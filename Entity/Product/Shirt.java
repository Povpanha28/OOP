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

    // Secure Setters with Exception Handling
    public void setSize(String size, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized to change size.");
        }
        if (size == null || size.isEmpty()) {
            throw new IllegalArgumentException("Invalid size. It cannot be empty.");
        }
        this.size = size;
    }

    public void setColor(String color, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized to change color.");
        }
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Invalid color. It cannot be empty.");
        }
        this.color = color;
    }

    public void setMaterial(String material, String password) throws UnauthorizedAccessException {
        if (!isAuthenticated(password)) {
            throw new UnauthorizedAccessException("Access Denied: Unauthorized to change material.");
        }
        if (material == null || material.isEmpty()) {
            throw new IllegalArgumentException("Invalid material. It cannot be empty.");
        }
        this.material = material;
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
