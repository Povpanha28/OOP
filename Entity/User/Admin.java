package Entity.User;

import java.util.HashMap;

public class Admin extends User implements Autentication {
    private static HashMap<Integer, Admin> adminDatabase = new HashMap<>();
    private int AdminID =0;
    private int totalAdmins = 1;
    private static  String role = "Admin";

    

    //Constructor
    public Admin(String username, String password) {
        super(username, password, role);
        this.AdminID = totalAdmins++;

    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String setUsername(String username) {
        this.username = username;
        return username;
        
    }

    public void setPassword(String password) {
        this.password = password;
    }
<<<<<<< Updated upstream
=======
    //show database
    public static HashMap<Integer, Admin> getAdminDatabase() {
        return adminDatabase;
    }
    @Override
    public String getRole() {
        return role;
    }
    @Override 
    public void login() {
        // Implementation of the login method
        System.out.println("logging in as a customer...");
        String username = getUsername();
        String password = getPassword();
        //check if user is already in exists
        for (Admin admin : adminDatabase.values()) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                System.out.println("Login successful.");
                return;
            }
        }
        System.out.println("Login failed. Username or password is incorrect.");
    }
    @Override
    public void register() {
        // Implementation of the register method
        System.out.println("Admin registered successfully.");
        String username = getUsername();
        String password = getPassword(super.email, super.password);
        String email = getEmail(super.email, super.password);
        //check if user is already in exists
        for (Admin admin : adminDatabase.values()) {
            if (admin.getUsername().equals(username)) {
                System.out.println("Registration failed. Username already exists.");
                return;
            }
        }

    }
    
>>>>>>> Stashed changes
}
