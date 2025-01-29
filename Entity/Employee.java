package Entity;

// Purpose: Manages staff and their responsibilities.
public class Employee {
    // Instance variables (Private for encapsulation)
    private int employeeID;
    private String employeeName;
    private String employeeRole;
    private double workHours;
    private double employeeSalary;
    private String paymentMethod; // Card, Cash
    private int transactionID;

    // Static variable (Shared across all instances)
    private static int totalEmployees = 0;

    // Constructor (Public: Allows object creation from anywhere)
    public Employee(String employeeName, int employeeID, String employeeRole, double workHours, double employeeSalary, String paymentMethod) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.employeeRole = employeeRole;
        setWorkHours(workHours); // Validation applied
        setEmployeeSalary(employeeSalary); // Validation applied
        this.paymentMethod = paymentMethod;
        totalEmployees++; // Increment total employee count
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

    public static int getTotalEmployees() {
        return totalEmployees;
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
}

