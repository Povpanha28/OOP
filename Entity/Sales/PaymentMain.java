package Entity.Sales;

import Entity.Exception.InsufficientAmountException;
import Entity.Exception.PaymentNotFoundException;
import Entity.Exception.UnauthorizedPaymentAccessException;

public class PaymentMain {
    public static void main(String[] args) throws UnauthorizedPaymentAccessException, InsufficientAmountException {
        try {
            // Creating payments
            Payment payment1 = new CashPayment(100, 300.0, "2025-02-27", "Cash", 300);
            Payment payment2 = new CashPayment(102, 200.0, "2025-02-27", "Cash", 100);

            // Process payment1
            try {
                if (payment1.processPayment()) {
                    System.out.println("Payment 1 processed successfully.");
                }
            } catch (InsufficientAmountException e) {
                System.out.println("Payment 1 processing failed: " + e.getMessage());
            }

            // Process payment2
            try {
                if (payment2.processPayment()) {
                    System.out.println("Payment 2 processed successfully.");
                }
            } catch (InsufficientAmountException e) {
                System.out.println("Payment 2 processing failed: " + e.getMessage());
            }

            // Retrieve payment by ID (use the actual IDs of the created payments)
            System.out.println("Retrieving payment with ID " + payment1.getPaymentID() + ":");
            try {
                System.out.println(Payment.getPaymentByID(payment1.getPaymentID())); // Retrieve using actual ID
            } catch (PaymentNotFoundException e) {
                System.out.println("Exception occurred while retrieving payment: " + e.getMessage());
            }

            // Attempt to remove payment with incorrect password (using actual ID)
            try {
                Payment.removePaymentByID(payment1.getPaymentID(), "WrongPass123"); // Should throw an exception
            } catch (UnauthorizedPaymentAccessException e) {
                System.out.println("Exception: " + e.getMessage());
            }

            // Attempt to remove payment with correct password (using actual ID)
            try {
                Payment.removePaymentByID(payment1.getPaymentID(), "SecurePass123"); // Should succeed if ID exists
            } catch (UnauthorizedPaymentAccessException | PaymentNotFoundException e) {
                System.out.println("Exception: " + e.getMessage());
            }

            // Display all payments remaining in the database
            System.out.println("Remaining Payments: " + Payment.getAllPayments());

        } catch (PaymentNotFoundException e) {
            System.out.println("Exception Occurred: " + e.getMessage());
        }
    }
}
