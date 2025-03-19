package Entity.InterfaceGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProductMain extends JFrame {
    private JButton addPButton;
    private JButton editPButton;;
    private JButton deletePButton;

    ProductMain() {
        setTitle("Manage Product");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBackground(new Color(240, 240, 240));

        addPButton = new JButton("Add Product");
        addPButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addPButton.addActionListener(e -> {
            new ProductInputGUI(); // Open Manage Product GUI
            dispose();
        });

        editPButton = new JButton("Edit Product");
        editPButton.setFont(new Font("Arial", Font.PLAIN, 14));
        editPButton.addActionListener(e -> {
            // new ProductEditGUI(); // Open Manage Product GUI
            dispose();
        });

        panel.add(centerPanel, BorderLayout.CENTER);
        add(panel);
        centerPanel.add(addPButton);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ProductMain(); // Test with "admin" or "user"
    }
}
