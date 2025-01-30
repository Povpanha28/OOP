package Entity;

public class Test {
    public static void main(String[] args) {
        Product product1 = new Product("Milk", "2021-01-01", 10, "2021-01-10");
        Product product2 = new Product("Bread", "2021-01-01", 20, "2021-01-10");
        Product product3 = new Product("Eggs", "2021-01-01", 30, "2021-01-10");

        System.out.println("Product 1: " + product1.productId);
        System.out.println("Product 2: " + product2.productId);
        System.out.println("Product 3: " + product3.productId);

    }
}
