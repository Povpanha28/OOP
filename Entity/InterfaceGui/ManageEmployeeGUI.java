package Entity.InterfaceGui;

import javax.swing.*;
import java.awt.*;

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

        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.addActionListener(e -> new EmployeeForm()); // Opens Employee Form

        JButton editEmployeeButton = new JButton("Edit Employee");
        editEmployeeButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Edit Employee Feature Coming Soon!"));

        JButton deleteEmployeeButton = new JButton("Delete Employee");
        deleteEmployeeButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Delete Employee Feature Coming Soon!"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(editEmployeeButton);
        buttonPanel.add(deleteEmployeeButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ManageEmployeeGUI();
    }
}