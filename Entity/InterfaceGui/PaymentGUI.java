package Entity.InterfaceGui;

import Database.MySQLConnection;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Map;
import javax.swing.*;

public class PaymentGUI extends JFrame {

    private JFrame frame;
    private JPanel panel;
    private Map<String, ShopManagementGUI.CartItem> cart;
    private JTextField membershipField; // To input membership code
    private JLabel totalLabel; // To update the total price dynamically
    private double totalPrice; // Total price of the cart, now defined as a class member
    private Connection connection;

    // Constructor to initialize the cart and create the GUI
    public PaymentGUI(Map<String, ShopManagementGUI.CartItem> cart) {
        this.cart = cart;
        this.totalPrice = 0; // Initialize total price to 0
        connection = MySQLConnection.getConnection(); // Get the database connection
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Payment Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 350); // Increased height to accommodate membership input
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Payment Summary", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Calculate the total price of the cart
        for (ShopManagementGUI.CartItem item : cart.values()) {
            JLabel itemLabel = new JLabel(item.getName() + " (x" + item.getQuantity() + ") - $" + item.getTotalPrice());
            panel.add(itemLabel);
            totalPrice += item.getTotalPrice(); // Sum the total price
        }

        totalLabel = new JLabel("Total: $" + totalPrice, SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(totalLabel, BorderLayout.CENTER);

        // Membership code input section
        JPanel membershipPanel = new JPanel();
        JLabel membershipLabel = new JLabel("Enter Membership Code: ");
        membershipField = new JTextField(15);
        membershipPanel.add(membershipLabel);
        membershipPanel.add(membershipField);
        frame.add(membershipPanel, BorderLayout.NORTH);

        // Button for confirming payment
        JButton payButton = new JButton("Confirm Payment");
        payButton.setFont(new Font("Arial", Font.BOLD, 14));
        payButton.setBackground(Color.GREEN);
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String membershipCode = membershipField.getText();
                double finalTotalPrice = totalPrice;

                // Check if membership code is valid
                boolean isValidMembership = checkMembershipCode(membershipCode);

                // Apply discount if valid membership code
                if (isValidMembership) {
                    finalTotalPrice = totalPrice * 0.9; // 10% discount
                    JOptionPane.showMessageDialog(frame, "Valid membership! 10% discount applied.", "Discount Applied", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid membership code.", "Invalid Code", JOptionPane.ERROR_MESSAGE);
                }

                // Display final total
                totalLabel.setText("Total: $" + finalTotalPrice);

                // Confirm payment
                JOptionPane.showMessageDialog(frame, "Payment Successful! Total: $" + finalTotalPrice, "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); // Close payment window after successful payment
                SwingUtilities.invokeLater(() -> ShopManagementGUI.main(new String[]{}));
            }
        });

        frame.add(payButton, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Method to check if the membership code exists in the database
    private boolean checkMembershipCode(String membershipCode) {
        String query = "SELECT COUNT(*) FROM membership WHERE membershipCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, membershipCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Return true if membership code exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an error occurs or the code doesn't exist
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Example cart with mock data (you can replace this with your actual cart object)
        Map<String, ShopManagementGUI.CartItem> cart = null;  // You will need to pass a real cart here.
        
        // Run the payment GUI
        SwingUtilities.invokeLater(() -> new PaymentGUI(cart));
    }
}
