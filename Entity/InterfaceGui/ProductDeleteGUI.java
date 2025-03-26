package Entity.InterfaceGui;

import Database.MySQLConnection;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ProductDeleteGUI {

    private JFrame frame;
    private JComboBox<String> productListComboBox;
    private Connection connection;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductDeleteGUI::new);
    }

    public ProductDeleteGUI() {
        frame = new JFrame("Delete Product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        connection = MySQLConnection.getConnection();

        JPanel buttonPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(this::handleDeleteProduct);
        buttonPanel.add(deleteButton);

        // Create and add the Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this::handleBackButton);
        buttonPanel.add(backButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        productListComboBox = new JComboBox<>();
        frame.add(productListComboBox, BorderLayout.NORTH);

        loadProductList();

        frame.setVisible(true);
    }

    private void loadProductList() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM product");
            while (rs.next()) {
                productListComboBox.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteProduct(ActionEvent event) {
        String selectedProductName = (String) productListComboBox.getSelectedItem();
        if (selectedProductName != null) {
            try {
                String query = "DELETE FROM product WHERE name = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, selectedProductName);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Product has been successfully deleted!");
                loadProductList(); // Refresh product list
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error deleting product.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No product selected!");
        }
    }

    // Handle the Back button action
    private void handleBackButton(ActionEvent event) {
        // Close the current frame
        frame.dispose();
        new ProductMain();

        // You can add the logic to navigate to the previous screen
        // For example, you can open a previous screen (like the main menu or product list) here.
        // For simplicity, I'll just print a message.
        System.out.println("Back to previous screen...");
    }
}
