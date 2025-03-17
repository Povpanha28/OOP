package Entity.InterfaceGui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ShopManagementGUI {

    private static JPanel centerPanel, rightPanel;
    private static JFrame frame;
    private static Map<String, CartItem> cart; // Track product, quantity, and price

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Shop Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        cart = new HashMap<>(); // Initialize cart as a Map to store product names, quantities, and prices

        // Top Panel (Header)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));
        JLabel titleLabel = new JLabel("Shop Management");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(titleLabel);
        frame.add(topPanel, BorderLayout.NORTH);

        // Left Panel (Categories)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 1, 5, 5));
        leftPanel.setPreferredSize(new Dimension(150, frame.getHeight()));
        String[] categories = {"All Items", "Shirt", "Pant", "Shoes", "Latest"};

        for (String category : categories) {
            JButton button = new JButton(category);
            button.addActionListener(new CategoryButtonListener(category));
            leftPanel.add(button);
        }
        frame.add(leftPanel, BorderLayout.WEST);

        // Right Panel (Cart Info)
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200, frame.getHeight()));
        frame.add(rightPanel, BorderLayout.EAST);

        // Center Panel (Product Grid)
        centerPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // Use 0 for dynamic rows and fixed 2 columns
        String[][] productData = {
            {"Shirt", "Product 1", "20.99"},
            {"Pant", "Product 2", "30.50"},
            {"Shoes", "Product 3", "50.00"},
            {"Shirt", "Product 4", "19.99"},
            {"Latest", "Product 5", "25.75"},
            {"Shoes", "Product 6", "40.00"}
        };

        for (String[] data : productData) {
            String category = data[0];
            String productName = data[1];
            double price = Double.parseDouble(data[2]);

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

            productPanel.putClientProperty("category", category);
            centerPanel.add(productPanel);
        }

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static class CategoryButtonListener implements ActionListener {

        private String category;
    
        public CategoryButtonListener(String category) {
            this.category = category;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            centerPanel.removeAll(); // Clear previous products
    
            String[][] productData = {
                {"Shirt", "Product 1", "20.99"},
                {"Pant", "Product 2", "30.50"},
                {"Shoes", "Product 3", "50.00"},
                {"Shirt", "Product 4", "19.99"},
                {"Latest", "Product 5", "25.75"},
                {"Shoes", "Product 6", "40.00"}
            };
    
            for (String[] data : productData) {
                String category = data[0];
                String productName = data[1];
                double price = Double.parseDouble(data[2]);
    
                // Only show items that match the selected category, or all items if "All Items" is clicked
                if (this.category.equals("All Items") || category.equals(this.category)) {
                    JPanel productPanel = new JPanel(new BorderLayout());
                    productPanel.setBackground(Color.LIGHT_GRAY);
                    productPanel.setPreferredSize(new Dimension(150, 100));
    
                    JLabel productLabel = new JLabel(productName + " ($" + price + ")", SwingConstants.CENTER);
    
                    JButton addButton = new JButton("+");
                    addButton.setFont(new Font("Arial", Font.BOLD, 12));
                    addButton.setForeground(Color.WHITE);
                    addButton.setBackground(Color.GREEN);
                    addButton.setPreferredSize(new Dimension(30, 30));
                    addButton.addActionListener(e1 -> addToCart(productName, price));
    
                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    buttonPanel.add(addButton);
    
                    productPanel.add(productLabel, BorderLayout.CENTER);
                    productPanel.add(buttonPanel, BorderLayout.NORTH);
    
                    centerPanel.add(productPanel); // Add product panel to the center
                }
            }
    
            centerPanel.revalidate(); // Revalidate the layout after updating
            centerPanel.repaint(); // Repaint the layout to reflect changes
        }
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
            double totalPrice = 0;
            for (CartItem item : cart.values()) {
                JPanel itemPanel = new JPanel(new BorderLayout());
                itemPanel.setBackground(Color.LIGHT_GRAY);
                itemPanel.setPreferredSize(new Dimension(180, 60)); // Fixed size for each cart item

                JLabel itemLabel = new JLabel(item.getName() + " (x" + item.getQuantity() + ") - $" + item.getTotalPrice());

                JButton addButton = new JButton("+");
                addButton.setFont(new Font("Arial", Font.BOLD, 12));
                addButton.setForeground(Color.WHITE);
                addButton.setBackground(Color.GREEN);
                addButton.setPreferredSize(new Dimension(30, 30));
                addButton.addActionListener(e -> increaseQuantity(item.getName()));

                JButton removeButton = new JButton("-");
                removeButton.setFont(new Font("Arial", Font.BOLD, 12));
                removeButton.setForeground(Color.WHITE);
                removeButton.setBackground(Color.RED);
                removeButton.setPreferredSize(new Dimension(30, 30));
                removeButton.addActionListener(e -> removeFromCart(item.getName()));

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(addButton);
                buttonPanel.add(removeButton);

                itemPanel.add(itemLabel, BorderLayout.CENTER);
                itemPanel.add(buttonPanel, BorderLayout.EAST);

                rightPanel.add(itemPanel);
                totalPrice += item.getTotalPrice();
            }

            // Display total price
            JLabel totalLabel = new JLabel("Total: $" + totalPrice);
            rightPanel.add(totalLabel, BorderLayout.SOUTH);
        }

        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private static void removeFromCart(String product) {
        CartItem item = cart.get(product);
        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
        } else {
            cart.remove(product);
        }
        updateCartDisplay();
    }

    private static void increaseQuantity(String product) {
        CartItem item = cart.get(product);
        item.setQuantity(item.getQuantity() + 1);
        updateCartDisplay();
    }

    // CartItem class to store product, price, and quantity
    static class CartItem {
        private String name;
        private double price;
        private int quantity;

        public CartItem(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getTotalPrice() {
            return price * quantity;
        }
    }
}
