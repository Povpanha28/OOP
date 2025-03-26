package Entity.InterfaceGui;

import java.awt.*;
import javax.swing.*;

public class ManageEmployeeGUI extends JFrame {
    public ManageEmployeeGUI() {
        setTitle("Manage Employees");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Manage Employees", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));

        // Buttons for managing employees
        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.addActionListener(e -> new EmployeeForm()); // Opens Employee Form

        JButton editEmployeeButton = new JButton("Edit Employee");
        editEmployeeButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Edit Employee Feature Coming Soon!"));

        JButton deleteEmployeeButton = new JButton("Delete Employee");
        deleteEmployeeButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Delete Employee Feature Coming Soon!"));

        // Back Button to return to the previous screen
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            dispose(); // Close current window
            new MainPage("admin"); // Go back to the main page (replace with appropriate previous screen)
        });

        // Panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // Added 4 rows to accommodate back button
        buttonPanel.add(backButton); // Add the back button first
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(editEmployeeButton);
        buttonPanel.add(deleteEmployeeButton);

        // Adding components to panel
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Adding panel to the frame
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ManageEmployeeGUI();
    }
}
