package Entity.InterfaceGui;

import java.awt.*;
import javax.swing.*;

public class ProductMain extends JFrame {
    private JButton addPButton;
    private JButton editPButton;
    private JButton deletePButton;

    public ProductMain() {
        setTitle("Manage Product");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBackground(new Color(240, 240, 240));

        addPButton = new JButton("Add Product");
        addPButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addPButton.addActionListener(e -> openWindow(new ProductInputGUI()));

        editPButton = new JButton("Edit Product");
        editPButton.setFont(new Font("Arial", Font.PLAIN, 14));
        editPButton.addActionListener(e -> openWindow(new ProductEditGUI()));

        deletePButton = new JButton("Delete Product");
        deletePButton.setFont(new Font("Arial", Font.PLAIN, 14));
        deletePButton.addActionListener(e -> openWindow(new ProductDeleteGUI()));

        centerPanel.add(addPButton);
        centerPanel.add(editPButton);
        centerPanel.add(deletePButton);

        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void openWindow(JFrame frame) {
        frame.setVisible(true);
        dispose(); // Closes the current window when a new one opens
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductMain::new);
    }
}
