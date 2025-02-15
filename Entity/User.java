package Entity;

import java.util.HashMap;

public class User {
    // Instance variables (Private for encapsulation, belong to an object)
    private static int userID = 0;
    private String username;
    protected String password;
    protected String email;
    private String phone;
    private String address;
    private String role;

    private static HashMap<Integer, User> userDatabase = new HashMap<>();

    // Constructor
    public User(String username, String password, String email, String role) {
        userID++;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;

        userDatabase.put(userID, this);
    }

    // Getter methods
    public int getUserID() {
        return userID;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword(String email, String password) {
        if (email.equals(this.email) && password.equals(this.password)) {
            return password;
        }
        return null;
    }

    public String getEmail(String email, String password) {
        if (email.equals(this.email) && password.equals(this.password)) {
            return email;
        }
        return null;
    }

    public String getPhone(String email, String password) {
        if (email.equals(this.email) && password.equals(this.password)) {
            return phone;
        }
        return phone;
    }

    public String getAddress(String email, String password) {
        if (email.equals(this.email) && password.equals(this.password)) {
            return address;
        }
        return address;
    }

    public String getRole() {
        return role;
    }
    

    public static HashMap<Integer, User> getUserDatabase() {
        return userDatabase;
    }

    public static User getUserByID(int id) {
        if(!userDatabase.containsKey(id)){
            return null;
        }
        return userDatabase.get(id);
    }

    public static void removeUserByID(int id) {
        User user = userDatabase.get(id);
        if (user != null) {
            userDatabase.remove(id);
            System.out.println("User with ID " + id + " removed successfully.");
        } else {
            System.out.println("Unauthorized access or user not found.");
        }
    }

    public void setUsername(String username, String email, String password) {
        if (email.equals(this.email) && password.equals(this.password)) {
            this.username = username;
        }
        else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setPassword(String password, String email, String oldPassword) {
        if (email.equals(this.email) && oldPassword.equals(this.password)) {
            this.password = password;
        }
        else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setEmail(String email, String password) {
        if (email.equals(this.email) && password.equals(this.password)) {
            this.email = email;
        }
        else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setPhone(String phone, String email, String password) {
        if (email.equals(this.email) && password.equals(this.password)) {
            this.phone = phone;
        }
        else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setAddress(String address, String email, String password) {
        if (email.equals(this.email) && password.equals(this.password)) {
            this.address = address;
        }
        else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setRole(String role, String email, String password) {
        if (email.equals(this.email) && password.equals(this.password)) {
            this.role = role;
        }
        else {
            System.out.println("Unauthorized access.");
        }
    }


    public static void setUserDatabase(HashMap<Integer, User> userDatabase, String email, String password) {
        if (email.equals("admin") && password.equals("admin")) {
            User.userDatabase = userDatabase;
        }
        else {
            System.out.println("Unauthorized access.");
        }
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone
                + ", address=" + address + ", role=" + role + "]";
    }


}
