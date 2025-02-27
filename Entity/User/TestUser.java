package Entity.User;

public class TestUser {
    public static void main(String[] args) {
        User supplier = new Supplier("Jack", "123", "Jack23@gmail.com", "Tesla", "123 St Amera", "012121212");
        Supplier castedSupplier = (Supplier) supplier;
        castedSupplier.register();
        Employee employee = new Employee("Lis", "123", "Lis23@gmail.com", "Lis", "Cashier", 45, 250, "Cash");
        employee.register();
        // Customer customer = new Customer("Ngorl Ngorl", "321",
        // "NgorlNgorl@yahoo.com", "CreditCard", "2");
        // customer.register();
        System.out.println(User.getUserDatabase());
    }
}
