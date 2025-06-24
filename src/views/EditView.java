package views;

import java.awt.*;
import javax.swing.*;

public class EditView extends JFrame {
    public EditView() {
        setTitle("SIMNOT - Edit Note");
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
                g.setColor(Color.RED);    g.fillOval(0, 10, 12, 12);
                g.setColor(Color.ORANGE); g.fillOval(12, 0, 12, 12);
                g.setColor(Color.GREEN);  g.fillOval(24, 10, 12, 12);
                g.setColor(Color.CYAN);   g.fillOval(12, 20, 12, 12);
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

        // Username
        JLabel labelUser = new JLabel("JhonDoe");
        labelUser.setFont(new Font("Arial", Font.PLAIN, 14));
        labelUser.setHorizontalAlignment(SwingConstants.RIGHT);
        labelUser.setBounds(530, 5, 100, 20);
        headerPanel.add(labelUser);

        // Logout
        JButton btnLogout = new JButton("<HTML><u>Logout</u></HTML>");
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
        btnLogout.setForeground(Color.RED);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.setBounds(543, 25, 100, 20);
        btnLogout.addActionListener(e -> {
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
    new DetailNoteView(); // Navigate back to details
    dispose();
});
topPanel.add(backBtn);
// Date Label
JLabel dateLabel = new JLabel("28, Jun 2025");
dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
dateLabel.setForeground(Color.GRAY);
topPanel.add(dateLabel);
// Add the top panel to the content panel
contentPanel.add(topPanel);
contentPanel.add(Box.createVerticalStrut(10));

        // Title Field
        JTextField titleField = new JTextField("Todays Activity - Untitled");
        titleField.setFont(new Font("Arial", Font.BOLD, 18));
        titleField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        contentPanel.add(titleField);

        contentPanel.add(Box.createVerticalStrut(10));

        // Date (Label)
        

        contentPanel.add(Box.createVerticalStrut(10));

        // Content Area
        JTextArea noteArea = new JTextArea(
            "This morning, I started my day with a quick workout to get energized. After that, I had a healthy breakfast with some oatmeal and fruit. Then,..."
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
            new DetailNoteView();
            dispose();
        });

        JButton btnSave = new JButton("Save");
        btnSave.setBackground(Color.decode("#00A9E0"));
        btnSave.setForeground(Color.WHITE);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setOpaque(true);
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);

        // Tambahkan ke panel
        btnPanel.add(btnCancel);
        btnPanel.add(btnSave);
        contentPanel.add(btnPanel);

        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new EditView();
    }
}
