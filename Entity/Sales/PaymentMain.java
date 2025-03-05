package Entity.Sales;

import Entity.ExceptionSrc.*;
import java.util.Scanner;

public class PaymentMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        // Menu for user interaction
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a Cash Payment");
            System.out.println("2. View Payment by ID");
            System.out.println("3. Exit");
            try {
                String choiceInput = scanner.nextLine(); // Consume newline
                new NumberOnlyException(choiceInput, "\\d+"); // Validate choice is numeric
                choice = Integer.parseInt(choiceInput);
                switch (choice) {
                    case 1:
                        try {
                            // Add a Cash Payment
                            System.out.print("Enter Sale ID: ");
                            String saleIDInput = scanner.nextLine();
                            new NumberOnlyException(saleIDInput, "\\d+"); // Validate that Sale ID is numeric
                            int saleID = Integer.parseInt(saleIDInput);

                            System.out.print("Enter Amount Paid: ");
                            String amountPaidInput = scanner.nextLine();
                            new NumberOnlyException(amountPaidInput, "\\d+(\\.\\d{1,2})?"); // Validate amount paid is
                                                                                            // numeric
                            double amountPaid = Double.parseDouble(amountPaidInput);

                            System.out.print("Enter Transaction Date (e.g., 2025-03-05): ");
                            String transactionDate = scanner.nextLine();

                            System.out.print("Enter Cashier Name: ");
                            String cashierName = scanner.nextLine();

                            System.out.print("Enter Money Given by Customer: ");
                            String moneyGivenInput = scanner.nextLine();
                            new NumberOnlyException(moneyGivenInput, "\\d+(\\.\\d{1,2})?"); // Validate money given is
                                                                                            // numeric
                            double moneyGiven = Double.parseDouble(moneyGivenInput);

                            // Create a CashPayment object
                            CashPayment cashPayment = new CashPayment(saleID, amountPaid, transactionDate, cashierName,
                                    moneyGiven);

                            // Check for sufficient amount
                            new InsufficientAmountException(moneyGiven, amountPaid);

                            // Process payment and validate
                            if (cashPayment.processPayment()) {
                                cashPayment.validatePayment();
                            }

                            System.out.println("Payment added: " + cashPayment);
                        } catch (InsufficientAmountException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (NumberOnlyException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("An unexpected error occurred: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            // View payment by ID
                            System.out.print("Enter Payment ID to view: ");
                            String paymentIDInput = scanner.nextLine();
                            new NumberOnlyException(paymentIDInput, "\\d+"); // Validate Payment ID is numeric
                            int paymentID = Integer.parseInt(paymentIDInput);

                            Payment payment = Payment.getPaymentByID(paymentID);
                            if (payment != null) {
                                System.out.println(payment);
                            } else {
                                System.out.println("Payment with ID " + paymentID + " not found.");
                            }
                        } catch (NumberOnlyException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("An unexpected error occurred: " + e.getMessage());
                        }
                        break;

                    case 3:
                        // Exit
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
