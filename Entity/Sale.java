package Entity;
public class Sale {

    int saleID;
    int amount;
    int CustomerID;
    String status;
    String saleDate;

    Sale(int saleID, int amount,String saleDate){
        this.saleDate = saleDate;
        this.saleID = saleID;
        this.amount = amount;
    }
}


