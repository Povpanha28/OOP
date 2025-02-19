package Entity.Product;

public class Inventory {
    private String itemName;
    private double itemPrice;
    private int quantityInStock;
    private int reorderLevel;
    private String supplierID;
    private String addedDate;

    private static final String ADMIN_PASSWORD = "admin123"; // Secure this properly in real systems

    // Constructor
    public Inventory(String itemName, double itemPrice, int quantityInStock, 
                     int reorderLevel, String supplierID, String addedDate) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantityInStock = quantityInStock;
        this.reorderLevel = reorderLevel;
        this.supplierID = supplierID;
        this.addedDate = addedDate;
    }

    // Getters
    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getAddedDate() {
        return addedDate;
    }

    // Authentication method
    private boolean isAuthenticated(String password) {
        return ADMIN_PASSWORD.equals(password);
    }

    // Setters with authentication
    public void setItemName(String itemName, String password) {
        if (isAuthenticated(password) && !itemName.isEmpty()) {
            this.itemName = itemName;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid input.");
        }
    }

    public void setItemPrice(double itemPrice, String password) {
        if (isAuthenticated(password) && itemPrice >= 0) {
            this.itemPrice = itemPrice;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid price.");
        }
    }

    public void setQuantityInStock(int quantityInStock, String password) {
        if (isAuthenticated(password) && quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid quantity.");
        }
    }

    public void setReorderLevel(int reorderLevel, String password) {
        if (isAuthenticated(password) && reorderLevel >= 0) {
            this.reorderLevel = reorderLevel;
        } else {
            System.out.println("Access Denied: Unauthorized or invalid reorder level.");
        }
    }

    public void setSupplierID(String supplierID, String password) {
        if (isAuthenticated(password)) {
            this.supplierID = supplierID;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    public void setAddedDate(String addedDate, String password) {
        if (isAuthenticated(password)) {
            this.addedDate = addedDate;
        } else {
            System.out.println("Access Denied: Unauthorized.");
        }
    }

    // Check if inventory needs restocking
    public boolean needsRestocking() {
        return quantityInStock <= reorderLevel;
    }

    @Override
    public String toString() {
        return "Inventory [Item Name=" + itemName + ", Price=" + itemPrice + 
               ", Stock=" + quantityInStock + ", Reorder Level=" + reorderLevel + 
               ", Supplier=" + supplierID + ", Added Date=" + addedDate + "]";
    }
}
