package Entity.Sales;

import Entity.Exception.DiscountRateException;
import Entity.Exception.InsufficientAmountException;
import Entity.Exception.NegativeNumberException;
import Entity.Exception.OutOfStockException;

public class SalesDiscount extends Sale {
    private double discountRate;

    public SalesDiscount(int customerID, int productID, int amountOfProduct,double totalPrice, double discountRate) throws InsufficientAmountException, NegativeNumberException, OutOfStockException, DiscountRateException {
        super(customerID, productID, amountOfProduct, totalPrice);
        if(discountRate < 0 || discountRate > 1){
            throw new DiscountRateException("Discount cannot be under 0 or over 100%");
        }
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double rate)throws  DiscountRateException{
        if(discountRate < 0 || discountRate > 1){
            throw new DiscountRateException("Discount cannot be under 0 or over 100%");
        }
        this.discountRate = rate;
    }

    @Override
    public void processSale() {
        System.out.println("Processing sale. Total price after discount: " + calculateTotalPrice());
    }

    @Override
    public double calculateTotalPrice() {
        double discountAmount = (totalPrice * discountRate);
        return super.calculateTotalPrice() - discountAmount;
    }
    
}
