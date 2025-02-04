package Entity;

import java.util.ArrayList;

public class Customer {
    // Instance variables (Private for encapsulation)
    private String customerName;
    static  int customerID = 0;
    private String customerContact;
    private String customerAddress;
    private int password;
    private String userName;

    // Static variable (Shared across all instances)
    private static int totalCustomers = 0;
    //define ArrayList for customer
    ArrayList<Customer> customer = new ArrayList<Customer>();
    //function add customer
    public void addCustomer(Customer customer){
        this.customer.add(customer);
    }
    //function remove customer
    public void removeCustomer(Customer customer){
        this.customer.remove(customer);
    }
    



    // Constructor for register
    public Customer(String customerName, String customerContact, String customerAddress) {
        this.customerName = customerName;
        customerID = customerID + 1;
        this.customerContact = customerContact;
        this.customerAddress = customerAddress;
        totalCustomers++; // Increment total customer count
    }

    //constructor for login
    public Customer(int password,String userName ){
        this.password = password;
        this.userName = userName;
    }


    // Getter methods (Public: Provides controlled access to private variables)
    public ArrayList<Customer> getCustomer(){
        return customer;
    }
    public String getCustomerName() {
        return customerName;
    }

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

    public int getCustomerPass(){
        return password;
    }
    public String getUserName(){
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
    public void changeUsername(){
        
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

