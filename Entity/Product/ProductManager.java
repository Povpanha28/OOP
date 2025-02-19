package Entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> productList; // List to store both NoneDiscountProduct & DiscountProduct

    public ProductManager() {
        this.productList = new ArrayList<>();
    }

    // Add a product to the list (both NoneDiscountProduct & DiscountProduct as Product)
    public void addProduct(Product product) {
        productList.add(product);
        System.out.println("Product added: " + product.getProductName());
    }

    // Display all products (override methods handle type-specific behavior)
    public void displayAllProducts() {
        for (Product product : productList) {
            System.out.println(product); // Calls overridden toString() from subclasses
            System.out.println("-------------------------------------");
        }
    }

    // Example usage
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();

        // Creating products
        Product discountProduct = new DiscountProduct("Laptop", 1200.00, 10, "2024-07-01", "2026-07-01", "S123", 15);
        Product noneDiscountProduct = new NoneDiscountProduct("Smartphone", 799.99, 15, "2024-07-10", "2026-07-10", "S567");

        // Adding products to the list
        manager.addProduct(discountProduct);
        manager.addProduct(noneDiscountProduct);

        // Display all products
        manager.displayAllProducts();
    }
}
