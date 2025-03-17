package Entity.User;

import Database.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class User implements Autentication {

    // Instance variables
    protected int user_id;
    protected String username;


    // Constructor
    public User(String username, int user_id2) {
        this.username = username;
    }
    //new constructor
    public User(String username){
        this.username =username;
    }

    // Getters
    public int getUserID() {
        return user_id;
    }
    protected String getUsername() {
        return username;
    }

    // Method to update user in the database
    protected void updateUserInDatabase() {
        String query = "UPDATE users SET username = ? WHERE user_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, this.username);
            preparedStatement.setInt(4, this.user_id);
            preparedStatement.executeUpdate();
            System.out.println("User updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to register a new user
    @Override
    public void register() {
        String query = "INSERT INTO users (username, role) VALUES (?, ?)";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(4, this.getRole()); // Add role to the query
            preparedStatement.executeUpdate();

            // Retrieve the generated userID
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                this.user_id = resultSet.getInt(1);
            }
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to remove a user
    public static void removeUserByID(int id) {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User with ID " + id + " removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to find a user by ID
    public static User getUserByID(int id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String role = resultSet.getString("role");

                // Create the appropriate User subclass based on the role
                switch (role) {
                    case "Admin":
                        return new Admin(username);
                    case "Membership":
                        return new membership(username);
                    case "Employee":
                        return new Employee(username);
                    default:
                        throw new IllegalArgumentException("Unknown role: " + role);
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "User [userID=" + user_id + ", username=" + username + "]";
    }



    public abstract String getRole();
}