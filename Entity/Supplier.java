package Entity;

import java.util.HashMap;

public class Supplier extends User implements Autentication {
    // Instance variables (Private for encapsulation)
    private String companyName;
    private String companyAddress;
    private String companyContact;

    private static HashMap<Integer, Supplier> supplierDatabase = new HashMap<>();

    // Constructor (Public: Allows object creation from anywhere)
    public Supplier(String username, String password, String email, String role, String companyName,
            String companyAddress, String companyContact) {
        super(username, password, email, role);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    // Getter methods (Public: Provides controlled access to private variables)

    // Static methods
    public static Supplier getUserByID(int id) {
        if (!supplierDatabase.containsKey(id)) {
            return null;
        }
        return supplierDatabase.get(id); // Retrieve payment by ID
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public static HashMap<Integer, Supplier> getSupplierDatabase() {
        return supplierDatabase;
    }

    // Remove supplier by ID
    public static void removeSupplierByID(int id) {
        Supplier supplier = supplierDatabase.get(id);
        if (supplier != null) {
            supplierDatabase.remove(id);
            System.out.println("Supplier with ID " + id + " removed successfully.");
        } else {
            System.out.println("Unauthorized access or supplier not found.");
        }
    }

    public void setCompanyName(String companyName, String password) {
        if (password.equals(this.password)) {
            this.companyName = companyName;
        } else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public static void setSupplierDatabase(HashMap<Integer, Supplier> supplierDatabase) {
        Supplier.supplierDatabase = supplierDatabase;
    }

    @Override
    public String toString() {
        return "Supplier [companyName=" + companyName + ", companyAddress=" + companyAddress + ", companyContact="
                + companyContact + "]";
    }

    @Override
    public void login() {
        System.out.println("Attempting to log in...");
        String username = getUsername();
        String password = getPassword(super.email, super.password);

        for (Supplier supplier : supplierDatabase.values()) {
            if (supplier.getUsername().equals(username)
                    && supplier.getPassword(super.email, super.password).equals(password)) {
                System.out.println("Login successful for Supplier: " + supplier.getCompanyName());
                return;
            }
        }

        System.out.println("Login failed. Invalid username or password.");
    }

    @Override
    public void register() {
        System.out.println("Registering a new supplier...");
        String username = getUsername();
        String password = getPassword(super.email, super.password);
        String email = getEmail(super.email, super.password);
        String role = getRole();
        String companyName = this.companyName;
        String companyAddress = this.companyAddress;
        String companyContact = this.companyContact;

        // Check if the username already exists
        for (Supplier supplier : supplierDatabase.values()) {
            if (supplier.getUsername().equals(username)) {
                System.out.println("Registration failed. Username already exists.");
                return;
            }
        }

        // Add the new supplier to the database
        Supplier newSupplier = new Supplier(username, password, email, role, companyName, companyAddress, companyContact);
        User.getUserDatabase().put(newSupplier.getUserID(), newSupplier);
        supplierDatabase.put(newSupplier.getUserID(), newSupplier);
        System.out.println("Supplier registered successfully! User ID: " + newSupplier.getUserID());
    }

}
