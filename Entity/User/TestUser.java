package Entity.User;

import java.util.Scanner;

public class TestUser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the main menu
            System.out.println("Choose an option to register a user:");
            System.out.println("1. Admin");
            System.out.println("2. Employee");
            System.out.println("3. Customer");
            System.out.println("4. Supplier");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline after number input

            switch (choice) {
                case 1:
                    // Admin registration
                    System.out.println("Enter details for Admin:");
                    System.out.print("Enter username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String adminPassword = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String adminEmail = scanner.nextLine();
                    User admin = new Admin(adminUsername, adminPassword, adminEmail);
                    admin.register();
                    break;

                case 2:
                    // Employee registration
                    System.out.println("Enter details for Employee:");
                    System.out.print("Enter username: ");
                    String employeeUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String employeePassword = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String employeeEmail = scanner.nextLine();
                    System.out.print("Enter employee name: ");
                    String employeeName = scanner.nextLine();
                    System.out.print("Enter employee role: ");
                    String employeeRole = scanner.nextLine();
                    System.out.print("Enter work hours: ");
                    double workHours = scanner.nextDouble();
                    System.out.print("Enter salary: ");
                    double employeeSalary = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter payment method: ");
                    String paymentMethod = scanner.nextLine();
                    System.out.print("Enter contact: ");
                    String contact = scanner.nextLine();
                    User employee = new Employee(employeeUsername, employeePassword, employeeEmail, employeeName, employeeRole,
                            workHours, employeeSalary, paymentMethod, contact);
                    employee.register();
                    break;

                case 3:
                    // Customer registration
                    System.out.println("Enter details for Customer:");
                    System.out.print("Enter username: ");
                    String customerUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String customerPassword = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String customerEmail = scanner.nextLine();
                    System.out.print("Enter payment method: ");
                    String customerPaymentMethod = scanner.nextLine();
                    System.out.print("Enter number of orders: ");
                    int customerOrders = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    User customer = new Customer(customerUsername, customerPassword, customerEmail, customerPaymentMethod, customerOrders);
                    customer.register();
                    break;

                case 4:
                    // Supplier registration
                    System.out.println("Enter details for Supplier:");
                    System.out.print("Enter username: ");
                    String supplierUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String supplierPassword = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String supplierEmail = scanner.nextLine();
                    System.out.print("Enter supplier company name: ");
                    String companyName = scanner.nextLine();
                    System.out.print("Enter supplier address: ");
                    String supplierAddress = scanner.nextLine();
                    System.out.print("Enter contact: ");
                    String supplierContact = scanner.nextLine();
                    User supplier = new Supplier(supplierUsername, supplierPassword, supplierEmail, companyName, supplierAddress, supplierContact);
                    supplier.register();
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
