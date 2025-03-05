package Entity.Sales;

public class CashPayment extends Payment {

    private String cashierName;
    private double moneyGiven;   // The amount of money the customer gives
    private double change;       // The change to be returned to the customer

    // Constructor for CashPayment
    public CashPayment(int saleID, double amountPaid, String transactionDate, String cashierName, double moneyGiven) {
        super(saleID, amountPaid, transactionDate, "Cash"); // "Cash" is the payment method
        this.cashierName = cashierName;
        this.moneyGiven = moneyGiven;
        calculateChange(); // Automatically calculate the change on creation
    }

    // Getter for cashierName
    public String getCashierName() {
        return cashierName;
    }

    // Getter and Setter for moneyGiven
    public double getMoneyGiven() {
        return moneyGiven;
    }

    public void setMoneyGiven(double moneyGiven) {
        this.moneyGiven = moneyGiven;
        calculateChange();  // Recalculate change whenever moneyGiven changes
    }

    // Getter for change
    public double getChange() {
        return change;
    }

    // Method to calculate change
    private void calculateChange() {
        if (moneyGiven >= getAmountPaid()) {
            this.change = moneyGiven - getAmountPaid();
            System.out.println("Change is: "+change);
        } else {
            this.change = 0.0;  // If the money given is less than amount paid, no change
            System.out.println("Error: Insufficient money given for the payment.");
        }
    }

    // Implement abstract methods for CashPayment

    @Override
    public boolean processPayment(){
        // Cash payment doesn't require a processing gateway
        System.out.println("Processing cash payment of amount: " + amountPaid);
        calculateChange(); // Recalculate change after processing
        return true;
    }
    

    @Override
    public boolean validatePayment() {
        // Cash payments are generally validated manually, but here we can assume validation is always successful
        System.out.println("Cash payment is validated.");
        return true;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Cashier='" + cashierName + '\'' +
                ", MoneyGiven=" + moneyGiven +
                ", Change=" + change;
    }
}
