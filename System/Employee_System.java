package System;

import Entity.User.Employee;

public class Employee_System {
    public static void main(String[] args) {

        Employee emp = new Employee("thyrak", "123");
        // Register a new employee login
        emp.register();
        emp.login();

        Employee emp1 = new Employee("John Doe", "Manager", 40, 50000, "Card");
        System.out.println(emp1.getEmployeeID());
        


        // Attempt to log in
    }

}
