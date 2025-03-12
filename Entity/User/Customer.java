package Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Database.MySQLConnection;


public class Customer extends User {
    private String paymentMethod;
    private int membershipLevel;

    // Constructor with all fields
    public Customer(String username, String password, String email, String paymentMethod, int membershipLevel) {
        super(username, password, email);
        this.paymentMethod = paymentMethod;
        this.membershipLevel = membershipLevel;
    }

    // New constructor with default values for paymentMethod and membershipLevel
    public Customer(String username, String password, String email) {
        super(username, password, email);
        this.paymentMethod = "DefaultPaymentMethod"; // Default value
        this.membershipLevel = 0; // Default value
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
    protected void updateUserInDatabase() {
        String query = "UPDATE user SET username = ?, password = ?, email = ?, paymentMethod = ?, membershipLevel = ? WHERE userID = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getPassword());
            preparedStatement.setString(3, getEmail());
            preparedStatement.setString(4, this.paymentMethod);
            preparedStatement.setInt(5, this.membershipLevel);
            preparedStatement.setInt(6, getUserID());
            preparedStatement.executeUpdate();
            System.out.println("Customer updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}