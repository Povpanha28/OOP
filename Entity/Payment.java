package Entity;


public class Payment {

    private static int paymentID = 0;
    private int saleID; 
    private String paymentMethod;
    private String transactionDate;
    private double amountPaid;

    private final String adminPassword = "SecurePass123";

    // Constructor (Public: Allows object creation from anywhere)
    public Payment(int saleID,  double amountPaid, String transactionDate, String paymentMethod) {
        paymentID++;
        this.saleID = saleID;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.transactionDate = transactionDate;
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

    // Password
    private boolean isAuthorized(String password) {
        return password.equals(adminPassword);
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
