package Entity.InterfaceGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    private JButton manageProductButton;
    private JButton manageEmployeeButton;
    private JButton manageMembershipButton;
    private String userRole; // Will hold the role of the user ("admin" or "user")

    public MainPage(String userRole) {
        this.userRole = userRole; // Store the user role

        // Frame settings
        setTitle("Main Page");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with BorderLayout to easily center components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Center panel for the buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 10, 10));  // Three buttons in a vertical layout
        centerPanel.setBackground(new Color(240, 240, 240));

        // Create and style the buttons
        manageProductButton = new JButton("Manage Product");
        manageProductButton.setPreferredSize(new Dimension(150, 40));
        manageProductButton.setFont(new Font("Arial", Font.PLAIN, 14));
        manageProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the ShopManagementGUI page
                new ShopManagementGUI().createAndShowGUI();
                dispose(); // Close the main page
            }
        });

        manageEmployeeButton = new JButton("Manage Employee");
        manageEmployeeButton.setPreferredSize(new Dimension(150, 40));
        manageEmployeeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        manageEmployeeButton.setEnabled(false);  // Initially disabled for non-admin users
        if (userRole.equals("admin")) {
            manageEmployeeButton.setEnabled(true); // Enable for admins
        }
        manageEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainPage.this, "Managing Employees...");
            }
        });

        manageMembershipButton = new JButton("Manage Membership");
        manageMembershipButton.setPreferredSize(new Dimension(150, 40));
        manageMembershipButton.setFont(new Font("Arial", Font.PLAIN, 14));
        manageMembershipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainPage.this, "Managing Memberships...");
            }
        });

        // Add buttons to center panel
        centerPanel.add(manageProductButton);
        centerPanel.add(manageEmployeeButton);
        centerPanel.add(manageMembershipButton);

        // Add the center panel to the main panel
        panel.add(centerPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainPage("user");  // Default to "user" for testing
    }
}
