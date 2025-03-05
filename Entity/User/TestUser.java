package Entity.User;

import Database.MySQLConnection;
import java.sql.*;
import java.util.List;

public class TestUser {
    public static void main(String[] args) {
        User admin1 = new Admin("admin1", "admin123", "thyrak123@gmail.com");
        admin1.register();

        User supplier1 = new Supplier("Ali", "1234", "Ali123@gmail.com", "Alibaba", "123 St Sam", "017234234");
        supplier1.register();

        User customer1 = new Customer("John", "1234", "John@gmail.com", "Cash", 1);
        customer1.register();

        User employee = new Employee("John", "1243", "John@gmail.com", "John", "Cashier", 30, 250, "Cash", "012345345");
        employee.register();

        // Add all users to the database
        addUsersToDatabase(List.of(admin1, supplier1, customer1, employee));
    }

    public static void addUsersToDatabase(List<User> users) {
        String queryUser = "INSERT INTO User (username, password, email, role) VALUES (?, ?, ?, ?)";
        String querySupplier = "INSERT INTO Supplier (user_id, company_name, company_address, company_contact) VALUES (?, ?, ?, ?)";
        String queryEmployee = "INSERT INTO Employee (user_id, employee_name, employee_role, work_hours, employee_salary, payment_method, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String queryCustomer = "INSERT INTO Customer (user_id, payment_method, membership_level) VALUES (?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmtUser = connection.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmtSupplier = connection.prepareStatement(querySupplier);
             PreparedStatement pstmtEmployee = connection.prepareStatement(queryEmployee);
             PreparedStatement pstmtCustomer = connection.prepareStatement(queryCustomer)) {

            for (User user : users) {
                // Insert user into User table
                pstmtUser.setString(1, user.getUsername());
                pstmtUser.setString(2, user.getPassword());
                pstmtUser.setString(3, user.getEmail());

                // Determine the role and set it
                String role = "Unknown"; // Default value
                if (user instanceof Admin) {
                    role = "Admin";
                } else if (user instanceof Supplier) {
                    role = "Supplier";
                } else if (user instanceof Customer) {
                    role = "Customer";
                } else if (user instanceof Employee) {
                    role = "Employee";
                }
                pstmtUser.setString(4, role);
                
                pstmtUser.executeUpdate();
                
                // Get generated user_id
                ResultSet generatedKeys = pstmtUser.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);

                    // Insert into specific tables
                    if (user instanceof Supplier) {
                        Supplier supplier = (Supplier) user;
                        pstmtSupplier.setInt(1, userId);
                        pstmtSupplier.setString(2, supplier.getCompanyName());
                        pstmtSupplier.setString(3, supplier.getCompanyAddress());
                        pstmtSupplier.setString(4, supplier.getCompanyContact());
                        pstmtSupplier.executeUpdate();
                    }

                    if (user instanceof Employee) {
                        Employee employee = (Employee) user;
                        pstmtEmployee.setInt(1, userId);
                        pstmtEmployee.setString(2, employee.getEmployeeName());
                        pstmtEmployee.setString(3, employee.getEmployeeRole());
                        pstmtEmployee.setDouble(4, employee.getWorkHours());
                        pstmtEmployee.setDouble(5, employee.getEmployeeSalary());
                        pstmtEmployee.setString(6, employee.getPaymentMethod());
                        pstmtEmployee.setString(7, employee.getContact());
                        pstmtEmployee.executeUpdate();
                    }

                    if (user instanceof Customer) {
                        Customer customer = (Customer) user;
                        pstmtCustomer.setInt(1, userId);
                        pstmtCustomer.setString(2, customer.getPaymentMethod());
                        pstmtCustomer.setInt(3, customer.getMembershipLevel());
                        pstmtCustomer.executeUpdate();
                    }
                }
            }

            System.out.println("Users added to the database successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding users to the database!");
            e.printStackTrace();
        }
    }
}
