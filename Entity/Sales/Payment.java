package Entity.Sales;

import java.util.HashMap;

public class Payment {

    private int paymentID;
    private static int nextPaymentID = 1;
    private int saleID;
    private String paymentMethod;
    private String transactionDate;
    private double amountPaid;

    private final String adminPassword = "SecurePass123";
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
    public int getPaymentID() {return paymentID;}
    public int getSaleID() {return saleID;}
    public String getPaymentMethod() {return paymentMethod;}
    public String getTransactionDate() {return transactionDate;}
    public double getAmountPaid() {return amountPaid;}

    public static Payment getPaymentByID(int id) {
        if(!paymentDatabase.containsKey(id)){
            return null;
        }
        return paymentDatabase.get(id); // Retrieve payment by ID
    }

    public static void removePaymentByID(int id, String password) {
        Payment payment = paymentDatabase.get(id);
        if (payment != null && payment.isAuthorized(password)) {
            paymentDatabase.remove(id);
            System.out.println("Payment with ID " + id + " removed successfully.");
        } else {
            System.out.println("Unauthorized access or payment not found.");
        }
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

    public void setSaleID(int saleID, String password) {
        if (isAuthorized(password)) {
            this.saleID = saleID;
        } else {
            System.out.println("Unauthorized access: Invalid password.");
        }
    }

    public void setPaymentMethod(String paymentMethod, String password) {
        if (isAuthorized(password)) {
            this.paymentMethod = paymentMethod;
        } else {
            System.out.println("Unauthorized access: Invalid password.");
        }
    }

    public void setAmountPaid(double amountPaid, String password) {
        if (isAuthorized(password)) {
            if (amountPaid >= 0) {
                this.amountPaid = amountPaid;
            } else {
                System.out.println("Invalid amount. Payment amount cannot be negative.");
            }
        } else {
            System.out.println("Unauthorized access: Invalid password.");
        }

    }

}
