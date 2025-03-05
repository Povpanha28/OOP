package Entity.Sales;

import Entity.ExceptionSrc.DiscountRateException;

public class SaleMain {
    public static void main(String[] args) {
        try{
            Sale sale1 = new Sale(2, 2, 5, 200);
            Sale sale2 = new SalesDiscount(1, 1, 10, 100, 0.2);
            Sale sale3 = new SalesDiscount(3, 3, 15, 300, 1.5);
            DiscountRateException discountRateException = new DiscountRateException(1.5);
        } catch (DiscountRateException e){
            System.out.println(e.getMessage());
        }
    }
}
