package Entity;

import Entity.Product.Product;

public class Test {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // Product product1 = new Product("Apple",20,"02-04-2024","02-04-2027","S001");
        // Product product2 = new Product("Banana",30,"02-04-2024","02-04-2027","S002");
        // System.out.println(Product.getProductById(2));

        Sale sale1 = new Sale(1,1,2);
        Sale sale2 = new Sale(2,2,3);
        System.out.println(Sale.getAllSales());
        
    }
}
