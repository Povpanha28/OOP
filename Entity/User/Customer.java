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
    public Customer(String username, String password, String email, String paymentMethod, String membershipLevel) {
        super(username, password, email);
        this.customerID = ++totalCustomers;
        this.paymentMethod = paymentMethod;
        this.membershipLevel = membershipLevel;

    }

    // Static methods 

    // Get customer by ID from Database...

    // Remove customer by ID from Database...

    // Show Database...

    // Getter methods
    public int getCustomerID() {return customerID;}
    public String getPaymentMethod() {return paymentMethod;}
    public String getMembershipLevel() {return membershipLevel;}

    // Autorized with password from user input ...
    
    // Setter methods
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
        Customer customer = new Customer(username, password, email, paymentMethod, membershipLevel);
        User.getUserDatabase().put(customer.getUserID(), customer);
        customerDatabase.put(customer.getCustomerID(), customer);
        System.out.println("Customer registered successfully! Customer ID: " + customer.getCustomerID());
        
    }
}
