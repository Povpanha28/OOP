package Entity.Product;

public class Inventory {
    private String itemName;
    private double itemPrice;
    private int quantityInStock;
    private int reorderLevel;
    private String supplierID;
    private String addedDate;

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

    // Setters
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) {
        if (itemPrice >= 0) {
            this.itemPrice = itemPrice;
        } else {
            System.out.println("Invalid price. It must be positive.");
        }
    }

    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
        } else {
            System.out.println("Invalid quantity. It must be positive.");
        }
    }

    public void setReorderLevel(int reorderLevel) {
        if (reorderLevel >= 0) {
            this.reorderLevel = reorderLevel;
        } else {
            System.out.println("Invalid reorder level. It must be positive.");
        }
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
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
