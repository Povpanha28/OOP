package Entity.Sales;

import Database.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesMain {
    public static void main(String[] args) {
        // Create an ArrayList to store Sale objects
        List<Sale> saleList = new ArrayList<>();
        
        // Create Sale objects (including SalesDiscount)
        Sale sale1 = new Sale(1, 1, 5, 99.95);
        Sale sale2 = new SalesDiscount(2, 2, 10, 199.95, 0.1);
        Sale sale3 = new SalesDiscount(3, 3, 2, 49.95, 0.2);

        // Add the sales to the ArrayList
        saleList.add(sale1);
        saleList.add(sale2);
        saleList.add(sale3);

        // Add sales to the database
        addSalesToDatabase(saleList);

        // Delete sale from the database
        deleteSaleFromDatabase(2); // Example: Delete sale with sale_id 2
    }

    // Method to add sales to the database
    public static void addSalesToDatabase(List<Sale> sales) {
        String query = "INSERT INTO sales (sale_id, customer_id, product_id, amount_of_product, total_price, sale_date, discount_rate) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
    
            // Loop through each sale and insert it into the database
            for (Sale sale : sales) {
                pstmt.setInt(1, sale.getSaleID());
                pstmt.setInt(2, sale.getCustomerID());
                pstmt.setInt(3, sale.getProductID());
                pstmt.setInt(4, sale.getAmountOfProduct());
                pstmt.setDouble(5, sale.getTotalPrice());

                // Set sale date (you can customize this based on the sale's creation time)
                pstmt.setString(6, sale.getSaleDate() != null ? sale.getSaleDate() : "2025-03-05");

                // Handle discount for SalesDiscount objects
                if (sale instanceof SalesDiscount) {
                    pstmt.setDouble(7, ((SalesDiscount) sale).getDiscountRate());
                } else {
                    pstmt.setDouble(7, 0.0); // No discount for regular Sale
                }
    
                pstmt.addBatch(); // Add to batch for batch execution
            }
    
            // Execute the batch of insert statements
            pstmt.executeBatch();
            System.out.println("Sales added to the database successfully!");
    
        } catch (SQLException e) {
            System.out.println("Error adding sales to the database!");
            e.printStackTrace();
        }
    }

    // Method to delete sale from the database
    public static void deleteSaleFromDatabase(int saleID) {
        String query = "DELETE FROM sales WHERE sale_id = ?";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, saleID);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Sale with ID " + saleID + " deleted successfully!");
            } else {
                System.out.println("No sale found with ID " + saleID);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting sale from the database!");
            e.printStackTrace();
        }
    }
}
