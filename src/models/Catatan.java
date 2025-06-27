package models;

import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Catatan {

    private int catatan_id;
    private String judul;
    private String isi;
    private Timestamp date_time;

    // Constructors
    public Catatan() {
    }

    public Catatan(int catatan_id, String judul, String isi, Timestamp date_time) {
        this.catatan_id = catatan_id;
        this.judul = judul;
        this.isi = isi;
        this.date_time = date_time;
    }

    // Getters
    public int getId() { return catatan_id; }
    public String getJudul() { return judul; }
    public String getIsi() { return isi; }
    public Timestamp getDate_time() { return date_time; }

    // ===== CRUD Methods =====
    public static boolean insert(int user_id, String judul, String isi) {
        try (
            Connection conn = DBConnection.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO catatan (judul, isi, user_id) VALUES (?, ?, ?)")) {
            stmt.setString(1, judul);
            stmt.setString(2, isi);
            stmt.setInt(3, user_id);
            int result = stmt.executeUpdate();

            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(int id, String judul, String isi) {
        try (
                Connection conn = DBConnection.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement("UPDATE catatan SET judul = ?, isi = ? WHERE catatan_id = ?")
                ) {
            stmt.setString(1, judul);
            stmt.setString(2, isi);
            stmt.setInt(3, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int id) {
        try (
                Connection conn = DBConnection.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM catatan WHERE catatan_id = ?")
                ) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Catatan getById(int id) {
        try (
            Connection conn = DBConnection.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM catatan WHERE catatan_id = ?")
            ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Catatan(
                    rs.getInt("catatan_id"),
                    rs.getString("judul"),
                    rs.getString("isi"),
                    rs.getTimestamp("date_time")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static List<Catatan> getAll(int id) {
        try (
            Connection conn = DBConnection.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM catatan WHERE user_id = ? ORDER BY date_time DESC");
            ) {
            stmt.setInt(1, id); 
            ResultSet rs = stmt.executeQuery();
            List<Catatan> catatanList = new ArrayList<>();
            while (rs.next()) {
                Catatan catatan = new Catatan(
                    rs.getInt("catatan_id"),
                    rs.getString("judul"),
                    rs.getString("isi"),
                    rs.getTimestamp("date_time")
                );
                catatanList.add(catatan);
            }
            return catatanList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
