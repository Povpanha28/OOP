package System;
import Entity.Supplier;
import java.util.HashMap;
public class Supplier_System {
    public static void main(String[] args) {
        HashMap<Integer, Supplier> supplierMap = new HashMap<Integer, Supplier>();

        Supplier supplier1 = new Supplier("Supplier1", "1234567890", "123 ABC Street", "ABC Company");
        Supplier supplier2 = new Supplier("Supplier2", "0987654321", "456 XYZ Street", "XYZ Company");

        supplierMap.put(supplier1.getSupplierID(), supplier1);
        supplierMap.put(supplier2.getSupplierID(), supplier2);

        System.out.println(supplierMap.get(1).getSupplierName());                
        System.out.println(supplierMap.get(2).getSupplierName());
    }
}
