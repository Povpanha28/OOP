package Entity.User;

public class TestUser {
    public static void main(String[] args) {
        User admin = new Admin("Admin", "11112222", "Admin123@gmail.com");
        admin.register();
        User employee = new Employee("Jack", "00001111", "Jack123@gmail.com", "Jack", "Cashier", 40, 250, "Cash", "012345345");
        employee.register();
        User customer = new Customer("Lisa", "12341234", "Lisa321@gmail.com", "Cash", 2);
        customer.register();
        User supplier = new Supplier("Horn", "13131313", "Horn22@gmail.com", "Alibaba", "123St123", "017828282");
        supplier.register();
        System.out.println(User.getUserDatabase());
    }
}
