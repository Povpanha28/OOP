package Entity;
public class Sale {
    String name;
    int saleID;
    int amount;
    int CustomerID;
    String status;
    String saleDate;

    Sale(int amount,int CustomerID){
        this.amount = amount;
        this.CustomerID = CustomerID;
    }
}


