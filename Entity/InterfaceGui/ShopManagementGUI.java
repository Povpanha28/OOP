package Entity.InterfaceGui;

import Database.MySQLConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ShopManagementGUI {
    private JPanel centerPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShopManagementGUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Shop Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

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
        JPanel leftPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        leftPanel.setPreferredSize(new Dimension(150, frame.getHeight()));
        String[] categories = {"All Items", "Shirt", "Pant", "Shoes", "Latest"};
        for (String category : categories) {
            JButton button = new JButton(category);
            button.addActionListener(e -> loadDataFromDatabase(category));
            leftPanel.add(button);
        }
        frame.add(leftPanel, BorderLayout.WEST);

        // Center Panel (Product Grid)
        centerPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        frame.add(centerPanel, BorderLayout.CENTER);

        // Load data from database
        loadDataFromDatabase("All Items");

        frame.setVisible(true);
    }

    // Load data from the database based on the selected category
    private void loadDataFromDatabase(String category) {
        // Clear previous content
        centerPanel.removeAll();

        String query = "SELECT name, price, qty, description, product_code, size, color, material_or_brand FROM product";

        // Modify query based on selected category
        if (!category.equals("All Items")) {
            query += " WHERE category = '" + category + "'";  // Assuming 'category' column exists
        }

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("qty");
                String description = rs.getString("description");
                String productCode = rs.getString("product_code");
                String size = rs.getString("size");
                String color = rs.getString("color");
                String materialOrBrand = rs.getString("material_or_brand");

                // Panel for each product
                JPanel productPanel = new JPanel();
                productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
                productPanel.setBackground(Color.LIGHT_GRAY);
                productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                productPanel.setPreferredSize(new Dimension(350, 300));

                // Product name label
                JLabel nameLabel = new JLabel("<html><b>" + name + "</b></html>");
                nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(nameLabel);

                // Product details (price, qty, etc.)
                JLabel detailsLabel = new JLabel("<html>Price: $" + price +
                        "<br>Qty: " + qty + 
                        "<br>Description: " + description +
                        "<br>Code: " + productCode + 
                        "<br>Size: " + size + 
                        "<br>Color: " + color + 
                        "<br>Brand/Material: " + materialOrBrand + "</html>", SwingConstants.CENTER);
                detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(detailsLabel);

                // Add product panel to center panel
                centerPanel.add(productPanel);
            }

            // Refresh the panel after adding components
            centerPanel.revalidate();
            centerPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from database", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
