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
    // Code ...
    

    // Constructor (Public: Allows object creation from anywhere)
    public Customer(String username, String password, String email, String role, String paymentMethod, String membershipLevel) {
        super(username, password, email, role);
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

        // Check if the username already exists
        

        // Add the new customer to the database

    }
}
