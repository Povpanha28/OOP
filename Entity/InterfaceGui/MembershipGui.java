package Entity.InterfaceGui;

import Database.MySQLConnection;
import Entity.Sales.Membership;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;

public class MembershipGui {

    private JFrame frame;
    private JTextField nameField, phoneField, addressField;
    private Connection connection;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MembershipGui::new);
    }

    public MembershipGui() {
        frame = new JFrame("Membership Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        connection = MySQLConnection.getConnection();

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        createForm(formPanel, gbc);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Membership");
        addButton.addActionListener(this::handleAddMembership);
        buttonPanel.add(addButton);

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void createForm(JPanel formPanel, GridBagConstraints gbc) {
        formPanel.add(new JLabel("Name:"), setGridBagConstraints(gbc, 0, 0));
        nameField = new JTextField(20);
        formPanel.add(nameField, setGridBagConstraints(gbc, 1, 0));

        formPanel.add(new JLabel("Phone:"), setGridBagConstraints(gbc, 0, 1));
        phoneField = new JTextField(20);
        formPanel.add(phoneField, setGridBagConstraints(gbc, 1, 1));

        formPanel.add(new JLabel("Address:"), setGridBagConstraints(gbc, 0, 2));
        addressField = new JTextField(20);
        formPanel.add(addressField, setGridBagConstraints(gbc, 1, 2));
    }

    private GridBagConstraints setGridBagConstraints(GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
    }

    private void handleAddMembership(ActionEvent event) {
        try {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.");
                return;
            }

            // Generate membership code with the format "MEMBER" + 4 random digits
            String membershipCode = "MEMBER" + String.format("%04d", new Random().nextInt(10000));

            Membership membership = new Membership(membershipCode, name, phone, address);
            addMembershipToDatabase(membership);

            JOptionPane.showMessageDialog(frame, "Membership added successfully!");

            // Clear the fields after adding
            nameField.setText("");
            phoneField.setText("");
            addressField.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error adding membership.");
            e.printStackTrace();
        }
    }

    private void addMembershipToDatabase(Membership membership) {
        String query = "INSERT INTO membership (membershipCode, name, phone, address) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, membership.getMembershipCode());
            pstmt.setString(2, membership.getName());
            pstmt.setString(3, membership.getPhone());
            pstmt.setString(4, membership.getAddress());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

