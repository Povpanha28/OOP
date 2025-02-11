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
    ArrayList<Customer> customer = new ArrayList<>();
    //function add customer
    public void addCustomer(int customerID, String customerName, String customerContact, String customerAddress){
        customer.add(new Customer(customerID ,customerName, customerContact, customerAddress));
    }
    //function remove customer
    public void removeCustomer(int customerID){
        for(int i = 0; i < customer.size(); i++){
            if(customer.get(i).getCustomerID() == customerID){
                customer.remove(i);
            }
        }
    }
    // count total customer
    public int totalCustomer(){
        return customer.size();
    }
    



    // Constructor for register
    public Customer(int customerID, String customerName, String customerContact, String customerAddress) {
        Customer.customerID = customerID;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.customerAddress = customerAddress;
        totalCustomers++; // Increment total customer count
    }

    //find customer by ID
    public Customer findCustomer(int customerID){
        for(int i = 0; i < customer.size(); i++){
            if(customer.get(i).getCustomerID() == customerID){
                return customer.get(i);
            }
        }
        return null;
    }

    //constructor for login
    public Customer(int password,String userName ){
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

