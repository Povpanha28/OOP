package System;

import Entity.Sales.Payment;

public class Payment_System {
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        Payment payment1 = new Payment(1, 100, "2021-01-01", "Cash");
        Payment payment2 = new Payment(2, 200, "2021-01-02", "Credit Card");
        
        System.out.println(Payment.getAllPayments());
        System.out.println(Payment.getPaymentByID(1));
        System.out.println(Payment.getPaymentByID(2));
        Payment.removePaymentByID(1, "SecurePass123");
        System.out.println(Payment.getAllPayments());

    }
}
