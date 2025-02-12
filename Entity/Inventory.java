package Entity;

import java.util.HashMap;

public class Inventory {
    // Instance variables (Private for encapsulation)
    private int inventoryID;
    private int productID;
    private int quantityInStock;
    private int reorderLevel;
    private String lastRestockedDate;

    // Static variables (Shared across all instances)
    private static int totalInventoryItems = 0;
    private static final String adminPassword = "SecurePass123";
    private static HashMap<Integer, Inventory> inventoryDatabase = new HashMap<>();

    // Constructor (Public: Allows object creation from anywhere)
    public Inventory(int productID, int quantityInStock, int reorderLevel, String lastRestockedDate) {
        this.inventoryID = ++totalInventoryItems; // Auto-generate unique inventoryID
        this.productID = productID;
        this.quantityInStock = quantityInStock;
        this.reorderLevel = reorderLevel;
        this.lastRestockedDate = lastRestockedDate;

        inventoryDatabase.put(this.inventoryID, this); // Add to database
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

    // Authorization method
    private boolean isAuthorized(String password) {
        return adminPassword.equals(password);
    }

    // Setter methods with authorization
    public void setProductID(int productID, String password) {
        if (isAuthorized(password)) {
            this.productID = productID;
        } else {
            System.out.println("Unauthorized access: Invalid password.");
        }
    }

    public void setQuantityInStock(int quantityInStock, String password) {
        if (isAuthorized(password)) {
            if (quantityInStock >= 0) {
                this.quantityInStock = quantityInStock;
            } else {
                System.out.println("Invalid quantity. It cannot be negative.");
            }
        } else {
            System.out.println("Unauthorized access: Invalid password.");
        }
    }

    public void setReorderLevel(int reorderLevel, String password) {
        if (isAuthorized(password)) {
            if (reorderLevel >= 0) {
                this.reorderLevel = reorderLevel;
            } else {
                System.out.println("Invalid reorder level. It cannot be negative.");
            }
        } else {
            System.out.println("Unauthorized access: Invalid password.");
        }
    }

    public void setLastRestockedDate(String lastRestockedDate, String password) {
        if (isAuthorized(password)) {
            this.lastRestockedDate = lastRestockedDate;
        } else {
            System.out.println("Unauthorized access: Invalid password.");
        }
    }

    // Static methods for managing the inventory database
    public static Inventory getByID(int inventoryID) {
        if (inventoryDatabase.containsKey(inventoryID)) {
            return inventoryDatabase.get(inventoryID);
        } else {
            System.out.println("Inventory with ID " + inventoryID + " not found.");
            return null;
        }
    }

    public static void removeByID(int inventoryID, String password) {
        if (adminPassword.equals(password)) {
            if (inventoryDatabase.containsKey(inventoryID)) {
                inventoryDatabase.remove(inventoryID);
                System.out.println("Inventory with ID " + inventoryID + " removed successfully.");
            } else {
                System.out.println("Inventory with ID " + inventoryID + " not found.");
            }
        } else {
            System.out.println("Unauthorized access: Invalid password.");
        }
    }

    // Method with local variable scope
    public void displayInventoryInfo() {
        String info = "Inventory ID: " + inventoryID + ", Product ID: " + productID +
                      ", Quantity: " + quantityInStock + ", Reorder Level: " + reorderLevel +
                      ", Last Restocked: " + lastRestockedDate;
        System.out.println(info);
    }

    // toString method (Provides a string representation of the object)
    @Override
    public String toString() {
        return "Inventory{" +
                "InventoryID=" + inventoryID +
                ", ProductID=" + productID +
                ", QuantityInStock=" + quantityInStock +
                ", ReorderLevel=" + reorderLevel +
                ", LastRestockedDate='" + lastRestockedDate + '\'' +
                '}';
    }
}
