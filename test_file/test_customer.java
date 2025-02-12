package test_file;

import Entity.Customer;

public class test_customer {
    public static void main(String[] args) {
        // Add customer
        Customer.addCustomer(1, "John Doe", "0123456789", "123 Main St", 1234, "johndoe");
        Customer.addCustomer(2, "Jane Smith", "0987654321", "456 Elm St", 5678, "janesmith");

        // Display total customers
        System.out.println("There are: " + Customer.totalCustomer() + " customers.");

        // Display all customers
        Customer.displayAllCustomers();
        // test login
        Customer.authenticateCustomer("johndoe", 1274);
    }
}
