package Entity.Sales;

public class Test {
    public static void main(String[] args) {
        Sale sale1 = new RetailSales(1, 1, 2, 100);
        Sale sale2 = new SalesDiscount(2, 2, 1, 150, 0.5);
        Sale.getSaleByID(1).processSale();
        System.out.println(sale2.calculateTotalPrice());
    }
}
