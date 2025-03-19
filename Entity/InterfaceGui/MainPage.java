package Entity.InterfaceGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    private JButton manageProductButton;
    private JButton manageEmployeeButton;
    private JButton manageMembershipButton;
    private JButton addCartButton;
    private String userRole;

    public MainPage(String userRole) {
        this.userRole = userRole;

        setTitle("Main Page");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        centerPanel.setBackground(new Color(240, 240, 240));

        manageProductButton = new JButton("Manage Product");
        manageProductButton.setFont(new Font("Arial", Font.PLAIN, 14));
        manageProductButton.addActionListener(e -> {
            
            dispose();
        });

        manageEmployeeButton = new JButton("Manage Employee");
        manageEmployeeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        manageEmployeeButton.setEnabled(userRole.equals("admin")); // Enable only for admin
        manageEmployeeButton.addActionListener(e -> {
            new ManageEmployeeGUI(); // Open Manage Employee GUI
            dispose();
        });

        manageMembershipButton = new JButton("Manage Membership");
        manageMembershipButton.setFont(new Font("Arial", Font.PLAIN, 14));
        manageMembershipButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Managing Memberships..."));

        addCartButton = new JButton("Make Sale");
        addCartButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addCartButton.addActionListener(e -> {
            new ShopManagementGUI().createAndShowGUI(); // Open Manage Employee GUI
            dispose();
        });
        centerPanel.add(manageProductButton);
        centerPanel.add(manageEmployeeButton);
        centerPanel.add(manageMembershipButton);
        centerPanel.add(addCartButton);

        panel.add(centerPanel, BorderLayout.CENTER);
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainPage("admin"); // Test with "admin" or "user"
    }
}
