package Entity.Sales;

import java.util.HashMap;

import Entity.Exception.InsufficientAmountException;
import Entity.Exception.InvalidPaymentMethodException;
import Entity.Exception.PaymentNotFoundException;
import Entity.Exception.UnauthorizedPaymentAccessException;

public abstract class Payment {

    private int paymentID;
    private static int nextPaymentID = 1;
    private int saleID;
    private String paymentMethod;
    private String transactionDate;
    protected double amountPaid;

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

    public static Payment getPaymentByID(int id) throws PaymentNotFoundException {
        if (!paymentDatabase.containsKey(id)) {
            throw new PaymentNotFoundException("Payment with ID " + id + " not found.");
        }
        return paymentDatabase.get(id); // Retrieve payment by ID
    }

    public static void removePaymentByID(int id, String password)
            throws UnauthorizedPaymentAccessException, PaymentNotFoundException {

        Payment payment = paymentDatabase.get(id);

        if (payment == null) {
            throw new PaymentNotFoundException("Payment with ID " + id + " not found.");
        }

        if (!payment.isAuthorized(password)) {
            throw new UnauthorizedPaymentAccessException("Unauthorized access: Incorrect password.");
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

    public void setSaleID(int saleID, String password) throws UnauthorizedPaymentAccessException {
        if (!isAuthorized(password)) {
            throw new UnauthorizedPaymentAccessException("Unauthorized access: Invalid password.");
        }
        this.saleID = saleID;
    }

    public void setPaymentMethod(String paymentMethod, String password) throws UnauthorizedPaymentAccessException {
        if (!isAuthorized(password)) {
            throw new UnauthorizedPaymentAccessException("Unauthorized access: Invalid password.");
        }
        this.paymentMethod = paymentMethod;
    }

    public void setAmountPaid(double amountPaid, String password)
            throws UnauthorizedPaymentAccessException, InvalidPaymentMethodException, InsufficientAmountException {

        if (!isAuthorized(password)) {
            throw new UnauthorizedPaymentAccessException("Unauthorized access: Invalid password.");
        }

        if (amountPaid < 0) {
            throw new InsufficientAmountException("Invalid amount: Payment amount cannot be negative.");
        }

        this.amountPaid = amountPaid;
    }

    public abstract boolean processPayment() throws InsufficientAmountException;

    public abstract boolean validatePayment();

}
