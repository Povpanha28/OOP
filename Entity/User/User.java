package Entity.User;

import Database.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class User implements Autentication {
    // Instance variables
    private int user_id;
    private String username;
    protected String password;
    protected String email;

    // Constructor
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters
    public int getUserID() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // Setters with authorization
    public void setUsername(String username, String email, String password) {
        if (email.equals(this.email) && password.equals(this.password)) {
            this.username = username;
            updateUserInDatabase();
        } else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setPassword(String password, String email, String oldPassword) {
        if (email.equals(this.email) && oldPassword.equals(this.password)) {
            this.password = password;
            updateUserInDatabase();
        } else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setEmail(String email, String password) {
        if (password.equals(this.password)) {
            this.email = email;
            updateUserInDatabase();
        } else {
            System.out.println("Unauthorized access.");
        }
    }

    // Method to update user in the database
    protected void updateUserInDatabase() {
        String query = "UPDATE users SET username = ?, password = ?, email = ? WHERE user_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.password);
            preparedStatement.setString(3, this.email);
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
        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.password);
            preparedStatement.setString(3, this.email);
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

    // Method to login a user
    @Override
    public void login() {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.user_id = resultSet.getInt("user_id");
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }
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
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");

                // Create the appropriate User subclass based on the role
                switch (role) {
                    case "Admin":
                        return new Admin(username, password, email);
                    case "Customer":
                        return new Customer(username, password, email);
                    case "Employee":
                        return new Employee(username, password, email);
                    case "Supplier":
                        return new Supplier(username, password, email);
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
        return "User [userID=" + user_id + ", username=" + username + ", email=" + email + "]";
    }

    public abstract String getRole();
}