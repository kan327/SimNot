package views;

import controllers.HomeController;
import java.awt.*;
import javax.swing.*;
import models.Catatan;

public class NoteCardView extends JPanel {

    public NoteCardView(Catatan data) {
        System.out.println("NoteCardView initialized with data: " + data.getId());
        setPreferredSize(new Dimension(574, 150)); // tinggi card
        setMaximumSize(new Dimension(574, 150)); // 🔒 Batas maksimal
        setMinimumSize(new Dimension(574, 150)); // 🔒 Batas minimal
        setBackground(Color.decode("#E0E0E0"));
        setLayout(new BorderLayout());

        // Panel isi utama
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15)); // padding

        // Title
        JLabel titleNoteLabel = new JLabel(data.getJudul());
        titleNoteLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleNoteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Date
        JLabel labelDate = new JLabel("28, Jun 2025");
        labelDate.setFont(new Font("Arial", Font.PLAIN, 14));
        labelDate.setForeground(Color.DARK_GRAY);
        labelDate.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Note Text (wrap pakai HTML)
        JLabel labelNote = new JLabel(
                "<html>" + data.getIsi() + "</html>"
        );
        labelNote.setFont(new Font("Arial", Font.PLAIN, 14));
        labelNote.setForeground(Color.BLACK);
        labelNote.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Tambahin semua ke content
        contentPanel.add(titleNoteLabel);
        contentPanel.add(Box.createVerticalStrut(3));
        contentPanel.add(labelDate);
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(labelNote);

        add(contentPanel, BorderLayout.CENTER);

        // Panel tombol bawah
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 15));

        // Tombol kiri (View More)
        JButton btnView = new JButton("<HTML><u>View More</u></HTML>");
        btnView.setFont(new Font("Arial", Font.PLAIN, 14));
        btnView.setForeground(new Color(0, 128, 0)); // hijau
        btnView.setContentAreaFilled(false);
        btnView.setBorderPainted(false);
        btnView.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new DetailNoteView(data.getId());
                SwingUtilities.getWindowAncestor(NoteCardView.this).dispose(); // nutup frame
            }
        });

        // Tombol kanan (Edit dan Delete)
        JPanel rightBtnPanel = new JPanel();
        rightBtnPanel.setOpaque(false);
        rightBtnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JButton btnEdit = new JButton("<HTML><u>Edit</u></HTML>");
        btnEdit.setFont(new Font("Arial", Font.PLAIN, 14));
        btnEdit.setForeground(Color.ORANGE);
        btnEdit.setContentAreaFilled(false);
        btnEdit.setBorderPainted(false);
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new FormView(data.getId());
                SwingUtilities.getWindowAncestor(NoteCardView.this).dispose(); // nutup frame
            }
        });

            JButton btnDelete = new JButton("<HTML><u>Delete</u></HTML>");
        btnDelete.setFont(new Font("Arial", Font.PLAIN, 14));
        btnDelete.setForeground(Color.RED);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int confirm = JOptionPane.showConfirmDialog(
                        NoteCardView.this,
                        "Are you sure you want to delete this note?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean res = HomeController.destroyCatatan(data.getId());
                    if (res) {
                        Container parent = NoteCardView.this.getParent();
                        if (parent != null) {
                            parent.remove(NoteCardView.this);
                            parent.revalidate();
                            parent.repaint();
                        }
                        JOptionPane.showMessageDialog(NoteCardView.this, "Note deleted successfully.");
                    }

                }
            }
        });

        rightBtnPanel.add(btnEdit);
        rightBtnPanel.add(btnDelete);

        buttonPanel.add(btnView, BorderLayout.WEST);
        buttonPanel.add(rightBtnPanel, BorderLayout.EAST);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
