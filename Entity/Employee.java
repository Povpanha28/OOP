package Entity;
// Purpose: Manages staff and their responsibilities.

public class Employee {

    int employeeID;
    String employeeName;
    String employeeRole;
    double employeeSalary;
    
    Employee(String employeeName, int employeeID){
        this.employeeName = employeeName;
        this.employeeID = employeeID;
    }
}
