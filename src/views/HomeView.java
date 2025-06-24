package views;

import java.awt.*;
import javax.swing.*;

public class HomeView extends JFrame {
    public HomeView() {
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
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        contentPanel.setBackground(new Color(240, 240, 240));

        // Search Section
        JLabel labelSearch = new JLabel("Search - Note");
        labelSearch.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel searchLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align to left
        searchLabelPanel.setOpaque(false);
        searchLabelPanel.add(labelSearch);
        contentPanel.add(searchLabelPanel);

        // Ubah searchPanel jadi BoxLayout biar rata kiri semua
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setOpaque(false);
            
        JTextField searchField = new JTextField(30);
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // biar fleksibel
        searchField.setBackground(Color.decode("#BEBEBE"));
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        searchPanel.add(searchField);
            
        searchPanel.add(Box.createHorizontalStrut(5)); // jarak
            
        JButton btnSearch = new JButton("   Search   ");
        btnSearch.setBackground(Color.decode("#00A9E0"));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setOpaque(true);
        btnSearch.setFocusPainted(false);
        btnSearch.setBorderPainted(false);
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchPanel.add(Box.createHorizontalStrut(8));
        searchPanel.add(btnSearch);

        JButton btnEdit = new JButton("  Add Note  ");
        btnEdit.setBackground(Color.decode("#4CAF50"));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setOpaque(true);
        btnEdit.setFocusPainted(false);
        btnEdit.setBorderPainted(false);
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(btnEdit);
        

            

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(searchPanel, BorderLayout.WEST);
        contentPanel.add(wrapper);

        contentPanel.add(Box.createVerticalStrut(10));
        

        // Note Cards
        JPanel noteCardPanel = new JPanel();
        noteCardPanel.setLayout(new BoxLayout(noteCardPanel, BoxLayout.Y_AXIS));
        noteCardPanel.setOpaque(false);
        
        

        for (int i = 0; i < 5; i++) {
            noteCardPanel.add(new NoteCardView());
            noteCardPanel.add(Box.createVerticalStrut(10));
        }

        contentPanel.add(noteCardPanel);

        // Scroll
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HomeView();
    }
}
