package Entity.Sales;

import Entity.ExceptionSrc.InsufficientAmountException;

public class PaymentMain {
    public static void main(String[] args) throws InsufficientAmountException{
        // Creating payments
        Payment payment1 = new CashPayment(100, 300.0, "2025-02-27", "Cash", 200);
        Payment payment2 = new CashPayment(102, 200.0, "2025-02-27", "Cash", 200);

        // Process payment1
        try {
            payment1.processPayment();
        } catch (InsufficientAmountException e) {
            System.out.println(e.getMessage());
        }

        // Process payment2
        try {
            payment2.processPayment();
        } catch (InsufficientAmountException e) {
            System.out.println(e.getMessage());
        }
    }
}
