package views;

import controllers.HomeController;
import java.awt.*;
import java.util.List;
import javax.swing.*;

import lib.UserStorage;
import models.Catatan;
import models.User;

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

        // ================== SEARCH SECTION ==================
        JPanel searchSectionPanel = new JPanel();
        searchSectionPanel.setLayout(new BoxLayout(searchSectionPanel, BoxLayout.Y_AXIS));
        searchSectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        searchSectionPanel.setBackground(new Color(240, 240, 240));

        JLabel labelSearch = new JLabel("Search - Note");
        labelSearch.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel searchLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchLabelPanel.setOpaque(false);
        searchLabelPanel.add(labelSearch);
        searchSectionPanel.add(searchLabelPanel);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setOpaque(false);

        JTextField searchField = new JTextField(30);
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        searchField.setBackground(Color.decode("#BEBEBE"));
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        searchPanel.add(searchField);

        searchPanel.add(Box.createHorizontalStrut(5));

        JButton btnSearch = new JButton("   Search   ");
        btnSearch.setBackground(Color.decode("#00A9E0"));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setOpaque(true);
        btnSearch.setFocusPainted(false);
        btnSearch.setBorderPainted(false);
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchPanel.add(Box.createHorizontalStrut(8));
        searchPanel.add(btnSearch);

        JButton btnAdd = new JButton("  Add Note  ");
        btnAdd.setBackground(Color.decode("#4CAF50"));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setOpaque(true);
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.addActionListener(e -> {
            new FormView(0);
            dispose();
        });
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(btnAdd);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(searchPanel, BorderLayout.WEST);
        searchSectionPanel.add(wrapper);

        // Gabungkan header + search ke panel atas
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchSectionPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // ================== NOTE CARDS SECTION ==================
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        contentPanel.setBackground(new Color(240, 240, 240));

        JPanel noteCardPanel = new JPanel();
        noteCardPanel.setLayout(new BoxLayout(noteCardPanel, BoxLayout.Y_AXIS));
        noteCardPanel.setOpaque(false);

        List<Catatan> catatanList = HomeController.getAllCatatan();

        if (catatanList == null || catatanList.isEmpty()) {
            JLabel noNotesLabel = new JLabel("No notes available.");
            noNotesLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            noNotesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            noteCardPanel.setPreferredSize(new Dimension(noteCardPanel.getWidth(), 200));
            noteCardPanel.add(noNotesLabel);
            noteCardPanel.add(Box.createVerticalStrut(10));
        } else {
            noteCardPanel.setAlignmentY(Component.TOP_ALIGNMENT);
            for (Catatan item : catatanList) {
                noteCardPanel.add(new NoteCardView(item));
                noteCardPanel.add(Box.createVerticalStrut(10));
            }
        }

        contentPanel.add(noteCardPanel);

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
