package Entity.Sales;

import java.util.HashMap;


public abstract class Payment {

    private int paymentID;
    private static int nextPaymentID = 1;
    private int saleID;
    private String paymentMethod;
    private String transactionDate;
    protected double amountPaid;

    private final static String adminPassword = "SecurePass123";
    private static HashMap<Integer, Payment> paymentDatabase = new HashMap<>();

    // Constructor (Public: Allows object creation from anywhere)
    public Payment(int saleID, double amountPaid, String transactionDate, String paymentMethod) {
        this.paymentID = nextPaymentID;
        nextPaymentID++;
        this.saleID = saleID;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.transactionDate = transactionDate;
        paymentDatabase.put(this.paymentID, this);
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

    public static Payment getPaymentByID(int id) {
        if (!paymentDatabase.containsKey(id)) {
            System.out.println("Payment with ID " + id + " not found.");
            return null; // Return null if payment not found
        }
        return paymentDatabase.get(id); // Retrieve payment by ID
    }

    public static void removePaymentByID(int id, String password) {

        Payment payment = paymentDatabase.get(id);

        if (payment == null) {
            System.out.println("Payment with ID " + id + " not found.");
            return;
        }

        paymentDatabase.remove(id);
        System.out.println("Payment with ID " + id + " removed successfully.");
    }

    public static HashMap<Integer, Payment> getAllPayments() {
        return paymentDatabase; // Retrieve all payments
    }

    // Password
    protected boolean isAuthorized(String password) {
        return password.equals(adminPassword);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "PaymentID=" + paymentID +
                ", SaleID=" + saleID +
                ", PaymentMethod='" + paymentMethod + '\'' +
                ", TransactionDate='" + transactionDate + '\'' +
                ", AmountPaid=" + amountPaid +
                '}';
    }

    // Setter methods

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public abstract boolean processPayment();

    public abstract boolean validatePayment();

}
