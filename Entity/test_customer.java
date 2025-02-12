package Entity;

public class test_customer {
    public static void main(String[] args) {
        Customer customer1 = new Customer(1, "John Doe", "1234567890", "123 Main St");
        Customer customer2 = new Customer(2, "Jane Dy", "0987654321", "456 Elm St");
        System.out.println("Total customers: " + Customer.getTotalCustomers());
        System.out.println("Customer 1: " + customer1.getCustomerName());
        System.out.println("Customer 2: " + customer2.getCustomerName());
        customer1.removeCustomer(1);
        System.out.println("Total customers: " + Customer.getTotalCustomers());
    }
}
