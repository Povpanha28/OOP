package Entity.InterfaceGui;

import Database.MySQLConnection;
import Entity.Product.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class ProductInputGUI {

    private JFrame frame;
    private JTextField nameField, priceField, qtyField, sizeField, colorField, materialBrandField;
    private JTextArea descriptionArea;
    private JComboBox<String> typeComboBox;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductInputGUI::new);
    }

    public ProductInputGUI() {
        frame = new JFrame("Product Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Creating the UI components
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Adding padding between components

        // Creating the form
        createForm(formPanel, gbc);

        // Panel for the button (always at the bottom)
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Add Product");
        submitButton.addActionListener(this::handleAddProduct);
        buttonPanel.add(submitButton);

        // Adding the form and button panel to the frame
        frame.add(formPanel, BorderLayout.CENTER);  // The form takes up most of the space
        frame.add(buttonPanel, BorderLayout.SOUTH);  // The button stays at the bottom

        frame.setVisible(true);
    }

    private void createForm(JPanel formPanel, GridBagConstraints gbc) {
        // Product Name
        formPanel.add(new JLabel("Product Name:"), setGridBagConstraints(gbc, 0, 0));
        nameField = new JTextField(20);
        formPanel.add(nameField, setGridBagConstraints(gbc, 1, 0));

        // Product Price
        formPanel.add(new JLabel("Product Price:"), setGridBagConstraints(gbc, 0, 1));
        priceField = new JTextField(20);
        formPanel.add(priceField, setGridBagConstraints(gbc, 1, 1));

        // Product Quantity
        formPanel.add(new JLabel("Product Quantity:"), setGridBagConstraints(gbc, 0, 2));
        qtyField = new JTextField(20);
        formPanel.add(qtyField, setGridBagConstraints(gbc, 1, 2));

        // Product Description
        formPanel.add(new JLabel("Product Description:"), setGridBagConstraints(gbc, 0, 3));
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formPanel.add(scrollPane, setGridBagConstraints(gbc, 1, 3));

        // Product Type
        formPanel.add(new JLabel("Product Type:"), setGridBagConstraints(gbc, 0, 4));
        typeComboBox = new JComboBox<>(new String[]{"Shirt", "Shoes", "Pant"});
        formPanel.add(typeComboBox, setGridBagConstraints(gbc, 1, 4));

        // Product Specific Inputs
        formPanel.add(new JLabel("Size:"), setGridBagConstraints(gbc, 0, 5));
        sizeField = new JTextField(20);
        formPanel.add(sizeField, setGridBagConstraints(gbc, 1, 5));

        formPanel.add(new JLabel("Color:"), setGridBagConstraints(gbc, 0, 6));
        colorField = new JTextField(20);
        formPanel.add(colorField, setGridBagConstraints(gbc, 1, 6));

        formPanel.add(new JLabel("Material/Brand:"), setGridBagConstraints(gbc, 0, 7));
        materialBrandField = new JTextField(20);
        formPanel.add(materialBrandField, setGridBagConstraints(gbc, 1, 7));

        // ActionListener for the combo box to update fields
        typeComboBox.addActionListener(e -> updateProductSpecificFields());
    }

    private GridBagConstraints setGridBagConstraints(GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
    }

    private void updateProductSpecificFields() {
        String selectedType = (String) typeComboBox.getSelectedItem();
        sizeField.setText("");
        colorField.setText("");
        materialBrandField.setText("");  // Ensure we reset this field

        if ("Shirt".equals(selectedType)) {
            materialBrandField.setText("Material");
        } else if ("Shoes".equals(selectedType)) {
            materialBrandField.setText("Brand");
        } else if ("Pant".equals(selectedType)) {
            materialBrandField.setText("Material");
        }
    }

    private void handleAddProduct(ActionEvent event) {
        try {
            // Get the inputs from the user
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int qty = Integer.parseInt(qtyField.getText());
            String description = descriptionArea.getText();
            String type = (String) typeComboBox.getSelectedItem();
            String size = sizeField.getText();
            String color = colorField.getText();
            String materialOrBrand = materialBrandField.getText();

            // Create a product object based on the input
            Product product = createProduct(type, name, price, qty, description, size, color, materialOrBrand);

            // Add the product to the database
            addProductToDatabase(product);

            // Show a success message
            JOptionPane.showMessageDialog(frame, "Product has been successfully added!");

            // Reset the fields after adding the product
            resetFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid input! Please check your fields.");
        }
    }

    private Product createProduct(String type, String name, double price, int qty, String description,
                                  String size, String color, String materialOrBrand) {
        switch (type) {
            case "Shirt":
                return new Shirt(name, price, qty, description, size, color, materialOrBrand);
            case "Shoes":
                return new Shoes(name, price, qty, description, size, color, materialOrBrand);
            case "Pant":
                return new Pant(name, price, qty, description, size, color, materialOrBrand);
            default:
                return null;
        }
    }

    private void addProductToDatabase(Product product) {
        String query = "INSERT INTO product (name, price, qty, description, size, color, material_or_brand) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setDouble(2, product.getProductPrice());
            pstmt.setInt(3, product.getProductQty());
            pstmt.setString(4, product.getProductDescription());
            pstmt.setString(5, product instanceof Shirt ? ((Shirt) product).getSize()
                    : product instanceof Shoes ? ((Shoes) product).getSize()
                    : ((Pant) product).getSize());
            pstmt.setString(6, product instanceof Shirt ? ((Shirt) product).getColor()
                    : product instanceof Shoes ? ((Shoes) product).getColor()
                    : ((Pant) product).getColor());
            pstmt.setString(7, product instanceof Shirt ? ((Shirt) product).getMaterial()
                    : product instanceof Shoes ? ((Shoes) product).getBrand()
                    : ((Pant) product).getMaterial());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resetFields() {
        // Clear all text fields and text area
        nameField.setText("");
        priceField.setText("");
        qtyField.setText("");
        descriptionArea.setText("");
        sizeField.setText("");
        colorField.setText("");
        materialBrandField.setText("");  // Ensure we clear this field

        // Reset the combo box to the first selection
        typeComboBox.setSelectedIndex(0);  // Reset to the first item in the list (e.g., "Shirt")

        // After resetting, we also update the fields based on the default combo box selection
        updateProductSpecificFields();  // This ensures "Material" doesn't remain in the text field for "Material/Brand"
    }
}
