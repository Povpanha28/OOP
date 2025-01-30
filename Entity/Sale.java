package Entity;

public class Sale {
    private static int counter = 0; // Static counter for unique IDs
    public int saleID;  // Instance variable for unique ID
    String name;
    int amount;
    int customerID;
    String saleDate;
    String paymentMethod;

    // Constructor
    public Sale(int amount, int customerID, String paymentMethod) {
        this.saleID = ++counter; // Assign unique ID to each instance
        this.amount = amount;
        this.customerID = customerID;
        this.paymentMethod = paymentMethod;
    }
}
