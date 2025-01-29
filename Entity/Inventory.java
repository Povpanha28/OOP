package Entity;

public class Inventory {
    int inventoryID;
    int productID;
    int quantityInStock;
    int reorderLevel;
    String lastRestockedDate;

    public Inventory(int inventoryId, int productId, int quantityInStock, int reorderLevel, String lastRestockedDate) {
        this.inventoryID = inventoryId;
        this.productID = productId;
        this.quantityInStock = quantityInStock;
        this.reorderLevel = reorderLevel;
        this.lastRestockedDate = lastRestockedDate;
    }

}
