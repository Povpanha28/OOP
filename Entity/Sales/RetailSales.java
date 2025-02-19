package Entity.Sales;

public class RetailSales extends Sale {
    private boolean paymentCompleted;

    public RetailSales(int customerID, int productID, int quantity, double totalPrice) {
        super(customerID, productID, quantity, totalPrice);
        this.paymentCompleted = false;
    }

    @Override
    public void processSale() {
        System.out.println("Processing retail sale. Payment status: " + paymentCompleted);
    }
    
    @Override
    public double calculateTotalPrice() {
        return totalPrice; // For example, calculate price based on quantity
    }
}
