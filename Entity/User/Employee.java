package Entity.User;

import java.util.HashMap;

public class Employee extends User implements Autentication {
    // Instance variables (Private for encapsulation)
    private int employeeID;
    private String employeeName;
    private String employeeRole;
    private double workHours;
    private double employeeSalary;
    private String paymentMethod;

    // Static variables (Shared across all instances)
    private static int totalEmployees = 0;
    private static HashMap<Integer, Employee> employeeDatabase = new HashMap<>();

    // Constructor (Public: Allows object creation from anywhere)
    public Employee(String username, String password, String email, String role, String employeeName,
            String employeeRole, double workHours, double employeeSalary, String paymentMethod) {
        super(username, password, email, role);
        this.employeeID = ++totalEmployees;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.workHours = workHours;
        this.employeeSalary = employeeSalary;
        this.paymentMethod = paymentMethod;

        employeeDatabase.put(this.employeeID, this);
    }

    // Static methods
    public static Employee getEmployeeByID(int id) {
        return employeeDatabase.getOrDefault(id, null);
    }

    public static void removeEmployeeByID(int id) {
        if (employeeDatabase.containsKey(id)) {
            employeeDatabase.remove(id);
            System.out.println("Employee with ID " + id + " removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static HashMap<Integer, Employee> getEmployeeDatabase() {
        return employeeDatabase;
    }

    // Getter methods
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

    // Setter methods
    public void setEmployeeName(String employeeName, String password) {
        if (password.equals(this.password)) {
            this.employeeName = employeeName;
        } else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setEmployeeRole(String employeeRole, String password) {
        if (password.equals(this.password)) {
            this.employeeRole = employeeRole;
        } else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setWorkHours(double workHours, String password) {
        if (password.equals(this.password)) {
            if (workHours >= 0) {
                this.workHours = workHours;
            } else {
                System.out.println("Invalid work hours. It cannot be negative.");
            }
        } else {
            System.out.println("Unauthorized access.");
        }
    }

    public void setEmployeeSalary(double employeeSalary, String password) {
        if (workHours >= 0) {
            if (employeeSalary >= 0) {
                this.employeeSalary = employeeSalary;
            } else {
                System.out.println("Invalid salary. It cannot be negative.");
            }
        } else {
            System.out.println("Invalid work hours. It cannot be negative.");
        }
    }

    public void setPaymentMethod(String paymentMethod, String password) {
        if (employeeSalary >= 0) {
            this.paymentMethod = paymentMethod;
        } else {
            System.out.println("Invalid salary. It cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return "Employee [ID=" + employeeID + ", Name=" + employeeName + ", Role=" + employeeRole +
                ", Salary=$" + employeeSalary + ", Work Hours=" + workHours + ", Payment Method=" + paymentMethod + "]";
    }

    @Override
    public void login() {
        System.out.println("Attempting to log in...");
        String username = getUsername();
        String password = getPassword(super.email, super.password);

        for (Employee employee : employeeDatabase.values()) {
            if (employee.getUsername().equals(username)
                    && employee.getPassword(super.email, super.password).equals(password)) {
                System.out.println("Login successful for Employee: " + employee.getEmployeeName());
                return;
            }
        }

        System.out.println("Login failed. Invalid username or password.");
    }

    @Override
    public void register() {
        System.out.println("Registering a new employee...");
        String username = getUsername();
        String password = getPassword(super.email, super.password);
        String email = getEmail(super.email, super.password);
        String role = getRole();
        String employeeName = this.employeeName;
        String employeeRole = this.employeeRole;
        double workHours = this.workHours;
        double employeeSalary = this.employeeSalary;
        String paymentMethod = this.paymentMethod;

        // Check if the username already exists
        for (Employee employee : employeeDatabase.values()) {
            if (employee.getUsername().equals(username)) {
                System.out.println("Registration failed. Username already exists.");
                return;
            }
        }

        // Add the new employee to the database
        Employee newEmployee = new Employee(username, password, email, role, employeeName, employeeRole, workHours,
                employeeSalary, paymentMethod);
        User.getUserDatabase().put(newEmployee.getUserID(), newEmployee);
        employeeDatabase.put(newEmployee.getEmployeeID(), newEmployee);
        System.out.println("Employee registered successfully! Employee ID: " + newEmployee.getEmployeeID());
    }
}
