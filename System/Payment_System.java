package System;

import java.util.HashMap;

import Entity.Payment;

public class Payment_System {
    public static void main(String[] args) {
        HashMap<Integer, Payment> paymentMap = new HashMap<Integer, Payment>();

        Payment payment1 = new Payment(1, 100, "2021-01-01", "Cash");
        Payment payment2 = new Payment(2, 200, "2021-01-02", "Credit Card");

        paymentMap.put(payment1.getPaymentID(), payment1);
        paymentMap.put(payment2.getPaymentID(), payment2);
        
        System.out.println(paymentMap.get(2).getPaymentID());

    }
}
