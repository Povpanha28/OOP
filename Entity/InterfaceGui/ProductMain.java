package Entity.InterfaceGui;

import java.awt.*;
import javax.swing.*;

public class ProductMain {
    private JButton addPButton;
    private JButton editPButton;
    private JButton deletePButton;
    private JButton backButton;

    public ProductMain() {
        JFrame frame = new JFrame("Manage Product");  // Create a JFrame object manually
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));  // Added one more row for back button
        centerPanel.setBackground(new Color(240, 240, 240));

        // Add Product button
        addPButton = new JButton("Add Product");
        addPButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addPButton.addActionListener(e -> {
            frame.dispose();
            new ProductInputGUI();
        }); // Opens Product Input Form

        // Edit Product button
        editPButton = new JButton("Edit Product");
        editPButton.setFont(new Font("Arial", Font.PLAIN, 14));
        editPButton.addActionListener(e -> {
            frame.dispose();
            new ProductEditGUI();
        }); // Opens Product Edit Form

        // Delete Product button
        deletePButton = new JButton("Delete Product");
        deletePButton.setFont(new Font("Arial", Font.PLAIN, 14));
        deletePButton.addActionListener(e -> {
            frame.dispose();
            new ProductDeleteGUI();
        }); // Opens Product Delete Form

        // Back Button to return to the previous screen
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            frame.dispose(); // Close current window
            new MainPage("admin"); // Go back to the main page (replace with the desired previous screen)
        });

        // Add buttons to the center panel
        centerPanel.add(backButton);  // Back button first
        centerPanel.add(addPButton);
        centerPanel.add(editPButton);
        centerPanel.add(deletePButton);

        frame.add(centerPanel, BorderLayout.CENTER);  // Add the center panel to the frame
        frame.setVisible(true);  // Make the frame visible
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductMain::new);
    }
}
