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
    //getter
    protected String getpass(){
        return password;
    }
    protected String getEmail(){
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
                private String getPassword() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
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
    public void login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
    

}