package Entity.Sales;

import Entity.Exception.*;

public class SaleMain {
    public static void main(String[] args) {
        try {
            // Creating a normal Sale
            System.out.println("----- Normal Sale -----");
            Sale sale1 = new Sale(101, 2001, 3, 1500);
            sale1.processSale();
            System.out.println(sale1);

            // Creating a Sale with Discount
            System.out.println("\n----- Sale with Discount -----");
            SalesDiscount sale2 = new SalesDiscount(102, 2002, 5, 2500, 0.2); // 20% Discount
            sale2.processSale();
            System.out.println(sale2);

            // Testing Admin Update
            System.out.println("\n----- Admin Update -----");
            sale1.setAmountOfProduct(10, "admin123");
            System.out.println("After Admin Update: " + sale1);

            // Testing Unauthorized Update
            System.out.println("\n----- Unauthorized Access Test -----");
            try {
                sale2.setTotalPrice(3000, "wrongpass");
            } catch (UnauthorizedAccessException e) {
                System.out.println(e.getMessage());
            }

            // Discount Rate Exception Test
            System.out.println("\n----- Invalid Discount Rate Test -----");
            try {
                sale2.setDiscountRate(1.5); // Invalid discount
            } catch (DiscountRateException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
