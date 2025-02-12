package Entity;
import java.util.HashMap;

public class test_customer {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.addCustomer(1, "John Doe", "1234567890", "123 Main St");
        customer.addCustomer(2, "Jane Doe", "0987654321", "456 Elm St");
        System.out.println("Total customers: " + customer.totalCustomer());
        System.out.println("Customer 1: " + customer.findCustomer(1).getCustomerName());
        System.out.println("Customer 2: " + customer.findCustomer(2).getCustomerName());
        customer.removeCustomer(1);
        System.out.println("Total customers: " + customer.totalCustomer());
    }
    
}
