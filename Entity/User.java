package Entity;

public class User {
    // Instance variables (Private for encapsulation, belong to an object)
    private static int userID = 0;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String role;

    // Constructor 
    public User(String username, String password, String email,String role) {
        userID++;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getter methods
    public int getUserID(String email, String password) {
        if(email.equals(this.email) && password.equals(this.password)) {
            return userID;
        }
        return -1;
    }

    public String getUsername(String email, String password) {
        if(email.equals(this.email) && password.equals(this.password)) {
            return username;
        }
        return null;
    }

    public String getPassword( String email, String password) {
        if(email.equals(this.email) && password.equals(this.password)) {
            return password;
        }
        return null;
    }

    public String getEmail(String email, String password) {
        if(email.equals(this.email) && password.equals(this.password)) {
            return email;
        }
        return null;
    }

    public String getPhone(String email, String password) {
        if(email.equals(this.email) && password.equals(this.password)) {
            return phone;
        }
        return phone;
    }

    public String getAddress(String email, String password) {
        if(email.equals(this.email) && password.equals(this.password)) {
            return address;
        }
        return address;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username, String email, String password) {
        if(email.equals(this.email) && password.equals(this.password)) {
            this.username = username;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static User getUserById(int id) {
        return null;
    }
}
