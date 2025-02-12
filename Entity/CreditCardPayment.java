package Entity;

import java.util.HashMap;

public class CreditCardPayment extends Payment {
    private String cardNumber;
    private static HashMap<Integer, CreditCardPayment> creditCardPaymentDatabase = new HashMap<>();

    public CreditCardPayment(int saleID, double amountPaid, String transactionDate, String paymentMethod, String cardNumber) {
        super(saleID, amountPaid, transactionDate, paymentMethod);
        this.cardNumber = cardNumber;
        // Store the payment in the HashMap using paymentID as the key
        creditCardPaymentDatabase.put(getPaymentID(), this);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber, String password) {
        if (isAuthorized(password)) {
            this.cardNumber = cardNumber;
        } else {
            System.out.println("Unauthorized access: Invalid password.");
        }
    }

    public static CreditCardPayment getByID(int paymentID) {
        return creditCardPaymentDatabase.get(paymentID);
    }

    public static void removeByID(int paymentID) {
        if (creditCardPaymentDatabase.containsKey(paymentID)) {
            creditCardPaymentDatabase.remove(paymentID);
            System.out.println("Payment with ID " + paymentID + " removed successfully.");
        } else {
            System.out.println("No payment found with ID " + paymentID);
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", CardNumber='" + cardNumber + '\'';
    }
}


