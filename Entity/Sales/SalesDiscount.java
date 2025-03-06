package Entity.Sales;


public class SalesDiscount extends Sale {
    private double discountRate;

    public SalesDiscount(int customerID, int productID, int amountOfProduct,double totalPrice, double discountRate) {
        super(customerID, productID, amountOfProduct, totalPrice);
        if(discountRate < 0 || discountRate > 1){
            System.out.println("Discount cannot be under 0 or over 100%");
        }
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double rate){
        if(discountRate < 0 || discountRate > 1){
            System.out.println("Discount cannot be under 0 or over 100%");
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
