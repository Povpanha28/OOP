package Entity.Sales;

import Database.MySQLConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class SalesMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = MySQLConnection.getConnection()) {
            while (true) {
                System.out.println("\n===== Sales Management System =====");
                System.out.println("1. Add Sales to Database");
                System.out.println("2. Delete Sale from Database");
                System.out.println("3. Update Sale in Database");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next();
                    continue;
                }

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addSale(scanner, connection);
                        break;

                    case 2:
                        deleteSale(scanner, connection);
                        break;

                    case 3:
                        updateSale(scanner, connection);
                        break;

                    case 4:
                        System.out.println("Exiting System...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid Choice! Please Try Again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database Connection Error!");
            e.printStackTrace();
        }
    }

    private static void addSale(Scanner scanner, Connection connection) {
        System.out.println("\nEnter Sale Details:");

        System.out.print("Product ID: ");
        int productId = scanner.nextInt();

        System.out.print("Amount of Product: ");
        int amount = scanner.nextInt();

        System.out.print("Total Price: ");
        double price = scanner.nextDouble();

        System.out.print("Is there any discount? (yes/no): ");
        String discountChoice = scanner.next();
        double discount = 0.0;

        if (discountChoice.equalsIgnoreCase("yes")) {
            System.out.print("Discount Percentage: ");
            discount = scanner.nextDouble();
        }

        String query = "INSERT INTO sales (product_id, amount_of_product, total_price, sale_date, discount_rate) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, productId);
            pstmt.setInt(2, amount);
            pstmt.setDouble(3, price);
            pstmt.setString(4, LocalDate.now().toString());
            pstmt.setDouble(5, discount);

            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                System.out.println("Sale added with ID: " + generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Error adding sale!");
            e.printStackTrace();
        }
    }

    private static void deleteSale(Scanner scanner, Connection connection) {
        System.out.print("\nEnter Sale ID to Delete: ");
        int saleID = scanner.nextInt();

        String query = "DELETE FROM sales WHERE sale_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, saleID);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Sale with ID " + saleID + " deleted successfully!");
            } else {
                System.out.println("No sale found with ID " + saleID);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting sale!");
            e.printStackTrace();
        }
    }

    private static void updateSale(Scanner scanner, Connection connection) {
        System.out.print("\nEnter Sale ID to Update: ");
        int saleID = scanner.nextInt();

        System.out.println("\nChoose what to update:");
        System.out.println("1. Amount of Product");
        System.out.println("2. Total Price");
        System.out.println("3. Discount Rate");
        System.out.println("4. Update All");
        System.out.print("Enter your choice: ");

        int updateChoice = scanner.nextInt();
        String query = "";
        boolean hasUpdate = false;

        try {
            switch (updateChoice) {
                case 1:
                    System.out.print("Enter New Amount of Product: ");
                    int newAmount = scanner.nextInt();
                    query = "UPDATE sales SET amount_of_product = ? WHERE sale_id = ?";
                    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                        pstmt.setInt(1, newAmount);
                        pstmt.setInt(2, saleID);
                        hasUpdate = pstmt.executeUpdate() > 0;
                    }
                    break;

                case 2:
                    System.out.print("Enter New Total Price: ");
                    double newPrice = scanner.nextDouble();
                    query = "UPDATE sales SET total_price = ? WHERE sale_id = ?";
                    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                        pstmt.setDouble(1, newPrice);
                        pstmt.setInt(2, saleID);
                        hasUpdate = pstmt.executeUpdate() > 0;
                    }
                    break;

                case 3:
                    System.out.print("Enter New Discount Percentage: ");
                    double newDiscount = scanner.nextDouble();
                    query = "UPDATE sales SET discount_rate = ? WHERE sale_id = ?";
                    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                        pstmt.setDouble(1, newDiscount);
                        pstmt.setInt(2, saleID);
                        hasUpdate = pstmt.executeUpdate() > 0;
                    }
                    break;

                case 4:
                    System.out.print("Enter New Amount of Product: ");
                    int updatedAmount = scanner.nextInt();
                    System.out.print("Enter New Total Price: ");
                    double updatedPrice = scanner.nextDouble();
                    System.out.print("Enter New Discount Percentage: ");
                    double updatedDiscount = scanner.nextDouble();

                    query = "UPDATE sales SET amount_of_product = ?, total_price = ?, discount_rate = ?, sale_date = ? WHERE sale_id = ?";
                    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                        pstmt.setInt(1, updatedAmount);
                        pstmt.setDouble(2, updatedPrice);
                        pstmt.setDouble(3, updatedDiscount);
                        pstmt.setString(4, LocalDate.now().toString());
                        pstmt.setInt(5, saleID);
                        hasUpdate = pstmt.executeUpdate() > 0;
                    }
                    break;

                default:
                    System.out.println("Invalid choice! No updates performed.");
                    return;
            }

            if (hasUpdate) {
                System.out.println("Sale with ID " + saleID + " updated successfully!");
            } else {
                System.out.println("No sale found with ID " + saleID);
            }
        } catch (SQLException e) {
            System.out.println("Error updating sale!");
            e.printStackTrace();
        }
    }
}
