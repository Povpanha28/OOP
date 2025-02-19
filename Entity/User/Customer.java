package Entity.User;

import java.util.HashMap;

public class Customer implements Autentication {
    // Instance variables (Private for encapsulation)
    private int customerID;
    private String customerName;
    private String customerContact;
    private String customerAddress;
    private int password;
    private String userName;

    // Static variable (Shared across all instances)
    private static int totalCustomers = 0;
    // Define HashMap for customers
    private static HashMap<Integer, Customer> customers = new HashMap<>();

    // Static function to add customer
    // public static void addCustomer(int customerID, String customerName, String customerContact, String customerAddress, int password, String userName) {
    //     customers.put(customerID, new Customer(customerID, customerName, customerContact, customerAddress, password, userName));
    // }

    // Static function to remove customer
    public static void removeCustomer(int customerID) {
        if (customers.remove(customerID) != null) {
            totalCustomers--; // Decrement total customer count
        }
    }

    // Static function to count total customers
    public static int totalCustomer() {
        return customers.size();
    }

    // Constructor for register
    public Customer(int customerID, String customerName, String customerContact, String customerAddress, int password, String userName) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.customerAddress = customerAddress;
        this.password = password;
        this.userName = userName;
        totalCustomers++; // Increment total customer count
    }

    // Static function to find customer by ID
    public static Customer findCustomer(int customerID) {
        return customers.get(customerID);
    }

    // Static function to authenticate customer
    public static void authenticateCustomer(String userName, int password) {
        boolean loginSuccessful = false;
        for (Customer customer : customers.values()) {
            if (customer.getUserName().equals(userName) && customer.getCustomerPass() == password) {
                System.out.println("Login successful.");
                loginSuccessful = true;
                break;
            }
        }
        if (!loginSuccessful) {
            System.out.println("Login failed.");
        }
        
    }

    // Static method to display all customers
    public static void displayAllCustomers() {
        for (Customer customer : customers.values()) {
            System.out.println(customer.getCustomerDetails());
        }
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

    public String getCustomerName() {
        return customerName;
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

    public void changeUsername(String newUserName) {
        this.userName = newUserName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    // Method with local variable scope
    public String getCustomerDetails() {
        return "Customer " + this.customerID + ": " + this.customerName + ", Contact: " + this.customerContact + ", Address: " + this.customerAddress;
    }

    @Override
    public void login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public void register() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }


}
