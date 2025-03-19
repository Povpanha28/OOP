package Entity.Product;

import Database.MySQLConnection;
import java.sql.*;
import java.util.Scanner;

public class ProductDemo {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Product Management System =====");
            System.out.println("1. Add New Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addProductToDatabase(getUserInput());
                case 2 -> viewAllProducts();
                case 3 -> updateProduct();
                case 4 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
    }

    public static Product getUserInput() {
        System.out.print("Enter product type (Shirt/Shoes/Pant): ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid price! Returning to menu.");
            scanner.nextLine();
            return null;
        }
        double price = scanner.nextDouble();

        System.out.print("Enter product quantity: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid quantity! Returning to menu.");
            scanner.nextLine();
            return null;
        }
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        return switch (type) {
            case "shirt" -> {
                System.out.print("Enter shirt size: ");
                String size = scanner.nextLine();
                System.out.print("Enter shirt color: ");
                String color = scanner.nextLine();
                System.out.print("Enter material: ");
                String material = scanner.nextLine();
                yield new Shirt(name, price, quantity, description, size, color, material);
            }
            case "shoes" -> {
                System.out.print("Enter shoe size: ");
                String size = scanner.nextLine();
                System.out.print("Enter shoe color: ");
                String color = scanner.nextLine();
                System.out.print("Enter brand: ");
                String brand = scanner.nextLine();
                yield new Shoes(name, price, quantity, description, size, color, brand);
            }
            case "pant" -> {
                System.out.print("Enter pant size: ");
                String size = scanner.nextLine();
                System.out.print("Enter pant color: ");
                String color = scanner.nextLine();
                System.out.print("Enter material: ");
                String material = scanner.nextLine();
                yield new Pant(name, price, quantity, description, size, color, material);
            }
            default -> {
                System.out.println("Invalid product type! Returning to menu.");
                yield null;
            }
        };
    }

    public static void addProductToDatabase(Product product) {
        if (product == null) {
            return;
        }

        String query = "INSERT INTO product (name, price, qty, description, size, color, material_or_brand) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setDouble(2, product.getProductPrice());
            pstmt.setInt(3, product.getProductQty());
            pstmt.setString(4, product.getProductDescription());
            pstmt.setString(5, product instanceof Shirt ? ((Shirt) product).getSize()
                    : product instanceof Shoes ? ((Shoes) product).getSize()
                    : ((Pant) product).getSize());
            pstmt.setString(6, product instanceof Shirt ? ((Shirt) product).getColor()
                    : product instanceof Shoes ? ((Shoes) product).getColor()
                    : ((Pant) product).getColor());
            pstmt.setString(7, product instanceof Shirt ? ((Shirt) product).getMaterial()
                    : product instanceof Shoes ? ((Shoes) product).getBrand()
                    : ((Pant) product).getMaterial());

            pstmt.executeUpdate();
            System.out.println("Product added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    public static void viewAllProducts() {
        String query = "SELECT * FROM product";

        try (Connection connection = MySQLConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n===== Product List =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("product_id") +
                        " | Name: " + rs.getString("name") +
                        " | Price: $" + rs.getDouble("price") +
                        " | Quantity: " + rs.getInt("qty"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
        }
    }

    public static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid ID! Returning to menu.");
            scanner.nextLine();
            return;
        }
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new product name: ");
        String newName = scanner.nextLine();
        
        System.out.print("Enter new product price: ");
        double newPrice = scanner.nextDouble();
        
        System.out.print("Enter new quantity: ");
        int newQty = scanner.nextInt();
        scanner.nextLine();

        String query = "UPDATE product SET name=?, price=?, qty=? WHERE product_id=?";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, newName);
            pstmt.setDouble(2, newPrice);
            pstmt.setInt(3, newQty);
            pstmt.setInt(4, productId);

            System.out.println(pstmt.executeUpdate() > 0 ? "Product updated successfully!" : "Product not found!");
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }
}
