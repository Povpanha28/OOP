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
    private JTextField membershipField;
    private JLabel totalLabel;
    private double totalPrice;
    private Connection connection;

    public PaymentGUI(Map<String, ShopManagementGUI.CartItem> cart) {
        this.cart = cart;
        this.totalPrice = 0;
        connection = MySQLConnection.getConnection();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Payment Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Payment Summary", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        for (ShopManagementGUI.CartItem item : cart.values()) {
            JLabel itemLabel = new JLabel(item.getName() + " (x" + item.getQuantity() + ") - $" + item.getTotalPrice());
            panel.add(itemLabel);
            totalPrice += item.getTotalPrice();
        }

        totalLabel = new JLabel("Total: $" + totalPrice, SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(totalLabel, BorderLayout.CENTER);

        JPanel membershipPanel = new JPanel();
        JLabel membershipLabel = new JLabel("Enter Membership Code: ");
        membershipField = new JTextField(15);
        membershipPanel.add(membershipLabel);
        membershipPanel.add(membershipField);
        frame.add(membershipPanel, BorderLayout.NORTH);

        JButton payButton = new JButton("Confirm Payment");
        payButton.setFont(new Font("Arial", Font.BOLD, 14));
        payButton.setBackground(Color.GREEN);
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String membershipCode = membershipField.getText();
                double finalTotalPrice = totalPrice;

                boolean isValidMembership = checkMembershipCode(membershipCode);
                if (isValidMembership) {
                    finalTotalPrice = totalPrice * 0.9;
                    JOptionPane.showMessageDialog(frame, "Valid membership! 10% discount applied.", "Discount Applied", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid membership code.", "Invalid Code", JOptionPane.ERROR_MESSAGE);
                }

                totalLabel.setText("Total: $" + finalTotalPrice);
                updateProductStock();  // Reduce qty from database

                JOptionPane.showMessageDialog(frame, "Payment Successful! Total: $" + finalTotalPrice, "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); 

                SwingUtilities.invokeLater(() -> {
                    ShopManagementGUI shopGUI = new ShopManagementGUI();
                    shopGUI.createAndShowGUI();
                });
            }
        });

        frame.add(payButton, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private boolean checkMembershipCode(String membershipCode) {
        String query = "SELECT COUNT(*) FROM membership WHERE membershipCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, membershipCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void updateProductStock() {
        for (ShopManagementGUI.CartItem item : cart.values()) {
            String updateQuery = "UPDATE product SET qty = qty - ? WHERE name = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {
                pstmt.setInt(1, item.getQuantity());
                pstmt.setString(2, item.getName());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
