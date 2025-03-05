package Entity.User;

import java.util.HashMap;

public class Customer extends User implements Autentication {
    // Instance variables (Private for encapsulation)
    private int customerID;
    private String paymentMethod;
    private String membershipLevel;

    // Static variables (Shared across all instances)
    private static int totalCustomers = 0;

    // Create HashMap for customerdatabase
    private static HashMap<Integer, Customer> customerDatabase = new HashMap<>();

    // Code ...
    

    // Constructor (Public: Allows object creation from anywhere)
    public Customer(String username, String password, String email, String role, String paymentMethod, String membershipLevel) {
        super(username, password, email, role);
        this.customerID = ++totalCustomers;
        this.paymentMethod = paymentMethod;
        this.membershipLevel = membershipLevel;

    }

    public String getRole(){
        return "Customer";
    }

    // Static methods 
  


    // Get customer by ID from Database...
    public static Customer getCustomerByID(int id) {
        return customerDatabase.getOrDefault(id, null);
    }

    // Remove customer by ID from Database...
    public static void removeCustomerByID(int id) {
        if (customerDatabase.containsKey(id)) {
            customerDatabase.remove(id);
            System.out.println("Customer with ID " + id + " removed successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Show Database...
    public static HashMap<Integer, Customer> getCustomerDatabase() {
        return customerDatabase;
    }

    // Getter methods
    public int getCustomerID() {return customerID;}
    public String getPaymentMethod() {return paymentMethod;}
    public String getMembershipLevel() {return membershipLevel;}

    // Autorized with password from user input ...

    
    // Setter methods
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    

    // Set autorized with password to setter
    public void setPaymentMethod(String paymentMethod, String Password) {
        this.paymentMethod = paymentMethod;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + customerID + ", Username=" + getUsername() +
                ", Email=" + getEmail(super.email, super.password) +
                ", Payment Method=" + paymentMethod + ", Membership Level=" + membershipLevel + "]";
    }

    @Override
    public void login() {
        System.out.println("Logging in as a customer...");

        String username = getUsername();
        String password = getPassword(super.email, super.password);

        // Check if the username exists
        for (Customer customer : customerDatabase.values()) {
            if (customer.getUsername().equals(username)) {
                // Check if the password is correct
                if (customer.getPassword(super.email, super.password).equals(password)) {
                    System.out.println("Login successful! Welcome back, " + customer.getUsername() + ".");
                    return;
                }
            }
        }
        System.out.println("Login failed. Incorrect username or password.");
        
    }

    @Override
    public void register() {
        System.out.println("Registering a new customer...");

        String username = getUsername();
        String password = getPassword(super.email, super.password);
        String email = getEmail(super.email, super.password);
        String paymentMethod = this.paymentMethod;
        String membershipLevel = this.membershipLevel;
        // Check if the username already exists
        for (Customer customer : customerDatabase.values()) {
            if (customer.getUsername().equals(username)) {
                System.out.println("Registration failed. Username already exists.");
                return;
            }
        }

        // Add the new customer to the database
        Customer customer = new Customer(username, password, email, paymentMethod, membershipLevel, membershipLevel);
        User.getUserDatabase().put(customer.getUserID(), customer);
        customerDatabase.put(customer.getCustomerID(), customer);
        System.out.println("Customer registered successfully! Customer ID: " + customer.getCustomerID());

    }
}
