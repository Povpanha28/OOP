package Entity.InterfaceGui;

import Database.MySQLConnection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;

    public LoginPage() {
        // Frame settings
        setTitle("Login Page");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10)); // Add spacing
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Title
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel userLabel = new JLabel("Username:");
        userField = new JTextField(10);
        JLabel passLabel = new JLabel("Password:");
        passField = new JPasswordField(10);

        inputPanel.add(userLabel);
        inputPanel.add(userField);
        inputPanel.add(passLabel);
        inputPanel.add(passField);

        // Button panel
        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);

        // Adding components to panel
        panel.add(titleLabel);
        panel.add(inputPanel);
        panel.add(buttonPanel);

        // Add panel to frame
        add(panel);

        // Visibility
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {
            new MainPage("admin"); // Open admin main page
            dispose();
        } else {
            String role = getUserRole(username, password);
            if (role != null) {
                new MainPage(username); // Open user main page
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getUserRole(String username, String password) {
        String query = "SELECT employee_role FROM Employee WHERE username = ? AND password = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("employee_role");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
