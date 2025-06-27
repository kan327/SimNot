package views;

import controllers.LoginController;
import lib.UserStorage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import models.User;

public class LoginView extends JFrame {
    public LoginView() {
        setTitle("Login");
        setSize(648, 598);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // tengah

        // panel utama
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240)); // abu-abu terang
        panel.setLayout(null);

        // Label Login
        JLabel labelLogin = new JLabel("LOGIN");
        labelLogin.setFont(new Font("Arial", Font.BOLD, 40));
        labelLogin.setBounds(85, 137, 133, 48);
        panel.add(labelLogin);

        // Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setBounds(85, 257, 41, 17);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(85, 284, 478, 40);
        emailField.setBackground(Color.decode("#BEBEBE"));
        emailField.setBorder(LineBorder.createGrayLineBorder());
        panel.add(emailField);

        // Password
        JLabel passLabel = new JLabel("Password");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // <- masih ada kesalahan ini btw
        passLabel.setBounds(85, 344, 69, 17);
        panel.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(85, 371, 478, 40);
        passField.setBackground(Color.decode("#BEBEBE"));
        passField.setBorder(LineBorder.createGrayLineBorder());
        panel.add(passField);

        // Tombol Register
        String dumReg = "<HTML><u>Register</u></HTML>";
        JButton btnRegister = new JButton(dumReg);
        emailLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 14)); // <- kesalahan lagi, ini harusnya bukan emailLabel
        btnRegister.setBounds(342, 431, 110, 40);
        btnRegister.setBorderPainted(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.setForeground(Color.BLACK);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegister.addActionListener((actionEvent) -> {
            new RegisterView();
            dispose();
        });
        panel.add(btnRegister);

        // Tombol Login
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(452, 431, 111, 40); // pastiin ini sesuai ukuran panel
        btnLogin.setBackground(Color.decode("#00A9E0"));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setOpaque(true); // PENTING
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false); // opsional
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener((actionEvent) -> {
            String email = emailField.getText().trim();
    String password = new String(passField.getPassword()).trim();

    if (email.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Email dan Password tidak boleh kosong!");
        return;
    }

    User user = LoginController.login(email, password); // Panggil via controller

    if (user != null) {
        UserStorage.setLocalUser(user.getUser_id(), user.getEmail(), user.getPassword(), user.getUser_name(), user.getDate_time());
        JOptionPane.showMessageDialog(null, "Login berhasil! Hai, " + user.getUser_name());
        new HomeView(); // masuk ke home
        dispose();
    } else {
        JOptionPane.showMessageDialog(null, "Email atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
    }
        });
        panel.add(btnLogin);

        // Dummy logo (lingkaran warna-warni)
        JPanel logo = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillOval(40, 0, 15, 15);
                g.setColor(Color.ORANGE);
                g.fillOval(20, 20, 15, 15);
                g.setColor(Color.GREEN);
                g.fillOval(40, 40, 15, 15);
                g.setColor(Color.CYAN);
                g.fillOval(60, 20, 15, 15);
            }
        };
        logo.setBounds(125+300, 130+10, 80, 60);
        logo.setOpaque(false);
        panel.add(logo);

        add(panel);
        setVisible(true);
    }
}
