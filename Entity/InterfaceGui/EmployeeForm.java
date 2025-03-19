package Entity.InterfaceGui;

import javax.swing.*;
import Database.MySQLConnection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeForm extends JFrame implements ActionListener {
    private JTextField usernameField, nameField, roleField, hoursField, salaryField, contactField;
    private JPasswordField passwordField;
    private JButton saveButton;

    public EmployeeForm() {
        setTitle("Employee Input Form");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Fields
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Employee Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Role:"));
        roleField = new JTextField();
        panel.add(roleField);

        panel.add(new JLabel("Work Hours:"));
        hoursField = new JTextField();
        panel.add(hoursField);

        panel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        panel.add(salaryField);

        panel.add(new JLabel("Contact:"));
        contactField = new JTextField();
        panel.add(contactField);

        // Save Button
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        panel.add(saveButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String name = nameField.getText();
        String role = roleField.getText();
        String hoursText = hoursField.getText();
        String salaryText = salaryField.getText();
        String contact = contactField.getText();

        // Input validation
        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || role.isEmpty() ||
            hoursText.isEmpty() || salaryText.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double workHours = Double.parseDouble(hoursText);
            double salary = Double.parseDouble(salaryText);

            if (workHours < 0 || salary < 0) {
                JOptionPane.showMessageDialog(this, "Work hours and salary must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            saveEmployeeToDatabase(username, password, name, role, workHours, salary, contact);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveEmployeeToDatabase(String username, String password, String name, String role, double workHours, double salary, String contact) {
        String query = "INSERT INTO Employee (username, password, employee_name, employee_role, work_hours, employee_salary, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, role);
            stmt.setDouble(5, workHours);
            stmt.setDouble(6, salary);
            stmt.setString(7, contact);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        nameField.setText("");
        roleField.setText("");
        hoursField.setText("");
        salaryField.setText("");
        contactField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeForm::new);
    }
}
