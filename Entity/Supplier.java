package Entity;

public class Supplier {
    // Instance variables (Private for encapsulation)
    private int supplierID;
    private String supplierName;
    private String contactNumber;
    private double amountPaid;
    private String date;
    private int transactionID;
    private String paymentMethod;

    // Static variable (Shared across all instances)
    private static int supplierCount = 0;

    // Constructor (Public: Allows object creation from anywhere)
    public Supplier(int transactionID, int supplierID, String supplierName, String contactNumber, double amountPaid, String paymentMethod) {
        this.transactionID = transactionID;
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactNumber = contactNumber;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        supplierCount++; // Increment supplier count
    }

    // Getter methods (Public: Provides controlled access to private variables)
    public int getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public String getDate() {
        return date;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public static int getSupplierCount() {
        return supplierCount;
    }

    // Setter methods (Public: Allows modifying private variables)
    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Method with local variable scope
    public void displaySupplierInfo() {
        String info = "Supplier: " + supplierName + " (ID: " + supplierID + "), Transaction ID: " + transactionID;
        System.out.println(info);
    }
}

