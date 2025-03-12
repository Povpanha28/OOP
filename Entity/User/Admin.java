package Entity.User;

import Database.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends User {
    private static final String role = "Admin";

    // Constructor
    public Admin(String username, String password, String email) {
        super(username, password, email);
    }

    @Override
    public String getRole() {
        return role;
    }

    // Override updateUserInDatabase to include admin-specific fields if needed
    @Override
    protected void updateUserInDatabase() {
        String query = "UPDATE user SET username = ?, password = ?, email = ? WHERE userID = ?";
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

    @Override
    public void register() {
        String query = "INSERT INTO user (username, password, email, role) VALUES (?, ?, ?, 'Admin')";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getPassword());
            preparedStatement.setString(3, getEmail());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int userID = resultSet.getInt(1);
                System.out.println("Admin registered successfully with ID: " + userID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}