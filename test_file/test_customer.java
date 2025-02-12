package test_file;
import Entity.Customer;

public class test_customer {
    public static void main(String[] args) {
        Customer.addCustomer(1, "John Doe", "1234567890", "123 Main St");
        Customer.addCustomer(2, "Jane Dy", "0987654321", "456 Elm St");
        System.out.println("Total customers: " + Customer.totalCustomer());
        System.out.println("Customer 1: " + Customer.findCustomer(1).getCustomerName());
        System.out.println("Customer 2: " + Customer.findCustomer(2).getCustomerName());
        Customer.removeCustomer(1);
        System.out.println("Total customers: " + Customer.totalCustomer());
    }
}