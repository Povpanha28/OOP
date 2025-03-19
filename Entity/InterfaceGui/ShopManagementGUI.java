package Entity.InterfaceGui;

import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ShopManagementGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShopManagementGUI().createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Shop Management");
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

        // Left Panel (Categories)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 1, 5, 5));
        leftPanel.setPreferredSize(new Dimension(150, frame.getHeight()));
        String[] categories = {"All Items", "Shirt", "Pant", "Shoes", "Latest"};

        for (String category : categories) {
            JButton button = new JButton(category);
            leftPanel.add(button);
        }
        frame.add(leftPanel, BorderLayout.WEST);

        // Right Panel (Cart Button)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setPreferredSize(new Dimension(150, frame.getHeight()));
        JButton cartButton = new JButton("View Cart");
        rightPanel.add(cartButton);
        frame.add(rightPanel, BorderLayout.EAST);

        // Center Panel (Product Grid)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 2, 10, 10));
        for (int i = 1; i <= 6; i++) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BorderLayout());
            productPanel.setBackground(Color.LIGHT_GRAY);
            productPanel.setPreferredSize(new Dimension(150, 100));
            JLabel productLabel = new JLabel("Product " + i, SwingConstants.CENTER);
            productPanel.add(productLabel, BorderLayout.CENTER);
            centerPanel.add(productPanel);
        }
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}