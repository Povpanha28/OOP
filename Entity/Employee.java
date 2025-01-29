package Entity;
// Purpose: Manages staff and their responsibilities.

public class Employee {

    int employeeID;
    String employeeName;
    String employeeRole;
    double workHours;
    double employeeSalary;
    String payment; // Card, Cash
    
    Employee(String employeeName, int employeeID, String employeeRole, double workHours, double employeeSalary, String payment){
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.employeeRole = employeeRole;
        this.workHours = workHours;
        this.employeeSalary = employeeSalary;
        this.payment = payment;
    }
}
