package Entity.Sales;

public abstract class Payment {

    private int paymentID;
    private static int nextPaymentID = 1;
    private int saleID;
    private String paymentMethod;
    protected double amountPaid;

    private final static String adminPassword = "SecurePass123";

    // Constructor (Public: Allows object creation from anywhere)
    public Payment(int saleID, double amountPaid, String paymentMethod) {
        this.paymentID = nextPaymentID;
        nextPaymentID++;
        this.saleID = saleID;
        this.paymentMethod = paymentMethod;
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

    public double getAmountPaid() {
        return amountPaid;
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

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public abstract boolean processPayment();

    public abstract boolean validatePayment();
}
