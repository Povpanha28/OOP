package Entity;

public class Sale {
    private static int counter = 0; // Static counter for unique IDs
    public int saleID;  // Instance variable for unique ID 
    int customerID; // Who buy
    int productID; // What buy
    int amountOfProduct; // How many buy
    int totalPrice; // How much pay
    String saleDate;
    String paymentMethod; // Should be deleted beacuse it is include in payment

    // Constructor
    public Sale(int amountOfProduct, int customerID, String paymentMethod) {
        this.saleID = ++counter; // Assign unique ID to each instance
        this.amountOfProduct = amountOfProduct;
        this.customerID = customerID;
        this.paymentMethod = paymentMethod;
    }
}
