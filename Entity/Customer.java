package Entity;

import java.util.HashMap;

public class Customer {
    // Instance variables (Private for encapsulation)
    private String customerName;
    static int customerID = 0;
    private String customerContact;
    private String customerAddress;
    private int password;
    private String userName;

    // Static variable (Shared across all instances)
    private static int totalCustomers = 0;
    // Define HashMap for customers
    private static HashMap<Integer, Customer> customers = new HashMap<>();

    // Function to add customer
    public void addCustomer(int customerID, String customerName, String customerContact, String customerAddress) {
        customers.put(customerID, new Customer(customerID, customerName, customerContact, customerAddress));
    }

    // Function to remove customer
    public void removeCustomer(int customerID) {
        customers.remove(customerID);
    }

    // Count total customers
    public int totalCustomer() {
        return customers.size();
    }

    // Constructor for register
    public Customer(int customerID, String customerName, String customerContact, String customerAddress) {
        Customer.customerID = customerID;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.customerAddress = customerAddress;
        totalCustomers++; // Increment total customer count
    }

    // Find customer by ID
    public Customer findCustomer(int customerID) {
        return customers.get(customerID);
    }

    // Constructor for login
    public Customer(int password, String userName) {
        this.password = password;
        this.userName = userName;
    }

    // Getter methods (Public: Provides controlled access to private variables)
    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public static int getTotalCustomers() {
        return totalCustomers;
    }

    public int getCustomerPass() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    // Setter methods (Public: Allows modifying private variables with validation)
    public void changeCustomerPass(int oldPassword, int newPassword) {
        if (this.password == oldPassword) {
            this.password = newPassword;
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Incorrect old password. Password change failed.");
        }
    }

    public void changeUsername() {
        // Implementation for changing username
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    // Method with local variable scope
    public void displayCustomerInfo() {
        String info = "Customer ID: " + customerID + ", Name: " + customerName +
                      ", Contact: " + customerContact + ", Address: " + customerAddress;
        System.out.println(info);
    }
}

