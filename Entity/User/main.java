package Entity.User;


public class main {
    public static void main(String[] args) {
        // Create a new Customer object
        Customer customer = new Customer("Thyrak", "testPassword", "test@example.com");
        // Register the customer in the database
        customer.register();

        // Attempt to log in with the same credentials
        customer.login();

        // Update customer information
        customer.setUsername("updatedCustomer", "test@example.com", "testPassword");
        customer.setPassword("newPassword", "test@example.com", "testPassword");
        customer.setEmail("updated@example.com", "newPassword");

        // Retrieve the customer by ID (assuming you know the ID)
        int customerID = customer.getUserID();
        Customer retrievedCustomer = (Customer) Customer.getUserByID(customerID);
        if (retrievedCustomer != null) {
            System.out.println("Retrieved Customer: " + retrievedCustomer);
        }

        // Remove the customer from the database (optional)
        Customer.removeUserByID(customerID);
    }
}