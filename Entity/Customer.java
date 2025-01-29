package Entity;

public class Customer {

    public String customerName;
    public int customerID;
    public String customerContact;
    public String customerAddress;
    
    
    
    public Customer(String customerName, int customerID, String customerContact, String customerAddress){
        this.customerName = customerName;
        this.customerID = customerID;
        this.customerContact = customerContact;
        this.customerAddress = customerAddress;
    }
}
