package Entity.InterfaceGui;

import Database.MySQLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductDeleteGUI extends JFrame {

    private JFrame frame;
    private JComboBox<String> productListComboBox;
    private Connection connection;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductDeleteGUI::new);
    }

    public ProductDeleteGUI() {
        frame = new JFrame("Delete Product");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        connection = MySQLConnection.getConnection();

        JPanel buttonPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(this::handleDeleteProduct);
        buttonPanel.add(deleteButton);

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
}
