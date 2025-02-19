package Entity.Product;

public class NoneDiscountProduct extends Product {

    // Constructor
    public NoneDiscountProduct(String productName, double productPrice, int productQty, 
                               String addedDate, String expiredDate, String supplierID) {
        super(productName, productPrice, productQty, addedDate, expiredDate, supplierID);
    }

    // Implement the abstract method from the Product class
    @Override
    public String getProductType() {
        return "NoneDiscountProduct";
    }

    @Override
    public String toString() {
        return super.toString() + ", Product Type= NoneDiscountProduct";
    }
}
