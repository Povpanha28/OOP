package Entity.User;

import Database.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends User {
    private static final String role = "Admin";
    private String password;
    private String email;

    // Constructor
    public Admin(String username) {
        super(username);
    }

    // Getters
    protected String getpass() {
        return password;
    }

    protected String getPassword() {
        return password;
    }

    protected String getEmail() {
        return email;
    }

    @Override
    public String getRole() {
        return role;
    }

    // Override updateUserInDatabase to include admin-specific fields if needed
    @Override
    protected void updateUserInDatabase() {
        String query = "UPDATE admin SET username = ?, password = ? WHERE userID = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getPassword());
            preparedStatement.setString(3, getEmail());
            preparedStatement.setInt(4, getUserID());
            preparedStatement.executeUpdate();
            System.out.println("Admin updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(String userID) {
        String query = "DELETE FROM user WHERE userID = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(userID));
            preparedStatement.executeUpdate();
            System.out.println("Admin removed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(String inputUsername, String inputPassword) {
        // Query to retrieve both username and password from the database
        String query = "SELECT username, password FROM admin WHERE username = ?";
        
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            // Set the username parameter in the query
            preparedStatement.setString(1, inputUsername);
            
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Check if the username exists in the database
            if (resultSet.next()) {
                // Retrieve the stored username and password from the database
                String storedUsername = resultSet.getString("username");
                String storedPassword = resultSet.getString("password");
                
                // Compare the input username and password with the stored values
                if (storedUsername.equals(inputUsername) && storedPassword.equals(inputPassword)) {
                    System.out.println("Admin login successful!");
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

    public static void main(String[] args) {
        Admin admin = new Admin("Youdy");
        admin.login("Youdy", "12345");
    }
}