package views;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.ActionListener;

public class FramaView extends JFrame {
    public FramaView() {
        setTitle("Frama - Welcome");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null); // Center screen
        setUndecorated(true); // Remove window bar for cleaner look
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(13, 71, 161), 2)); // Blue border

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Welcome to Frama");
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setForeground(new Color(33, 150, 243)); // Light blue
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Subtitle
        JLabel subtitle = new JLabel("A Minimal Custom Java MVC Framework");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitle.setForeground(Color.LIGHT_GRAY);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        // Button
        JButton startButton = new JButton("Get Started");
        startButton.setFocusPainted(false);
        startButton.setBackground(new Color(33, 150, 243));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        startButton.addActionListener(e -> {
            // Contoh: navigasi ke controller selanjutnya
            new LoginView();  // panggil view halaman berikutnya
            dispose();
        });

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(startButton);

        // Vertical box
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(80));
        box.add(title);
        box.add(Box.createVerticalStrut(20));
        box.add(subtitle);
        box.add(Box.createVerticalGlue());

        mainPanel.add(box, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }
}
