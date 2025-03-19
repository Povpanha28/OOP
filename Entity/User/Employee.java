package Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.MySQLConnection;

public class Employee extends User {
    private static final String role = "Employee";

    
    // Additional instance variables for Employee
    private String employeeName;
    private String employeeRole;
    private double workHours;
    private double employeeSalary;
    private String paymentMethod;
    private String contact;

    // Constructor to initialize all fields
    public Employee(String username,int user_id, String employeeName,String employeeRole, double workHours, double employeeSalary,String contact, String paymentMethod) {
        super(username, user_id); // Initialize username and user_id using the User class constructor
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.workHours = workHours;
        this.employeeSalary = employeeSalary;
        this.paymentMethod = paymentMethod;
        this.contact = contact;
    }
    public Employee(String username){
        super(username);
    }



    // Getters and Setters for additional fields
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public void addToMembership(String x) {
        String query = "INSERT INTO Membership (member_name) VALUES (?)";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, x);
            preparedStatement.executeUpdate();
            System.out.println("User added to membership successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Override toString() method to include Employee-specific details
    @Override
    public String toString() {
        return "Employee [userID=" + getUserID() + ", username=" + getUsername() +
                       ", employeeName=" + employeeName + ", employeeRole=" + employeeRole +
                       ", workHours=" + workHours + ", employeeSalary=" + employeeSalary +
                       ", paymentMethod=" + paymentMethod + ", contact=" + contact + "]";
            }
        


    @Override
    protected void updateUserInDatabase(){
        String query = "UPDATE employee SET username = ?, password = ? WHERE employee_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setInt(6, getUserID());
            preparedStatement.executeUpdate();
            System.out.println("Customer updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    };
    @Override
    public String getRole(){
        return role;
    }
    @Override
    public void login(String username, String password){
        String query = "SELECT username, password FROM admin WHERE username = ?";
        
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            // Set the username parameter in the query
            preparedStatement.setString(1, username);
            
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Check if the username exists in the database
            if (resultSet.next()) {
                // Retrieve the stored username and password from the database
                String storedUsername = resultSet.getString("username");
                String storedPassword = resultSet.getString("password");
                
                // Compare the input username and password with the stored values
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
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
    //main method
    public static void main(String[] args) {
        Employee employee = new Employee("John Doe", 1, "John Doe", "Software Engineer", 40, 50000, "john.doe@example.com", "PayPal");
        employee.addToMembership("Employee Membership");
        System.out.println(employee);
        employee.updateUserInDatabase();
        employee.login("John Doe", "password");
    

}
}
