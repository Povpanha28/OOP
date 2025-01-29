package Entity;

public class Payment {
    int paymentID;
    int saleID;
    String paymentMethod;
    String transactionDate;
    double amountPaid;

    public Payment(int paymentId, int saleId, String paymentMethod, String transactionDate, double amountPaid) {
        this.paymentID = paymentId;
        this.saleID = saleId;
        this.paymentMethod = paymentMethod;
        this.transactionDate = transactionDate;
        this.amountPaid = amountPaid;
    }
}
