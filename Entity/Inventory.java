package Entity;

public class Inventory {
    // Instance variables (Private for encapsulation)
    private int inventoryID;
    private int productID;
    private int quantityInStock;
    private int reorderLevel;
    private String lastRestockedDate;

    // Static variable (Shared across all instances)
    private static int totalInventoryItems = 0;

    // Constructor (Public: Allows object creation from anywhere)
    public Inventory(int inventoryID, int productID, int quantityInStock, int reorderLevel, String lastRestockedDate) {
        this.inventoryID = inventoryID;
        this.productID = productID;
        this.quantityInStock = quantityInStock;
        this.reorderLevel = reorderLevel;
        this.lastRestockedDate = lastRestockedDate;
        totalInventoryItems++; // Increment total inventory count
    }

    // Getter methods (Public: Provides controlled access to private variables)
    public int getInventoryID() {
        return inventoryID;
    }

    public int getProductID() {
        return productID;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public String getLastRestockedDate() {
        return lastRestockedDate;
    }

    public static int getTotalInventoryItems() {
        return totalInventoryItems;
    }

    // Setter methods (Public: Allows modifying private variables with validation)
    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
        } else {
            System.out.println("Invalid quantity. It cannot be negative.");
        }
    }

    public void setReorderLevel(int reorderLevel) {
        if (reorderLevel >= 0) {
            this.reorderLevel = reorderLevel;
        } else {
            System.out.println("Invalid reorder level. It cannot be negative.");
        }
    }

    public void setLastRestockedDate(String lastRestockedDate) {
        this.lastRestockedDate = lastRestockedDate;
    }

    // Method with local variable scope
    public void displayInventoryInfo() {
        String info = "Inventory ID: " + inventoryID + ", Product ID: " + productID +
                      ", Quantity: " + quantityInStock + ", Reorder Level: " + reorderLevel +
                      ", Last Restocked: " + lastRestockedDate;
        System.out.println(info);
    }
}

