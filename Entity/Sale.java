package Entity;
public class Sale {
    String name;
    int saleID;
    int amount;
    int CustomerID;
    String saleDate;

<<<<<<< HEAD
    Sale(int amount,int CustomerID){
=======
    Sale(int saleID, int amount,int CustomerID){
        this.saleID = saleID;
>>>>>>> 157b034ccf701c0b0e08a2fff8e1105867351385
        this.amount = amount;
        this.CustomerID = CustomerID;
    }
}


