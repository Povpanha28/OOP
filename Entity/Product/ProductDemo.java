package Entity.Product;

import Database.MySQLConnection;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ProductDemo {

    public static void main(String[] args) {
        // Create an ArrayList to store Product objects
        List<Product> productList = new ArrayList<>();

        // Create objects of different Product subclasses
        Shirt shirt = new Shirt("T-Shirt", 19.99, 10, "Comfortable cotton t-shirt", "2025-02-19", "S001", "M", "Red", "Cotton");
        Shoes shoes = new Shoes("Running Shoes", 49.99, 15, "Lightweight running shoes", "2025-02-19", "S002", "42", "Blue", "Nike");
        Pant pant = new Pant("Jeans", 39.99, 20, "Stylish blue jeans", "2025-02-19", "S003", "L", "Blue", "Denim");

        // Add the products to the ArrayList
        productList.add(shirt);
        productList.add(shoes);
        productList.add(pant);

        // Add products to the database
        addProductsToDatabase(productList);
    }

    // Method to add products to the database
    public static void addProductsToDatabase(List<Product> products) {
        String query = "INSERT INTO product (name, price, qty, description, added_date, product_code, size, color, material_or_brand) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
    
            // Loop through each product and insert it into the database
            for (Product product : products) {
                pstmt.setString(1, product.getProductName());
                pstmt.setDouble(2, product.getProductPrice());
                pstmt.setInt(3, product.getProductQty());
                pstmt.setString(4, product.getProductDescription());
                pstmt.setString(5, product.getAddedDate());
                pstmt.setString(6, product.getSupplierID());
    
                // Set the size and color common to all products
                pstmt.setString(7, product instanceof Shirt ? ((Shirt) product).getSize() : null);
                pstmt.setString(8, product instanceof Shirt ? ((Shirt) product).getColor() : null);
    
                // Handling polymorphism for the last column (material/brand)
                if (product instanceof Shirt) {
                    pstmt.setString(9, ((Shirt) product).getMaterial());
                } else if (product instanceof Shoes) {
                    pstmt.setString(9, ((Shoes) product).getBrand());
                } else if (product instanceof Pant) {
                    pstmt.setString(9, ((Pant) product).getMaterial());
                }
    
                pstmt.addBatch(); // Add to batch for batch execution
            }
    
            // Execute the batch of insert statements
            pstmt.executeBatch();
            System.out.println("Products added to the database successfully!");
    
        } catch (SQLException e) {
            System.out.println("Error adding products to the database!");
            e.printStackTrace();
        }
    }
}