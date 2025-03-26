package Entity.Sales;

import Database.MySQLConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class PaymentMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = MySQLConnection.getConnection()) {
            while (true) {
                System.out.println("\n===== Payment Management System =====");
                System.out.println("1. Add Cash Payment");
                System.out.println("2. Delete Payment");
                System.out.println("3. Update Payment");
                System.out.println("4. View Payments");  // New Feature
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next();  
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addPayment(scanner, connection);
                        break;
                    case 2:
                        deletePayment(scanner, connection);
                        break;
                    case 3:
                        updatePayment(scanner, connection);
                        break;
                    case 4:
                        viewPayments(connection); // New Feature
                        break;
                    case 5:
                        System.out.println("Exiting System...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid Choice! Please Try Again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database Connection Error! Please check your connection.");
            e.printStackTrace();
        }
    }

    private static void addPayment(Scanner scanner, Connection connection) {
        System.out.println("\nEnter Cash Payment Details:");

        int saleID;
        while (true) {
            System.out.print("Enter Sale ID: ");
            if (scanner.hasNextInt()) {
                saleID = scanner.nextInt();
                if (saleID > 0) break;
                System.out.println("Sale ID must be a positive number.");
            } else {
                System.out.println("Invalid input! Please enter a valid Sale ID.");
            }
            scanner.next(); 
        }

        double amountPaid;
        while (true) {
            System.out.print("Enter Amount Paid: ");
            if (scanner.hasNextDouble()) {
                amountPaid = scanner.nextDouble();
                if (amountPaid > 0) break;
                System.out.println("Amount Paid must be greater than zero.");
            } else {
                System.out.println("Invalid input! Please enter a valid amount.");
            }
            scanner.next();
        }

        scanner.nextLine();

        System.out.print("Enter Cashier Name: ");
        String cashierName = scanner.nextLine();

        double moneyGiven;
        while (true) {
            System.out.print("Enter Money Given by Customer: ");
            if (scanner.hasNextDouble()) {
                moneyGiven = scanner.nextDouble();
                if (moneyGiven >= amountPaid) break;
                System.out.println("Money given must be at least equal to the amount paid.");
            } else {
                System.out.println("Invalid input! Please enter a valid amount.");
            }
            scanner.next();
        }

        CashPayment cashPayment = new CashPayment(saleID, amountPaid, cashierName, moneyGiven);
        addPaymentToDatabase(cashPayment, connection);
    }

    private static void addPaymentToDatabase(CashPayment cashPayment, Connection connection) {
        String query = "INSERT INTO payment (paymentMethod, amountPaid, paymentDate, cashierName) VALUES (?, ?, ?, ?)";
    
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, cashPayment.getPaymentMethod());
            pstmt.setDouble(2, cashPayment.getAmountPaid());
            pstmt.setString(3, LocalDate.now().toString());
            pstmt.setString(4, cashPayment.getCashierName());
    
            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Payment added successfully!" : "Error: Payment not added.");
        } catch (SQLException e) {
            System.out.println("Error adding payment to the database!");
            e.printStackTrace();
        }
    }

    private static void deletePayment(Scanner scanner, Connection connection) {
        System.out.print("\nEnter Payment ID to Delete: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid Payment ID.");
            scanner.next();
            return;
        }
        int paymentID = scanner.nextInt();

        String query = "DELETE FROM payment WHERE paymentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, paymentID);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Payment deleted successfully!" : "No payment found with that ID.");
        } catch (SQLException e) {
            System.out.println("Error deleting payment from the database!");
            e.printStackTrace();
        }
    }

    private static void updatePayment(Scanner scanner, Connection connection) {
        System.out.print("\nEnter Payment ID to Update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid Payment ID.");
            scanner.next();
            return;
        }
        int paymentID = scanner.nextInt();

        System.out.println("\nChoose what to update:");
        System.out.println("1. Amount Paid");
        System.out.println("2. Cashier Name");
        System.out.print("Enter your choice: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid option.");
            scanner.next();
            return;
        }

        int updateChoice = scanner.nextInt();
        scanner.nextLine();
        String query = "";
        boolean hasUpdate = false;

        try {
            switch (updateChoice) {
                case 1:
                    System.out.print("Enter New Amount Paid: ");
                    double newAmountPaid = scanner.nextDouble();
                    query = "UPDATE payment SET amountPaid = ? WHERE paymentID = ?";
                    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                        pstmt.setDouble(1, newAmountPaid);
                        pstmt.setInt(2, paymentID);
                        hasUpdate = pstmt.executeUpdate() > 0;
                    }
                    break;

                case 2:
                    System.out.print("Enter New Cashier Name: ");
                    String newCashierName = scanner.nextLine();
                    query = "UPDATE payment SET cashierName = ? WHERE paymentID = ?";
                    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                        pstmt.setString(1, newCashierName);
                        pstmt.setInt(2, paymentID);
                        hasUpdate = pstmt.executeUpdate() > 0;
                    }
                    break;

                default:
                    System.out.println("Invalid choice! No updates performed.");
                    return;
            }

            System.out.println(hasUpdate ? "Payment updated successfully!" : "No payment found with that ID.");
        } catch (SQLException e) {
            System.out.println("Error updating payment!");
            e.printStackTrace();
        }
    }

    private static void viewPayments(Connection connection) {
        String query = "SELECT * FROM payment";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\n===== Payment Records =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("paymentID") +
                        ", Method: " + rs.getString("paymentMethod") +
                        ", Amount: " + rs.getDouble("amountPaid") +
                        ", Date: " + rs.getString("paymentDate") +
                        ", Cashier: " + rs.getString("cashierName"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving payments!");
            e.printStackTrace();
        }
    }
}
