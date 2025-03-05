package Entity.User;

import java.util.HashMap;

public class Admin extends User{
    private int adminID;
    private static int totalAdmins = 1;
    private static final String role = "Admin";

    // Static admin database
    private static HashMap<Integer, Admin> adminDatabase = new HashMap<>();

    // Constructor
    public Admin(String username, String password, String email) {
        super(username, password, email);
        this.adminID = totalAdmins++;
    }

    public String getRole() {
        return role;
    }

    public int getAdminID() {
        return adminID;
    }

    public static HashMap<Integer, Admin> getAdminDatabase() {
        return adminDatabase;
    }

    public static Admin getAdminByID(int id) {
        return adminDatabase.getOrDefault(id, null);
    }

    public static void removeAdminByID(int id) {
        if (adminDatabase.containsKey(id)) {
            adminDatabase.remove(id);
            User.getUserDatabase().remove(id);
            System.out.println("Admin with ID " + id + " removed successfully.");
        } else {
            System.out.println("Admin not found.");
        }
    }

    @Override
    public void login() {
        System.out.println("Attempting to log in as Admin...");
        for (Admin admin : adminDatabase.values()) {
            if (admin.getUsername().equals(getUsername()) && admin.getPassword().equals(getPassword())) {
                System.out.println("Login successful for Admin: " + admin.getUsername());
                return;
            }
        }
        System.out.println("Login failed. Invalid username or password.");
    }

    @Override
    public void register() {
        System.out.println("Registering new Admin...");
        if (adminDatabase.containsKey(this.getUserID())) {
            System.out.println("Admin already registered.");
            return;
        }
        adminDatabase.put(this.getUserID(), this);
        User.getUserDatabase().put(this.getUserID(), this);
        System.out.println("Admin registered successfully! Admin ID: " + this.getUserID());
    }

    @Override
    public String toString() {
        return "Admin [ID=" + adminID + ", Username=" + getUsername() + "]";
    }
}
