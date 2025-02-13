package Entity;

import java.util.HashMap;

public class Supplier {
    // Instance variables (Private for encapsulation)
    private int supplierID;
    private String supplierName;
    private String contactNumber;
    private String address;
    private String companyName;

    private static int totalSuppliers = 0;
    private static String password = "password";
    private static HashMap<Integer, Supplier> supplierDatabase = new HashMap<>();

    // Constructor (Public: Allows object creation from anywhere)
    public Supplier(String supplierName, String contactNumber, String address, String companyName) {
        this.supplierID = ++totalSuppliers;
        this.supplierName = supplierName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.companyName = companyName;

        supplierDatabase.put(this.supplierID, this);
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

    public String getAddress() {
        return address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public static Supplier getPaymentByID(int id) {
        if(!supplierDatabase.containsKey(id)){
            return null;
        }
        return supplierDatabase.get(id); // Retrieve payment by ID
    }

    public static void removePaymentByID(int id, String password) {
        Supplier supplier = supplierDatabase.get(id);
        if (supplier != null && password.equals(Supplier.password)) {
            supplierDatabase.remove(id);
            System.out.println("Payment with ID " + id + " removed successfully.");
        } else {
            System.out.println("Unauthorized access or payment not found.");
        }
    }

    public static HashMap<Integer, Supplier> getAllPayments() {
        return supplierDatabase; // Retrieve all payments
    }

    // Setter methods
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

    public void setAddress(String address, String password) {
        if (password.equals(Supplier.password)) {
            this.address = address;
        } else {
            System.out.println("Incorrect password. Address cannot be changed.");
        }
    }

    public void setCompanyName(String companyName, String password) {
        if (password.equals(Supplier.password)) {
            this.companyName = companyName;
        } else {
            System.out.println("Incorrect password. Company name cannot be changed.");
        }
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "SupplierID=" + supplierID +
                ", SupplierName='" + supplierName + '\'' +
                ", ContactNumber='" + contactNumber + '\'' +
                ", Address='" + address + '\'' +
                ", CompanyName='" + companyName + '\'' +
                '}';
    }
}
