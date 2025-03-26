package Entity.InterfaceGui;

import java.awt.*;
import javax.swing.*;

public class MainPage {
    private JButton manageProductButton;
    private JButton manageEmployeeButton;
    private JButton manageMembershipButton;
    private JButton addCartButton;
    private JButton backButton; // Back button
    private String userRole;

    public MainPage(String userRole) {
        this.userRole = userRole;

        JFrame frame = new JFrame("Main Page");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Panel to hold the buttons
        JPanel panel = new JPanel(new BorderLayout());

        // Center panel with grid layout
        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10)); // Increased grid rows to 5 to accommodate the back button
        centerPanel.setBackground(new Color(240, 240, 240));

        // Button to manage products
        manageProductButton = new JButton("Manage Product");
        manageProductButton.setFont(new Font("Arial", Font.PLAIN, 14));
        manageProductButton.addActionListener(e -> {
            frame.dispose();
            new ProductMain();
        });

        // Button to manage employees, only enabled for admin
        manageEmployeeButton = new JButton("Manage Employee");
        manageEmployeeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        manageEmployeeButton.setEnabled(userRole.equals("admin")); // Enable only for admin
        manageEmployeeButton.addActionListener(e -> {
            frame.dispose();
            new ManageEmployeeGUI(); // Open Manage Employee GUI
        });

        // Button to manage memberships
        manageMembershipButton = new JButton("Manage Membership");
        manageMembershipButton.setFont(new Font("Arial", Font.PLAIN, 14));
        manageMembershipButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Managing Memberships..."));

        // Button to make a sale
        addCartButton = new JButton("Make Sale");
        addCartButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addCartButton.addActionListener(e -> {
            frame.dispose();
            new ShopManagementGUI().createAndShowGUI(); // Open Manage Employee GUI
        });

        // Back Button to return to the previous screen
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            frame.dispose();
            new LoginPage(); // Replace PreviousScreen with the class you want to go back to
        });

        // Adding buttons to the center panel
        centerPanel.add(backButton); // Add back button at the top
        centerPanel.add(manageProductButton);
        centerPanel.add(manageEmployeeButton);
        centerPanel.add(manageMembershipButton);
        centerPanel.add(addCartButton);

        // Adding the center panel to the frame
        panel.add(centerPanel, BorderLayout.CENTER);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainPage("admin"); // Test with "admin" or "user"
    }
}
