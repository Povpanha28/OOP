package Entity;

public class Customer {

    String customerName;
    int customerID;
    String customerContact;
    String customerAddress;
    
    Customer(String customerName, int customerID,String customerContact,String customerAddress){
        this.customerName = customerName;
        this.customerID = customerID;
        this.customerContact = customerContact;
        this.customerAddress = customerAddress;
    }
}
