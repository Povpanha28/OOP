package System;
import Entity.User.Supplier;
public class Supplier_System {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        
        Supplier supplier1 = new Supplier("Supplier1", "1234567890", "123 ABC Street", "ABC Company");
        Supplier supplier2 = new Supplier("Supplier2", "0987654321", "456 XYZ Street", "XYZ Company");
        System.out.println(Supplier.getAllPayments());
        System.out.println(Supplier.getPaymentByID(1));
        System.out.println(Supplier.getPaymentByID(2));
        Supplier.removePaymentByID(1, "password");
        System.out.println(Supplier.getAllPayments());
    }
}
