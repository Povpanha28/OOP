package Entity.User;

import Database.MySQLConnection;
import java.sql.*;
import java.util.Scanner;

public class ManageEmployee {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
    }

    public static void addEmployee() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter role: ");
        String role = scanner.nextLine();
        System.out.print("Enter work hours: ");
        double workHours = scanner.nextDouble();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter contact: ");
        String contact = scanner.nextLine();

        String query = "INSERT INTO Employee (username, password, employee_name, employee_role, work_hours, employee_salary, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, role);
            pstmt.setDouble(5, workHours);
            pstmt.setDouble(6, salary);
            pstmt.setString(7, contact);

            pstmt.executeUpdate();
            System.out.println("Employee added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    public static void viewEmployees() {
        String query = "SELECT * FROM Employee";
        try (Connection connection = MySQLConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n===== Employee List =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("employee_id") + " | Name: " + rs.getString("employee_name") +
                        " | Role: " + rs.getString("employee_role") + " | Salary: " + rs.getDouble("employee_salary"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
        }
    }

    public static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid ID! Returning to menu.");
            scanner.nextLine();
            return;
        }
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new role: ");
        String newRole = scanner.nextLine();
        System.out.print("Enter new salary: ");
        double newSalary = scanner.nextDouble();
        scanner.nextLine();

        String query = "UPDATE Employee SET employee_name=?, employee_role=?, employee_salary=? WHERE employee_id=?";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, newRole);
            pstmt.setDouble(3, newSalary);
            pstmt.setInt(4, employeeId);

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? "Employee updated successfully!" : "Employee not found!");
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    public static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid ID! Returning to menu.");
            scanner.nextLine();
            return;
        }
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        String query = "DELETE FROM Employee WHERE employee_id=?";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, employeeId);
            int rowsDeleted = pstmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? "Employee deleted successfully!" : "Employee not found!");
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }
}