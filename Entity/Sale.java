package Entity;

public class Sale {
    String name;
    int saleID;
    int amount;
    int customerID;
    String saleDate;
    String paymentMethod;

    // Constructor
    public Sale(int saleID, int amount, int customerID, String paymentMethod) {
        this.saleID = saleID;
        this.amount = amount;
        this.customerID = customerID;
        this.paymentMethod = paymentMethod;
    }
}