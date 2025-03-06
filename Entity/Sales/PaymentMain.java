package Entity.Sales;

import Database.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentMain {
    public static void main(String[] args) {
        // Create an ArrayList to store Payment objects
        List<Payment> paymentList = new ArrayList<>();

        // Create CashPayment objects
        Payment payment1 = new CashPayment(101, 500.0, "2025-03-05", "John Doe", 600.0);
        Payment payment2 = new CashPayment(102, 300.0, "2025-03-05", "Jane Smith", 400.0);

        // Add payments to the ArrayList
        paymentList.add(payment1);
        paymentList.add(payment2);

        // Add payments to the database
        // addPaymentsToDatabase(paymentList);

        // Delete payment with paymentID 1
        deletePaymentFromDatabase(4);
    }

    // Method to add payments to the database
    public static void addPaymentsToDatabase(List<Payment> payments) {
        String query = "INSERT INTO payment (saleID, paymentMethod, amountPaid, transactionDate) VALUES (?, ?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Loop through each payment and insert it into the database
            for (Payment payment : payments) {
                if (payment.processPayment() && payment.validatePayment()) {
                    pstmt.setInt(1, payment.getSaleID());
                    pstmt.setString(2, payment.getPaymentMethod());
                    pstmt.setDouble(3, payment.getAmountPaid());
                    pstmt.setString(4, payment.getTransactionDate());

                    pstmt.addBatch(); // Add to batch for batch execution
                } else {
                    System.out.println("Payment validation failed for: " + payment);
                }
            }

            // Execute the batch of insert statements
            pstmt.executeBatch();
            System.out.println("Payments added to the database successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding payments to the database!");
            e.printStackTrace();
        }
    }

    // Method to delete payment from the database
    public static void deletePaymentFromDatabase(int paymentID) {
        String query = "DELETE FROM payment WHERE paymentID = ?";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, paymentID);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Payment with ID " + paymentID + " deleted successfully!");
            } else {
                System.out.println("No payment found with ID " + paymentID);
            }

        } catch (SQLException e) {
            System.out.println("Error deleting payment from the database!");
            e.printStackTrace();
        }
    }
}
