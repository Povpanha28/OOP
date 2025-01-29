package Entity;

public class Sale {
    private String name;
    private int saleID;
    private int amount;
    private int customerID;
    private String saleDate;
    private String paymentMethod;

    // Constructor
    public Sale(int saleID, int amount, int customerID, String paymentMethod) {
        this.saleID = saleID;
        this.amount = amount;
        this.customerID = customerID;
        this.paymentMethod = paymentMethod;
    }

    // Getters
    public int getSaleID() {
        return saleID;
    }

    public int getAmount() {
        return amount;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getName() {
        return name;
    }

    public String getSaleDate() {
        return saleDate;
    }

    // Setters
    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }
}
