package Entity.User;

<<<<<<< HEAD
public class Admin {
=======
public class Admin extends User implements Autentication {
    private int AdminID =0;
    private int totalAdmins = 1;
    
    private static  String role = "Admin";


    //Constructor
    public Admin(String username, String password) {
        super(username, password, role);
        this.AdminID = totalAdmins++;
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
>>>>>>> 5895c8945b0f6989731ae25a516b1aa013204088
    
}
