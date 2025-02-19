package Entity.Product;

public class Pant extends Product {
    private String size;
    private String color;
    private String material;

    // Constructor
    public Pant(String productName, double productPrice, int productQty, 
                String productDescription, String addedDate, String supplierID, 
                String size, String color, String material) {
        super(productName, productPrice, productQty, addedDate, "", supplierID);
        setProductDescription(productDescription, "admin123"); // Ensuring proper description
        this.size = size;
        this.color = color;
        this.material = material;
    }

    // Implementing abstract method from Product
    @Override
    public String getProductType() {
        return "Pant";
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

    // Setters with authentication
    public void setSize(String size, String password) {
        if (isAuthenticated(password)) {
            this.size = size;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    public void setColor(String color, String password) {
        if (isAuthenticated(password)) {
            this.color = color;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    public void setMaterial(String material, String password) {
        if (isAuthenticated(password)) {
            this.material = material;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nPant Details: " +
               "\n  - Size: " + size + 
               "\n  - Color: " + color + 
               "\n  - Material: " + material;
    }
}
