package Entity;

public class Supplier {
    private int supplierID;
    private String supplierName;
    private String contactNumber;
    private double amountPaid;
    private String date;
    private int transactionID;
    private String paymentMethod;

    Supplier(int transactionID, int supplierID, String supplierName,String contactNumber, double amountPaid, String paymentMethod){
        this.transactionID = transactionID;
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactNumber = contactNumber;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;

    }
}
