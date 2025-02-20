package Entity.Product;

public class Shirt extends Product {
    private String size;
    private String color;
    private String material;

    // Constructor
    public Shirt(String productName, double productPrice, int productQty, 
                 String productDescription, String addedDate, String supplierID, 
                 String size, String color, String material) {
        super(productName, productPrice, productQty, addedDate, supplierID);

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

    public void setMaterial(String material, String password) {
        if (isAuthenticated(password) && material != null && !material.isEmpty()) {
            this.material = material;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid material.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nShirt Details:" +
               "\n  - Size: " + size + 
               "\n  - Color: " + color + 
               "\n  - Material: " + material;
    }
}
