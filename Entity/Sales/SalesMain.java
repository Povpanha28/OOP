package Entity.Sales;

import java.util.Scanner;
import Entity.ExceptionSrc.NumberOnlyException;
import Entity.ExceptionSrc.InsufficientAmountException;

public class SalesMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===== Cloth Shop Management System =====");
            System.out.println("1. Add Sale");
            System.out.println("2. View Sale by ID");
            System.out.println("3. Update Sale Amount (Admin Only)");
            System.out.println("4. Show All Sales");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            try {
                String choiceInput = scanner.nextLine();
                new NumberOnlyException(choiceInput, "\\d+");
                choice = Integer.parseInt(choiceInput);

                switch (choice) {
                    case 1:
                        try {
                            System.out.print("Enter Customer ID: ");
                            String customerIDInput = scanner.nextLine();
                            new NumberOnlyException(customerIDInput, "\\d+");
                            int customerID = Integer.parseInt(customerIDInput);

                            System.out.print("Enter Product ID: ");
                            String productIDInput = scanner.nextLine();
                            new NumberOnlyException(productIDInput, "\\d+");
                            int productID = Integer.parseInt(productIDInput);

                            System.out.print("Enter Amount of Product: ");
                            String amountInput = scanner.nextLine();
                            new NumberOnlyException(amountInput, "\\d+");
                            int amount = Integer.parseInt(amountInput);

                            System.out.print("Enter Total Price: ");
                            double price = scanner.nextDouble();
                            scanner.nextLine();

                            new InsufficientAmountException(price, price);

                            Sale newSale = new Sale(customerID, productID, amount, price);
                            System.out.println("Sale Added Successfully: " + newSale);
                        } catch (NumberOnlyException | InsufficientAmountException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Enter Sale ID to View: ");
                        int saleID = scanner.nextInt();
                        Sale sale = Sale.getSaleByID(saleID);
                        if (sale != null) {
                            System.out.println(sale);
                        }
                        scanner.nextLine();
                        break;

                    case 3:
                        System.out.print("Enter Sale ID to Update: ");
                        int updateID = scanner.nextInt();
                        scanner.nextLine();
                        Sale updateSale = Sale.getSaleByID(updateID);
                        if (updateSale != null) {
                            System.out.print("Enter Admin Password: ");
                            String password = scanner.nextLine();
                            try {
                                System.out.print("Enter New Amount of Product: ");
                                String newAmountInput = scanner.nextLine();
                                new NumberOnlyException(newAmountInput, "\\d+");
                                int newAmount = Integer.parseInt(newAmountInput);
                                updateSale.setAmountOfProduct(newAmount, password);
                                System.out.println("Updated Sale: " + updateSale);
                            } catch (NumberOnlyException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;

                    case 4:
                        System.out.println("All Sales: " + Sale.getAllSales());
                        break;

                    case 5:
                        System.out.println("Exiting... Thank you!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
