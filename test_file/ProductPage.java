package test_file;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductPage extends JFrame {
    private JList<String> productList;
    private JTextArea productDetailsArea;
    private DefaultListModel<String> listModel;
    
    // Main constructor for ProductPage
    public ProductPage() {
        // Frame settings
        setTitle("Product Page");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // List panel for products
        listModel = new DefaultListModel<>();
        listModel.addElement("Product 1");
        listModel.addElement("Product 2");
        listModel.addElement("Product 3");
        listModel.addElement("Product 4");

        productList = new JList<>(listModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.setFont(new Font("Arial", Font.PLAIN, 18));
        productList.setBackground(Color.WHITE);
        productList.setForeground(new Color(50, 50, 150));

        // Add ListSelectionListener to handle item selection
        productList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Check if the user actually selected an item (not just the initial selection)
                if (!e.getValueIsAdjusting()) {
                    String selectedProduct = productList.getSelectedValue();
                    showProductDetails(selectedProduct);
                }
            }
        });

        // Scroll pane for product list
        JScrollPane listScrollPane = new JScrollPane(productList);
        listScrollPane.setPreferredSize(new Dimension(200, 0));

        // Product details area
        productDetailsArea = new JTextArea();
        productDetailsArea.setEditable(false);
        productDetailsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        productDetailsArea.setBackground(new Color(240, 240, 240));
        productDetailsArea.setForeground(new Color(50, 50, 150));
        productDetailsArea.setText("Select a product to view details.");

        JScrollPane detailsScrollPane = new JScrollPane(productDetailsArea);
        detailsScrollPane.setPreferredSize(new Dimension(400, 0));

        // Add "+" button to open Add Product dialog
        JButton addProductButton = new JButton("+");
        addProductButton.setFont(new Font("Arial", Font.PLAIN, 30));
        addProductButton.setForeground(Color.WHITE);
        addProductButton.setBackground(new Color(50, 150, 50)); // Green button
        addProductButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Add Product dialog
                openAddProductDialog();
            }
        });

        // Adding "+" button to the top of the main panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(addProductButton, BorderLayout.EAST);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(listScrollPane, BorderLayout.WEST);
        mainPanel.add(detailsScrollPane, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
    }

    // Method to show product details when a product is selected
    private void showProductDetails(String productName) {
        String productDetails = "";

        // Dummy product details (can be replaced with real data from a database)
        switch (productName) {
            case "Product 1":
                productDetails = "Product 1 Details:\nPrice: $100\nDescription: This is a great product.";
                break;
            case "Product 2":
                productDetails = "Product 2 Details:\nPrice: $150\nDescription: This is an amazing product.";
                break;
            case "Product 3":
                productDetails = "Product 3 Details:\nPrice: $200\nDescription: This is a premium product.";
                break;
            case "Product 4":
                productDetails = "Product 4 Details:\nPrice: $250\nDescription: This is a luxury product.";
                break;
            default:
                productDetails = "Select a product to view details.";
        }

        productDetailsArea.setText(productDetails); // Update the JTextArea with product details
    }

    // Method to open the Add Product dialog
    private void openAddProductDialog() {
        // Create a new JDialog for adding products
        JDialog addProductDialog = new JDialog(this, "Add Product", true);
        addProductDialog.setSize(400, 300);
        addProductDialog.setLocationRelativeTo(this);
        
        // Set up the form for adding a new product
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Product Name:");
        JTextField nameField = new JTextField(10);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(10);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionField = new JTextArea(3, 10);
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);

        JButton addButton = new JButton("Add Product");
        addButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(50, 150, 50)); // Green button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the product to the list
                String productName = nameField.getText();
                String price = priceField.getText();
                String description = descriptionField.getText();

                if (productName.isEmpty() || price.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(addProductDialog, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String newProduct = productName + " - $" + price;
                    listModel.addElement(newProduct); // Add new product to the list
                    JOptionPane.showMessageDialog(addProductDialog, "Product added successfully!");

                    // Close the dialog after adding the product
                    addProductDialog.dispose();
                }
            }
        });

        dialogPanel.add(nameLabel);
        dialogPanel.add(nameField);
        dialogPanel.add(priceLabel);
        dialogPanel.add(priceField);
        dialogPanel.add(descriptionLabel);
        dialogPanel.add(new JScrollPane(descriptionField));
        dialogPanel.add(addButton);

        addProductDialog.add(dialogPanel);

        // Show the dialog
        addProductDialog.setVisible(true);
    }

    public static void main(String[] args) {
        new ProductPage();
    }
}
