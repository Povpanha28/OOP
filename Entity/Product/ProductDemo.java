package Entity.Product;

public class ProductDemo {

    public static void main(String[] args) {
        // Create objects of Shirt, Shoes, and Pant
        Shirt shirt = new Shirt("T-Shirt", 19.99, 10, "Comfortable cotton t-shirt", "2025-02-19", "S001", "M", "Red", "Cotton");
        Shoes shoes = new Shoes("Running Shoes", 49.99, 15, "Lightweight running shoes", "2025-02-19", "S002", "42", "Blue", "Nike");
        Pant pant = new Pant("Jeans", 39.99, 20, "Stylish blue jeans", "2025-02-19", "S003", "L", "Blue", "Denim");

        // Store the objects in an array of type Product
        Product[] products = new Product[3];
        products[0] = shirt;
        products[1] = shoes;
        products[2] = pant;

        // Iterate over the array and print out the details of each product
        for (Product product : products) {
            System.out.println(product.toString());
        }

        // Example of casting and accessing specific properties
        System.out.println("\nAccessing specific properties using casting:");

        // Casting to Shirt
        Shirt shirtFromArray = (Shirt) products[0];
        System.out.println("Shirt Size: " + shirtFromArray.getSize());

        // Casting to Shoes
        Shoes shoesFromArray = (Shoes) products[1];
        System.out.println("Shoes Brand: " + shoesFromArray.getBrand());

        // Casting to Pant (assuming Pant class exists)
        Pant pantFromArray = (Pant) products[2];
        System.out.println("Pant Color: " + pantFromArray.getColor());
    }
}
