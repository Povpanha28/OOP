package Entity.InterfaceGui;

import Database.MySQLConnection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class ShopManagementGUI {

    private static JPanel centerPanel, rightPanel;
    private static JFrame frame;
    private static Map<String, CartItem> cart;
    private static JButton checkoutButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShopManagementGUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
        frame = new JFrame("Shop Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        cart = new HashMap<>();

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));
        JLabel titleLabel = new JLabel("Shop Management");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(titleLabel);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(4, 1, 5, 5)); // Adjusted grid layout to 4 rows
        leftPanel.setPreferredSize(new Dimension(150, frame.getHeight()));
        String[] categories = {"All Items", "Shirt", "Pant", "Shoes"}; // Removed "Latest" category

        for (String category : categories) {
            JButton button = new JButton(category);
            button.addActionListener(new CategoryButtonListener(category));
            leftPanel.add(button);
        }
        frame.add(leftPanel, BorderLayout.WEST);

        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200, frame.getHeight()));
        frame.add(rightPanel, BorderLayout.EAST);

        centerPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Initially load all products
        loadProductsFromDatabase("All Items");

        frame.add(centerPanel, BorderLayout.CENTER);

        checkoutButton = new JButton("Checkout");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setBackground(Color.BLUE);
        checkoutButton.setPreferredSize(new Dimension(180, 40));
        checkoutButton.addActionListener(e -> openPaymentGUI());
        checkoutButton.setVisible(true);

        rightPanel.add(checkoutButton);

        frame.setVisible(true);
    }

    private static void loadProductsFromDatabase(String category) {
        // Clear the center panel before adding new products
        centerPanel.removeAll();

        // Query to fetch product data based on category
        String query = category.equals("All Items") ? "SELECT * FROM product" : "SELECT * FROM product WHERE category = ?";
        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(query)) {
            if (!category.equals("All Items")) {
                pstmt.setString(1, category);
            }
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String productName = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String size = resultSet.getString("size");
                String color = resultSet.getString("color");
                String materialOrBrand = resultSet.getString("material_or_brand");
                int qty = resultSet.getInt("qty");

                JPanel productPanel = new JPanel(new BorderLayout());
                productPanel.setBackground(Color.LIGHT_GRAY);
                productPanel.setPreferredSize(new Dimension(150, 100));

                JLabel productLabel = new JLabel(productName + " ($" + price + ")", SwingConstants.CENTER);

                JButton addButton = new JButton("+");
                addButton.setFont(new Font("Arial", Font.BOLD, 12));
                addButton.setForeground(Color.WHITE);
                addButton.setBackground(Color.GREEN);
                addButton.setPreferredSize(new Dimension(30, 30));
                addButton.addActionListener(e -> addToCart(productName, price));

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(addButton);

                productPanel.add(productLabel, BorderLayout.CENTER);
                productPanel.add(buttonPanel, BorderLayout.NORTH);

                // Store additional product details in the panel
                productPanel.putClientProperty("description", description);
                productPanel.putClientProperty("size", size);
                productPanel.putClientProperty("color", color);
                productPanel.putClientProperty("material_or_brand", materialOrBrand);
                productPanel.putClientProperty("qty", qty);

                centerPanel.add(productPanel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Revalidate and repaint the center panel to reflect changes
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    static class CategoryButtonListener implements ActionListener {
        private String category;

        public CategoryButtonListener(String category) {
            this.category = category;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            loadProductsFromDatabase(category); // Load products of selected category
        }
    }

    private static void openPaymentGUI() {
        new PaymentGUI(cart); // Assuming PaymentGUI has a constructor that accepts cart data
        frame.dispose(); // Close the current shop management window
    }

    private static void addToCart(String product, double price) {
        cart.put(product, new CartItem(product, price, cart.getOrDefault(product, new CartItem(product, price, 0)).getQuantity() + 1));
        updateCartDisplay();
    }

    private static void updateCartDisplay() {
        rightPanel.removeAll();
        if (cart.isEmpty()) {
            rightPanel.add(new JLabel("Cart is empty", SwingConstants.CENTER));
        } else {
            for (CartItem item : cart.values()) {
                JLabel itemLabel = new JLabel(item.getName() + " (x" + item.getQuantity() + ") - $" + item.getTotalPrice());
                rightPanel.add(itemLabel);
            }
        }
        rightPanel.add(checkoutButton, BorderLayout.SOUTH);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    static class CartItem {
        private String name;
        private double price;
        private int quantity;

        public CartItem(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public double getTotalPrice() { return price * quantity; }
    }
}
