package Entity.Product;

import Database.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDemo {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Product Management System =====");
            System.out.println("1. Add New Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addProductsToDatabase(getUserInput());
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    // scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Get user input for adding products
    public static List<Product> getUserInput() {
        List<Product> productList = new ArrayList<>();
        System.out.print("Enter the number of products to add: ");
        int numProducts = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numProducts; i++) {
            System.out.println("\nEnter details for Product " + (i + 1) + ":");
            System.out.print("Enter product type (Shirt/Shoes/Pant): ");
            String type = scanner.nextLine().trim();

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter product price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter product quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter product description: ");
            String description = scanner.nextLine();

            System.out.print("Enter added date (YYYY-MM-DD): ");
            String addedDate = scanner.nextLine();

            System.out.print("Enter supplier ID: ");
            String supplierID = scanner.nextLine();

            if (type.equalsIgnoreCase("Shirt")) {
                System.out.print("Enter shirt size: ");
                String size = scanner.nextLine();
                System.out.print("Enter shirt color: ");
                String color = scanner.nextLine();
                System.out.print("Enter material: ");
                String material = scanner.nextLine();
                productList.add(new Shirt(name, price, quantity, description, addedDate, supplierID, size, color, material));

            } else if (type.equalsIgnoreCase("Shoes")) {
                System.out.print("Enter shoe size: ");
                String size = scanner.nextLine();
                System.out.print("Enter shoe color: ");
                String color = scanner.nextLine();
                System.out.print("Enter brand: ");
                String brand = scanner.nextLine();
                productList.add(new Shoes(name, price, quantity, description, addedDate, supplierID, size, color, brand));

            } else if (type.equalsIgnoreCase("Pant")) {
                System.out.print("Enter pant size: ");
                String size = scanner.nextLine();
                System.out.print("Enter pant color: ");
                String color = scanner.nextLine();
                System.out.print("Enter material: ");
                String material = scanner.nextLine();
                productList.add(new Pant(name, price, quantity, description, addedDate, supplierID, size, color, material));

            } else {
                System.out.println("Invalid product type! Skipping...");
            }
        }
        return productList;
    }

    // CREATE: Add products to the database
    public static void addProductsToDatabase(List<Product> products) {
        String query = "INSERT INTO product (name, price, qty, description, added_date, product_code, size, color, material_or_brand) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
    
            for (Product product : products) {
                pstmt.setString(1, product.getProductName());
                pstmt.setDouble(2, product.getProductPrice());
                pstmt.setInt(3, product.getProductQty());
                pstmt.setString(4, product.getProductDescription());
                pstmt.setString(5, product.getAddedDate());
                pstmt.setString(6, product.getSupplierID());

                pstmt.setString(7, product instanceof Shirt ? ((Shirt) product).getSize() : 
                                    product instanceof Shoes ? ((Shoes) product).getSize() : 
                                    product instanceof Pant ? ((Pant) product).getSize() : null);

                pstmt.setString(8, product instanceof Shirt ? ((Shirt) product).getColor() : 
                                    product instanceof Shoes ? ((Shoes) product).getColor() : 
                                    product instanceof Pant ? ((Pant) product).getColor() : null);

                pstmt.setString(9, product instanceof Shirt ? ((Shirt) product).getMaterial() : 
                                    product instanceof Shoes ? ((Shoes) product).getBrand() : 
                                    product instanceof Pant ? ((Pant) product).getMaterial() : null);

                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Products added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding products!");
            e.printStackTrace();
        }
    }

    // READ: View all products
    public static void viewAllProducts() {
        String query = "SELECT * FROM product";
    
        try (Connection connection = MySQLConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
    
            System.out.println("\n===== Product List =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Name: " + rs.getString("name") +
                                   " | Price: $" + rs.getDouble("price") + 
                                   " | Quantity: " + rs.getInt("qty"));
            }
    
        } catch (SQLException e) {
            System.out.println("Error fetching products! Full error details:");
            e.printStackTrace();  // Prints full error details
        }
    }
    
    

    // UPDATE: Modify product details
    public static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new product name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new product price: ");
        double newPrice = scanner.nextDouble();

        System.out.print("Enter new quantity: ");
        int newQty = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "UPDATE product SET name=?, price=?, qty=? WHERE id=?";
        
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, newName);
            pstmt.setDouble(2, newPrice);
            pstmt.setInt(3, newQty);
            pstmt.setInt(4, productId);

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? "Product updated successfully!" : "Product not found!");

        } catch (SQLException e) {
            System.out.println("Error updating product!");
            e.printStackTrace();
        }
    }

    // DELETE: Remove a product by ID
    public static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int productId = scanner.nextInt();

        String query = "DELETE FROM product WHERE id=?";
        
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, productId);
            int rowsDeleted = pstmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? "Product deleted successfully!" : "Product not found!");

        } catch (SQLException e) {
            System.out.println("Error deleting product!");
            e.printStackTrace();
        }
    }
}
