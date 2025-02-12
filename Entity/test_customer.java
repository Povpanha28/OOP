package Entity;

public class test_customer {
    public static void main(String[] args) {
        //create object of customer
        Customer customer = new Customer(1, "John", "123456789", "123, ABC Street");
        //add customer
        customer.addCustomer(1, "John", "123456789", "123, ABC Street");
        customer.addCustomer(2, "Doe", "987654321", "456, XYZ Street");
        //remove customer
        customer.removeCustomer(1);
        //count total customer
        System.out.println(customer.totalCustomer());
    }
    
}
