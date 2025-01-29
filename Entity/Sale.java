package Entity;
public class Sale {
    String name;
    int saleID;
    int amount;
    int CustomerID;
    String saleDate;

    Sale(int saleID, int amount,int CustomerID){
        this.saleID = saleID;
        this.amount = amount;
        this.CustomerID = CustomerID;
    }
}


