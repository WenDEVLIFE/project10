package projectorg10;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CustomJDialog extends JDialog {

    public CustomJDialog(JFrame parent, String title, boolean modal, String message) {
        super(parent, title, modal);
        setLocationRelativeTo(parent);
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set BorderLayout for the dialog
        setLayout(new BorderLayout(10, 10));

        // Set background color for the dialog
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Add left logo
        JLabel leftLogo = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logoresize.jpg"))));
        add(leftLogo, BorderLayout.WEST);

        // Add right logo
        JLabel rightLogo = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pocpls.jpg"))));
        add(rightLogo, BorderLayout.EAST);

        // Add central content panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Example layout
        centerPanel.setBackground(new java.awt.Color(5, 7, 153));
        centerPanel.add(new JLabel(message, JLabel.CENTER));
        add(centerPanel, BorderLayout.CENTER);
    }

    public void showDialog() {
        setVisible(true);
    }

    public void closeDialog() {
        dispose();
    }
}