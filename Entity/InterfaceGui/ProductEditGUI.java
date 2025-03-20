package Entity.InterfaceGui;

import Database.MySQLConnection;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ProductEditGUI extends JFrame{

    private JFrame frame;
    private JTextField nameField, priceField, qtyField, sizeField, colorField, materialBrandField;
    private JTextArea descriptionArea;
    private JComboBox<String> typeComboBox;
    private JComboBox<String> productListComboBox;
    private Connection connection;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductEditGUI::new);
    }

    public ProductEditGUI() {
        frame = new JFrame("Edit Product");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        connection = MySQLConnection.getConnection();

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        createForm(formPanel, gbc);

        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("Update Product");
        updateButton.addActionListener(this::handleUpdateProduct);
        buttonPanel.add(updateButton);

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        productListComboBox = new JComboBox<>();
        frame.add(productListComboBox, BorderLayout.NORTH);
        
        loadProductList();

        productListComboBox.addActionListener(this::onProductSelect);

        frame.setVisible(true);
    }

    private void createForm(JPanel formPanel, GridBagConstraints gbc) {
        formPanel.add(new JLabel("Product Name:"), setGridBagConstraints(gbc, 0, 0));
        nameField = new JTextField(20);
        formPanel.add(nameField, setGridBagConstraints(gbc, 1, 0));

        formPanel.add(new JLabel("Product Price:"), setGridBagConstraints(gbc, 0, 1));
        priceField = new JTextField(20);
        formPanel.add(priceField, setGridBagConstraints(gbc, 1, 1));

        formPanel.add(new JLabel("Product Quantity:"), setGridBagConstraints(gbc, 0, 2));
        qtyField = new JTextField(20);
        formPanel.add(qtyField, setGridBagConstraints(gbc, 1, 2));

        formPanel.add(new JLabel("Product Description:"), setGridBagConstraints(gbc, 0, 3));
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formPanel.add(scrollPane, setGridBagConstraints(gbc, 1, 3));

        formPanel.add(new JLabel("Product Type:"), setGridBagConstraints(gbc, 0, 4));
        typeComboBox = new JComboBox<>(new String[]{"Shirt", "Shoes", "Pant"});
        formPanel.add(typeComboBox, setGridBagConstraints(gbc, 1, 4));

        formPanel.add(new JLabel("Size:"), setGridBagConstraints(gbc, 0, 5));
        sizeField = new JTextField(20);
        formPanel.add(sizeField, setGridBagConstraints(gbc, 1, 5));

        formPanel.add(new JLabel("Color:"), setGridBagConstraints(gbc, 0, 6));
        colorField = new JTextField(20);
        formPanel.add(colorField, setGridBagConstraints(gbc, 1, 6));

        formPanel.add(new JLabel("Material/Brand:"), setGridBagConstraints(gbc, 0, 7));
        materialBrandField = new JTextField(20);
        formPanel.add(materialBrandField, setGridBagConstraints(gbc, 1, 7));
    }

    private GridBagConstraints setGridBagConstraints(GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
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

    private void onProductSelect(ActionEvent event) {
        String selectedProductName = (String) productListComboBox.getSelectedItem();
        if (selectedProductName != null) {
            loadProductDetails(selectedProductName);
        }
    }

    private void loadProductDetails(String productName) {
        try {
            String query = "SELECT * FROM product WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                priceField.setText(String.valueOf(rs.getDouble("price")));
                qtyField.setText(String.valueOf(rs.getInt("qty")));
                descriptionArea.setText(rs.getString("description"));
                typeComboBox.setSelectedItem(rs.getString("category"));
                sizeField.setText(rs.getString("size"));
                colorField.setText(rs.getString("color"));
                materialBrandField.setText(rs.getString("material_or_brand"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdateProduct(ActionEvent event) {
        try {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int qty = Integer.parseInt(qtyField.getText());
            String description = descriptionArea.getText();
            String type = (String) typeComboBox.getSelectedItem();
            String size = sizeField.getText();
            String color = colorField.getText();
            String materialOrBrand = materialBrandField.getText();

            String query = "UPDATE product SET name = ?, price = ?, qty = ?, description = ?, size = ?, color = ?, material_or_brand = ?, category = ? WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, qty);
            pstmt.setString(4, description);
            pstmt.setString(5, size);
            pstmt.setString(6, color);
            pstmt.setString(7, materialOrBrand);
            pstmt.setString(8, type);
            pstmt.setString(9, (String) productListComboBox.getSelectedItem());

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Product has been successfully updated!");
            loadProductList(); // Refresh product list
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error updating product.");
        }
    }
}
