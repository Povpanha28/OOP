package Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Database.MySQLConnection;



public class membership extends User {
    
    // New constructor with default values for paymentMethod and membershipLevel
    public membership(String username,int user_id) {
        super(username, user_id);
    }

    public membership(String username){
        super(username);

    }

    @Override
    public String getRole() {
        return "Membership";
    }

    @Override
    protected void updateUserInDatabase() {
        String query = "UPDATE membership SET username = ?, password = ?, email = ?, paymentMethod = ? WHERE member_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setInt(6, getUserID());
            preparedStatement.executeUpdate();
            System.out.println("Customer updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}