package Entity;
public class Sale {

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


