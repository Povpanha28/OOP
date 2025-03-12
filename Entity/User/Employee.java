package Entity.User;

public class Employee extends User {
    // Additional instance variables for Employee
    private String employeeName;
    private String employeeRole;
    private double workHours;
    private double employeeSalary;
    private String paymentMethod;
    private String contact;

    // Constructor
    public Employee(String username, String password, String email, String employeeName,
                    String employeeRole, double workHours, double employeeSalary, String paymentMethod, String contact) {
        super(username, password, email);
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.workHours = workHours;
        this.employeeSalary = employeeSalary;
        this.paymentMethod = paymentMethod;
        this.contact = contact;
    }

    // Constructor for basic Employee (without additional details)
    public Employee(String username, String password, String email) {
        super(username, password, email);
    }

    @Override
    public String getRole() {
        return "Employee";
    }

    // Additional methods for Employee
    // (You can add methods specific to Employee here)
}