package Entity;

public class Supplier {
    // Instance variables (Private for encapsulation)
    private int supplierID;
    private String supplierName;
    private String contactNumber;

    private static int totalSuppliers = 0;
    private static String password = "password";

    // Constructor (Public: Allows object creation from anywhere)
    public Supplier(String supplierName, String contactNumber) {
        this.supplierID = ++totalSuppliers;
        this.supplierName = supplierName;
        this.contactNumber = contactNumber;
    }

    // Getter methods (Public: Provides controlled access to private variables)
    public int getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    //Setter methods
    public void setSupplierName(String supplierName, String password) {
        if (password.equals(Supplier.password)) {
            this.supplierName = supplierName;
        } else {
            System.out.println("Incorrect password. Supplier name cannot be changed.");
        }
    }

    public void setContactNumber(String contactNumber, String password) {
        if (password.equals(Supplier.password)) {
            this.contactNumber = contactNumber;
        } else {
            System.out.println("Incorrect password. Contact number cannot be changed.");
        }
    }

}

