package Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    // Override toString() method to include Employee-specific details
    @Override
    public String toString() {
        return "Employee [userID=" + getUserID() + ", username=" + getUsername() +
                       ", employeeName=" + employeeName + ", employeeRole=" + employeeRole +
                       ", workHours=" + workHours + ", employeeSalary=" + employeeSalary +
                       ", paymentMethod=" + paymentMethod + ", contact=" + contact + "]";
            }
        

    public void login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
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

}
