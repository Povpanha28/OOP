package Entity.Product;

public class Pant extends Product {
    private String size;
    private String color;
    private String material;

    // Constructor
    public Pant(String productName, double productPrice, int productQty,
                String productDescription,
                String size, String color, String material) {
        super(productName, productPrice, productQty);
        this.size = size;
        this.color = color;
        this.material = material;
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

    // Setters with If-Else Logic (instead of exception handling)
    public boolean setSize(String size, String password) {
        if (!isAuthenticated(password)) {
            System.out.println("Access Denied: Unauthorized modification of size.");
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
        } else {
            this.color = color;
            return true;
        }
    }

    public boolean setMaterial(String material, String password) {
        if (!isAuthenticated(password)) {
            System.out.println("Access Denied: Unauthorized modification of material.");
            return false;
        } else {
            this.material = material;
            return true;
        }
    }

    // Implementing abstract method from Product
    @Override
    public String getProductType() {
        return "Pant";
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
        Pant pant = (Pant) obj;

        // Check superclass fields (use super.equals to compare parent class attributes)
        return super.equals(obj) &&
               size.equals(pant.size) &&
               color.equals(pant.color) &&
               material.equals(pant.material);
    }

    @Override
    public String toString() {
        return super.toString() + "\nPant Details: " +
                "\n  - Size: " + size +
                "\n  - Color: " + color +
                "\n  - Material: " + material;
    }
}
