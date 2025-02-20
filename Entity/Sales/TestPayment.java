package Entity.Sales;

public class TestPayment {
    public static void main(String[] args) {
        Payment reciept1 = new CashPayment(1, 100, "10-10-2020", "Lis", 200);
        Payment reciept2 = new CashPayment(2, 200, "10-10-2020", "Lis", 200);

        System.out.println(Payment.getAllPayments());
        Payment.getPaymentByID(1).processPayment();
        Payment.getPaymentByID(2).processPayment();
    }
}
