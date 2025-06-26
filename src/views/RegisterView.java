package views;

import controllers.RegisterController;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class RegisterView extends JFrame {
    public RegisterView() {
        setTitle("Register - SIMNOT");
        setSize(648, 598);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setLayout(null);

        // Label Register
        JLabel labelRegister = new JLabel("REGISTER");
        labelRegister.setFont(new Font("Arial", Font.BOLD, 40));
        labelRegister.setBounds(85, 70, 500, 48);
        panel.add(labelRegister);

        // Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setBounds(85, 170, 41, 17);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(85, 197, 478, 40);
        emailField.setBackground(Color.decode("#BEBEBE"));
        emailField.setBorder(LineBorder.createGrayLineBorder());
        panel.add(emailField);

        // Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setBounds(85, 247, 100, 17);
        panel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(85, 274, 478, 40);
        usernameField.setBackground(Color.decode("#BEBEBE"));
        usernameField.setBorder(LineBorder.createGrayLineBorder());
        panel.add(usernameField);

        // Password
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passLabel.setBounds(85, 324, 69, 17);
        panel.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(85, 351, 478, 40);
        passField.setBackground(Color.decode("#BEBEBE"));
        passField.setBorder(LineBorder.createGrayLineBorder());
        panel.add(passField);

        // Confirm Password
        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmLabel.setBounds(85, 401, 150, 17);
        panel.add(confirmLabel);

        JPasswordField confirmField = new JPasswordField();
        confirmField.setBounds(85, 428, 478, 40);
        confirmField.setBackground(Color.decode("#BEBEBE"));
        confirmField.setBorder(LineBorder.createGrayLineBorder());
        panel.add(confirmField);

        // Tombol Login
        JButton btnLogin = new JButton("<HTML><u>Login</u></HTML>");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        btnLogin.setBounds(342, 480, 110, 40);
        btnLogin.setBorderPainted(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener((e) -> {
            new LoginView();
            dispose();
        });
        panel.add(btnLogin);

        // Tombol Register
        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(452, 480, 111, 40);
        btnRegister.setBackground(Color.decode("#00A9E0"));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setOpaque(true);
        btnRegister.setFocusPainted(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRegister.addActionListener((e) -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passField.getPassword()).trim();
            String confirmPassword = new String(confirmField.getPassword()).trim();

            System.out.println("Email yang dikirim: " + email);


            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Kolom tidak boleh kosong!");
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Konfirmasi password tidak sesuai!");
            } else {
                boolean result = RegisterController.registerAdd(email, password, username);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Register sukses!");
                    new LoginView();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Register gagal!");
                }
            }
        });

        panel.add(btnRegister);

        // Dummy logo
        JPanel logo = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);    g.fillOval(40, 0, 15, 15);
                g.setColor(Color.ORANGE); g.fillOval(20, 20, 15, 15);
                g.setColor(Color.GREEN);  g.fillOval(40, 40, 15, 15);
                g.setColor(Color.CYAN);   g.fillOval(60, 20, 15, 15);
            }
        };
        logo.setBounds(425, 70, 80, 60);
        logo.setOpaque(false);
        panel.add(logo);

        add(panel);
        setVisible(true);
    }
}
