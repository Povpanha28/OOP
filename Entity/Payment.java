package Entity;

public class Payment {

    private static int paymentID = 0;
    private int saleID;
    private String paymentMethod;
    private String transactionDate;
    private double amountPaid;


    // Constructor (Public: Allows object creation from anywhere)
    public Payment(int paymentID, int saleID, String paymentMethod, String transactionDate, double amountPaid) {
        paymentID++;
        this.saleID = saleID;
        this.paymentMethod = paymentMethod;
        this.transactionDate = transactionDate;
        this.amountPaid = amountPaid;
    }

    // Getter methods (Public: Provides controlled access to private variables)
    public int getPaymentID() {
        return paymentID;
    }

    public int getSaleID() {
        return saleID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }


    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setAmountPaid(double amountPaid) {
        if (amountPaid >= 0) {
            this.amountPaid = amountPaid;
        } else {
            System.out.println("Invalid amount. Payment amount cannot be negative.");
        }
    }

}

