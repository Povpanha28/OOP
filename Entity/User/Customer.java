package Entity.User;

import java.util.HashMap;

public class Customer extends User{
    // Instance variables
    private String paymentMethod;
    private int membershipLevel;

    // Static variables
    private static HashMap<Integer, Customer> customerDatabase = new HashMap<>();

    // Constructor
    public Customer(String username, String password, String email, String paymentMethod, int membershipLevel) {
        super(username, password, email);
        this.paymentMethod = paymentMethod;
        this.membershipLevel = membershipLevel;
    }

    public String getRole() {
        return "Customer";
    }

    // Static Methods
    public static Customer getCustomerByID(int id) {
        return customerDatabase.getOrDefault(id, null);
    }

    public static void removeCustomerByID(int id) {
        if (customerDatabase.containsKey(id)) {
            customerDatabase.remove(id);
            User.getUserDatabase().remove(id);
            System.out.println("Customer with ID " + id + " removed successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public static HashMap<Integer, Customer> getCustomerDatabase() {
        return customerDatabase;
    }

    // Getter Methods
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getMembershipLevel() {
        return membershipLevel;
    }

    // Setter Methods with Authorization
    public void setPaymentMethod(String paymentMethod, String password) {
        if (password.equals(this.password)) {
            this.paymentMethod = paymentMethod;
        } else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setMembershipLevel(int membershipLevel, String password) {
        if (password.equals(this.password)) {
            this.membershipLevel = membershipLevel;
        } else {
            System.out.println("Unauthorized access.");
        }
    }

    @Override
    public String toString() {
        return "Customer [Username=" + getUsername() + ", Email=" + getEmail() + ", Payment Method=" + paymentMethod
                + ", Membership Level=" + membershipLevel + "]";
    }

    @Override
    public void login() {
        System.out.println("Attempting to log in as a customer...");
        for (Customer customer : customerDatabase.values()) {
            if (customer.getUsername().equals(getUsername()) && customer.getPassword().equals(getPassword())) {
                System.out.println("Login successful for Customer: " + customer.getUsername());
                return;
            }
        }
        System.out.println("Login failed. Invalid username or password.");
    }

    @Override
    public void register() {
        System.out.println("Registering a new customer...");
        if (customerDatabase.containsKey(this.getUserID())) {
            System.out.println("Customer already registered.");
            return;
        }
        customerDatabase.put(this.getUserID(), this);
        User.getUserDatabase().put(this.getUserID(), this);
        System.out.println("Customer registered successfully! User ID: " + this.getUserID());
    }
}