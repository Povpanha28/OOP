package Entity.User;

public class Admin extends User implements Autentication {
    private int AdminID =0;
    private int totalAdmins = 1;
    private String password;
    private String username;
    private static  String role = "Admin";


    public Admin(int AdminID, String username, String password, String firstName, String lastName) {
        super(firstName, lastName, username, password);
        this.AdminID = AdminID + totalAdmins;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
