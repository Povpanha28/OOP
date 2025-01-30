package Entity;

public class Supplier {
    // Instance variables (Private for encapsulation)
    private static int supplierID = 0;
    private String supplierName;
    private String contactNumber;
    private double amountPaid;
    private String date;
    private int transactionID;
    private String paymentMethod;


    // Constructor (Public: Allows object creation from anywhere)
    public Supplier(int transactionID, int supplierID, String supplierName, String contactNumber, double amountPaid, String paymentMethod) {
        this.transactionID = transactionID;
        supplierID++;
        this.supplierName = supplierName;
        this.contactNumber = contactNumber;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
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

}

