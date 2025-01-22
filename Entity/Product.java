package Entity;
// Purpose: Manages the details of items available for sale

public class Product {

    String name;
    int id;
    double price;
    int qty;
    String description;
    boolean status;
    String addedDate;
    
    Product(String name, int id, String addedDate){
        this.name = name;
        this.id = id;
        this.addedDate = addedDate;
    }
}
