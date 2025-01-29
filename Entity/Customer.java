package Entity;

public class Customer {
    // Instance variables (Private for encapsulation)
    private String customerName;
    private int customerID;
    private String customerContact;
    private String customerAddress;

    // Static variable (Shared across all instances)
    private static int totalCustomers = 0;

    // Constructor (Public: Allows object creation from anywhere)
    public Customer(String customerName, int customerID, String customerContact, String customerAddress) {
        this.customerName = customerName;
        this.customerID = customerID;
        this.customerContact = customerContact;
        this.customerAddress = customerAddress;
        totalCustomers++; // Increment total customer count
    }

    // Getter methods (Public: Provides controlled access to private variables)
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

    // Setter methods (Public: Allows modifying private variables with validation)
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerContact(String customerContact) {
        if (customerContact.matches("\\d{10}")) { // Ensures it's a 10-digit number
            this.customerContact = customerContact;
        } else {
            System.out.println("Invalid contact number. It must be a 10-digit number.");
        }
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

