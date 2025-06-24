package views;

import java.awt.*;
import javax.swing.*;

public class DetailNoteView extends JFrame {
    public DetailNoteView() {
        setTitle("SIMNOT - Home");
        setSize(648, 598);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        

        // ================== HEADER ==================
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setPreferredSize(new Dimension(700, 70));
        headerPanel.setBackground(Color.decode("#EDEDED"));

        // Logo (dummy lingkaran)
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

        // SIMNOT
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
        btnLogout.addActionListener((actionEvent) -> {
            new LoginView();
            dispose();
        });
        headerPanel.add(btnLogout);

        add(headerPanel, BorderLayout.NORTH);

        // ================== MAIN CONTENT ==================
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 35, 20, 35));
        contentPanel.setBackground(new Color(240,240,240));

        // Back
        JButton btnBack = new JButton(" Back ");
        btnBack.setBackground(Color.decode("#00A9E0"));
        btnBack.setForeground(Color.WHITE);
        btnBack.setOpaque(true);
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnBack.addActionListener((actionEvent) -> {
            new HomeView();
            dispose();
        });

        contentPanel.add(btnBack);
        contentPanel.add(Box.createVerticalStrut(10));

        // Judul dan tombol edit/delete
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titleLabel = new JLabel("Todays Activity - Untitled");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titlePanel.add(titleLabel, BorderLayout.WEST);

        JPanel actionPanel = new JPanel();
        actionPanel.setOpaque(false);
        actionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));

        JButton btnEdit = new JButton("  Edit  ");
        btnEdit.setFont(new Font("Arial", Font.PLAIN, 16));
        btnEdit.setBackground(Color.decode("#FF9800"));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setOpaque(true);
        btnEdit.setFocusPainted(false);
        btnEdit.setBorderPainted(false);
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.addActionListener((actionEvent) -> {
            new EditView();
            dispose();
        });
        actionPanel.add(btnEdit);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.PLAIN, 16));
        btnDelete.setBackground(Color.decode("#F44336"));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setOpaque(true);
        btnDelete.setFocusPainted(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionPanel.add(btnDelete);

        titlePanel.add(actionPanel, BorderLayout.EAST);
        contentPanel.add(titlePanel);

        contentPanel.add(Box.createVerticalStrut(5));

        JLabel dateLabel = new JLabel("28, Jun 2025");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateLabel.setForeground(Color.DARK_GRAY);
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(dateLabel);

        contentPanel.add(Box.createVerticalStrut(20));

        JTextArea noteArea = new JTextArea(
            "This morning, I started my day with a quick workout to get energized. After that, I had a healthy breakfast with some oatmeal and fruit. Then,.....This morning, I started my day with a quick workout to get energized. After that, I had a healthy breakfast with some oatmeal and fruit. Then,.....This morning, I started my day with a quick workout to get energized. After that, I had a healthy breakfast with some oatmeal and fruit. Then,.....This morning, I started my day with a quick workout to get energized. After that, I had a healthy breakfast with some oatmeal and fruit. Then,.....This morning, I started my day with a quick workout to get energized. After that, I had a healthy breakfast with some oatmeal and fruit. Then,....."
        );
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);
        noteArea.setEditable(false);
        noteArea.setFont(new Font("Arial", Font.PLAIN, 16));
        
        noteArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(noteArea);
        scrollPane.setBorder(null);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        scrollPane.setBackground(Color.WHITE);
        contentPanel.add(scrollPane);

        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DetailNoteView();
    }
}
