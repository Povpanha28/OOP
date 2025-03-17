package test_file;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

        // Set background color for the frame
        getContentPane().setBackground(new Color(240, 240, 240)); // light grey background

        // Panel with BoxLayout for better centering
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        // Title
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 32));
        titleLabel.setForeground(new Color(50, 50, 150)); // Change title color

        // Input panel with custom styles and padding
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10)); // Add horizontal and vertical gap
        inputPanel.setBackground(new Color(240, 240, 240));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Padding around input panel

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        userLabel.setForeground(new Color(0, 0, 0));

        userField = new JTextField(10);
        userField.setFont(new Font("Arial", Font.PLAIN, 18));
        userField.setBackground(Color.WHITE);
        userField.setForeground(new Color(50, 50, 150));

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passLabel.setForeground(new Color(0, 0, 0));

        passField = new JPasswordField(10);
        passField.setFont(new Font("Arial", Font.PLAIN, 18));
        passField.setBackground(Color.WHITE);
        passField.setForeground(new Color(50, 50, 150));

        inputPanel.add(userLabel);
        inputPanel.add(userField);
        inputPanel.add(passLabel);
        inputPanel.add(passField);

        // Button panel with added styling
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(50, 50, 150)); // Dark blue background
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border around button
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);

        // Add button to panel
        buttonPanel.add(loginButton);

        // Add components to the main panel
        panel.add(Box.createVerticalGlue()); // Adds vertical space to center the content
        panel.add(titleLabel);
        panel.add(inputPanel);
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue()); // Adds more vertical space to center

        // Add panel to frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login Successful");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
