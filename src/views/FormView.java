package views;

import controllers.FormController;
import lib.UserStorage;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import models.Catatan;
import models.User;

public class FormView extends JFrame {

    public FormView(int id) {
        String method;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd, MMM yyyy");
        String formattedDate = today.format(formatter);

        
        if (id > 0) {
            method = "Edit";
            setTitle("SIMNOT - Edit Note");
        } else {
            method = "Create";
            setTitle("SIMNOT - Create Note");
        }
        
        Catatan data = null;
        if (method.equals("Edit")) {
            data = FormController.initData(id);
        }
        System.out.println("FormView initialized with ID: " + id);
        System.out.println(method + " method selected");
        System.out.println(data != null ? "Data loaded successfully" : "No data to edit");
        System.out.println(data.getJudul() + " - " + data.getIsi());

        setSize(648, 598);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ========== HEADER ==========
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setPreferredSize(new Dimension(700, 70));
        headerPanel.setBackground(Color.decode("#EDEDED"));

        // Logo
        JPanel logo = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillOval(0, 10, 12, 12);
                g.setColor(Color.ORANGE);
                g.fillOval(12, 0, 12, 12);
                g.setColor(Color.GREEN);
                g.fillOval(24, 10, 12, 12);
                g.setColor(Color.CYAN);
                g.fillOval(12, 20, 12, 12);
            }
        };
        logo.setBounds(20, 15, 40, 40);
        logo.setOpaque(false);
        headerPanel.add(logo);

        // SIMNOT Title
        JLabel labelTitle = new JLabel("SIMNOT");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitle.setBounds(67, 15, 100, 30);
        headerPanel.add(labelTitle);

        User user = UserStorage.getLocalUser();
        JLabel labelUser = new JLabel(user.getUser_name());
        labelUser.setFont(new Font("Arial", Font.PLAIN, 14));
        labelUser.setHorizontalAlignment(SwingConstants.RIGHT);
        labelUser.setBounds(510, 5, 100, 20);
        headerPanel.add(labelUser);

        JButton btnLogout = new JButton("<HTML><u>Logout</u></HTML>");
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
        btnLogout.setForeground(Color.RED);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.setBounds(543, 25, 100, 20);
        btnLogout.addActionListener(e -> {
            UserStorage.clearLocalUser();
            new LoginView();
            dispose();
        });
        headerPanel.add(btnLogout);
        add(headerPanel, BorderLayout.NORTH);

        // ========== MAIN CONTENT ==========
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 25, 25));

        // Create a new panel for the date label and back button
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false); // Make it transparent
// Back Button
        JButton backBtn = new JButton("< Back");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        backBtn.setForeground(Color.BLUE);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> {
            new HomeView(); // Navigate back to details
            dispose();
        });
        topPanel.add(backBtn);
// Date Label
        JLabel dateLabel = new JLabel(formattedDate);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateLabel.setForeground(Color.GRAY);
        topPanel.add(dateLabel);
// Add the top panel to the content panel
        contentPanel.add(topPanel);
        contentPanel.add(Box.createVerticalStrut(10));

        // Title Field
        JTextField titleField = new JTextField((method.equals("Edit")) ? data.getJudul() : "Untitled Note");
        titleField.setFont(new Font("Arial", Font.BOLD, 18));
        titleField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        contentPanel.add(titleField);

        contentPanel.add(Box.createVerticalStrut(10));

        // Date (Label)
        contentPanel.add(Box.createVerticalStrut(10));

        // Content Area
        JTextArea noteArea = new JTextArea(
            (method.equals("Edit")) ? data.getIsi() : "..."
        );
        noteArea.setFont(new Font("Arial", Font.PLAIN, 15));
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);
        noteArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane noteScroll = new JScrollPane(noteArea);
        noteScroll.setPreferredSize(new Dimension(550, 300));
        contentPanel.add(noteScroll);

        contentPanel.add(Box.createVerticalStrut(20));

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBackground(Color.LIGHT_GRAY);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.addActionListener(e -> {
            new HomeView();
            dispose();
        });

        JButton btnSave = new JButton(method+" Note");
        btnSave.setBackground(Color.decode("#00A9E0"));
        btnSave.setForeground(Color.WHITE);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setOpaque(true);
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.addActionListener(e -> {
            boolean res = false;
            if (method.equals("Create")) {
                res = FormController.createNote(titleField.getText(), noteArea.getText());
            } else {
                res = FormController.updateNote(id, titleField.getText(), noteArea.getText());
            }

            System.out.println(res);

            if (res) {
                new HomeView();
                dispose();
            }
        });

        // Tambahkan ke panel
        btnPanel.add(btnCancel);
        btnPanel.add(btnSave);
        contentPanel.add(btnPanel);

        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FormView(0);
    }
}
