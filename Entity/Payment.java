package Entity;

public class Payment {
    // Instance variables (Private for encapsulation)
    private int paymentID;
    private int saleID;
    private String paymentMethod;
    private String transactionDate;
    private double amountPaid;

    // Static variable (Shared across all instances)
    private static int totalPayments = 0;

    // Constructor (Public: Allows object creation from anywhere)
    public Payment(int paymentID, int saleID, String paymentMethod, String transactionDate, double amountPaid) {
        this.paymentID = paymentID;
        this.saleID = saleID;
        this.paymentMethod = paymentMethod;
        this.transactionDate = transactionDate;
        this.amountPaid = amountPaid;
        totalPayments++; // Increment total payment count
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

    public static int getTotalPayments() {
        return totalPayments;
    }

    // Setter methods (Public: Allows modifying private variables)
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
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

    // Method with local variable scope
    public void displayPaymentInfo() {
        String info = "Payment ID: " + paymentID + ", Amount: $" + amountPaid + ", Method: " + paymentMethod;
        System.out.println(info);
    }
}

