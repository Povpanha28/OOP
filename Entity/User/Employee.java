package Entity.User;

import java.util.HashMap;

public class Employee extends User {
    // Instance variables (Private for encapsulation)
    private String employeeName;
    private String employeeRole;
    private double workHours;
    private double employeeSalary;
    private String contact;

    // Static variables (Shared across all instances)
    private static HashMap<Integer, Employee> employeeDatabase = new HashMap<>();

    // Constructor (Public: Allows object creation from anywhere)
    public Employee(String username, String password, String employeeName,
            String employeeRole, double workHours, double employeeSalary, String paymentMethod, String contact) {
        super(username, password);
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.workHours = workHours;
        this.employeeSalary = employeeSalary;
        this.contact = contact;
    }

    public String getRole() {
        return "Employee";
    }

    // Static methods
    public static Employee getEmployeeByID(int id) {
        return employeeDatabase.getOrDefault(id, null);
    }

    public static void removeEmployeeByID(int id) {
        if (employeeDatabase.containsKey(id)) {
            employeeDatabase.remove(id);
            User.getUserDatabase().remove(id);
            System.out.println("Employee with ID " + id + " removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static HashMap<Integer, Employee> getEmployeeDatabase() {
        return employeeDatabase;
    }

    // Getter methods

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

    public String getContact() {
        return contact;
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

    public void setContact(String contact, String password) {
        if (password.equals(this.password)) {
            this.contact = contact;
        } else {
            System.out.println("Unauthorized access.");
        }
    }

    @Override
    public String toString() {
        return "Employee [employeeName=" + employeeName + ", employeeRole=" + employeeRole + ", workHours=" + workHours
                + ", employeeSalary=" + employeeSalary + ", contact=" + contact
                + "]";
    }

    @Override
    public void login() {
        System.out.println("Attempting to log in...");
        String username = getUsername();
        String password = getPassword();

        for (Employee employee : employeeDatabase.values()) {
            if (employee.getUsername().equals(username)
                    && employee.getPassword().equals(password)) {
                System.out.println("Login successful for Employee: " + employee.getEmployeeName());
                return;
            }
        }

        System.out.println("Login failed. Invalid username or password.");
    }

    @Override
    public void register() {
        System.out.println("Registering a new employee...");

        if (employeeDatabase.containsKey(this.getUserID())) {
            System.out.println("Employee already registered.");
            return;
        }

        employeeDatabase.put(this.getUserID(), this);
        User.getUserDatabase().put(this.getUserID(), this); // Now safe to add
        System.out.println("Employee registered successfully! User ID: " + this.getUserID());
    }

}
