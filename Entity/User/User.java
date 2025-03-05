package Entity.User;

import java.util.HashMap;

public abstract class User implements Autentication {
    // Instance variables (Private for encapsulation, belong to an object)
    private static int counter = 1;
    private int userID;
    private String username;
    protected String password;
    protected String email;

    private static HashMap<Integer, User> userDatabase = new HashMap<>();

    // Constructor
    public User(String username, String password, String email) {
        this.userID = counter++;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public abstract String getRole();

    // Getter methods
    public int getUserID() {
        return userID;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }
        

    public String getEmail() {
        return email;
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

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", email=" + email + "]";
    }

    public abstract void register();
    public abstract void login();

}
