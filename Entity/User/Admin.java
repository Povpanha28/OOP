package Entity.User;

import Database.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Admin extends User {
    private static final String role = "Admin";
    private boolean isLoggedIn = false; // New field to track login status
    private Connection connection;


    // Constructor
    public Admin(String username) {
        super(username);
    }

    @Override
    public String getRole() {
        return role;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public void login(String inputUsername, String inputPassword) {
        String query = "SELECT username, password FROM admin WHERE username = ?";
    
        try {
            connection = MySQLConnection.getConnection(); // Assign connection to the field
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inputUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                String storedUsername = resultSet.getString("username");
                String storedPassword = resultSet.getString("password");
    
                if (storedUsername.equals(inputUsername) && storedPassword.equals(inputPassword)) {
                    System.out.println("Admin login successful!");
                    isLoggedIn = true;
                } else {
                    System.out.println("Invalid username or password.");
                }
            } else {
                System.out.println("Admin username not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void addEmployee(String username, String role, String password) {
        if (!isLoggedIn) {
            System.out.println("Access Denied: Admin must log in first!");
            return;
        }
    
        if (connection == null) { // Ensure the connection is open
            System.out.println("Error: Database connection is closed.");
            return;
        }
    
        String query = "INSERT INTO employee (username, role, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, role);
            preparedStatement.setString(3, password);
    
            int rowsInserted = preparedStatement.executeUpdate();
    
            if (rowsInserted > 0) {
                System.out.println("Employee added successfully!");
            } else {
                System.out.println("Failed to add employee.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        Admin admin = new Admin("Youdy");
        admin.login("Youdy", "12345");
        admin.addEmployee("thyrak", "Manager", "1234"); // Will only work if login is successful
    }
}
