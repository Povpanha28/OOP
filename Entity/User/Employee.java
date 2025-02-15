package Entity.User;

import java.util.HashMap;

// Purpose: Manages staff and their responsibilities.
public class Employee extends User implements Autentication {
    // Instance variables (Private for encapsulation)
    private int employeeID;
    private String employeeName;
    private String employeeRole;
    private double workHours;
    private double employeeSalary;
    private String paymentMethod; // Card, Cash
    private int transactionID;
    

    private final String adminPassword = "SecurePass123";	

    private String username;
    private String password;

    // Static variables (Shared across all instances)
    private static int totalEmployees = 0;
    private static HashMap<String, String> credentials = new HashMap<>(); // To store usernames and passwords
    private static HashMap<Integer, Employee> employeeDatabase = new HashMap<>(); // To store usernames and passwords

    // Constructor (Public: Allows object creation from anywhere)
    public Employee(String employeeName, String employeeRole, double workHours, double employeeSalary,
            String paymentMethod) {
        this.employeeID = ++totalEmployees;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        setWorkHours(workHours); // Validation applied
        setEmployeeSalary(employeeSalary); // Validation applied
        this.paymentMethod = paymentMethod;

        employeeDatabase.put(this.employeeID, this);
    }

    // Registration
    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter methods (Public: Provides controlled access to private variables)
    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public double getWorkHours() {
        return workHours;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public static Employee getEmployeeByID(int id) {
        if (!employeeDatabase.containsKey(id)) {
            return null;
        }
        return employeeDatabase.get(id); // Retrieve payment by ID
    }

    public static void removePaymentByID(int id, String password) {
        Employee employee = employeeDatabase.get(id);
        if (employee != null && employee.isAuthorized(password)) {
            employeeDatabase.remove(id);
            System.out.println("Payment with ID " + id + " removed successfully.");
        } else {
            System.out.println("Unauthorized access or payment not found.");
        }
    }

    public static HashMap<Integer, Employee> getAllPayments() {
        return employeeDatabase; // Retrieve all payments
    }

    // Password
    protected boolean isAuthorized(String password) {
        return password.equals(adminPassword);
    }

    // Setter methods (Public: Allows modifying private variables with validation)
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public void setWorkHours(double workHours) {
        if (workHours >= 0) {
            this.workHours = workHours;
        } else {
            System.out.println("Invalid work hours. It cannot be negative.");
        }
    }

    public void setEmployeeSalary(double employeeSalary) {
        if (employeeSalary >= 0) {
            this.employeeSalary = employeeSalary;
        } else {
            System.out.println("Invalid salary. It cannot be negative.");
        }
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    // Method with local variable scope
    public void displayEmployeeInfo() {
        String info = "Employee ID: " + employeeID + ", Name: " + employeeName + ", Role: " + employeeRole +
                ", Salary: $" + employeeSalary + ", Work Hours: " + workHours + ", Payment Method: " + paymentMethod;
        System.out.println(info);
    }

    @Override
    public void login() {
        if (username != null && password != null) {
            if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
                System.out.println("Login successful! Welcome, " + username);
            } else {
                System.out.println("Login failed. Invalid username or password.");
            }
        } else {
            System.out.println("Username and password must not be null for login.");
        }
    }

    @Override
    public void register() {
        if (username != null && password != null) {
            if (credentials.containsKey(username)) {
                System.out.println("Registration failed. Username already exists.");
            } else {
                credentials.put(username, password);
                System.out.println("Registration successful! You can now log in.");
            }
        } else {
            System.out.println("Username and password must not be null for registration.");
        }
    }

}
