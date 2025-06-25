package models;

import config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Catatan {
    private int id;
    private String judul;
    private String isi;
    private Timestamp date_time;
                
    // Constructors
    public Catatan() {}

    public Catatan(int id) {
        this.id = id;
        this.judul = "";
        this.isi = "";
        this.date_time = new Timestamp(System.currentTimeMillis());
    }

    // Getters
    public int getId( ) { return id; }
    public String getJudul( ) { return judul; }
    public String getIsi( ) { return isi; }
    public Timestamp getDate_time( ) { return date_time; }


    // ===== CRUD Methods =====
    public boolean insert( ) {
        String sql = "INSERT INTO catatan (judul, isi, date_time) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, judul);
            stmt.setString(2, isi);
            stmt.setTimestamp(3, date_time);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update() {
        String sql = "UPDATE catatan SET judul = ?, isi = ?, date_time = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("")
            ) {
            stmt.setString(1, judul);
            stmt.setString(2, isi);
            stmt.setTimestamp(3, date_time);
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete( ) {
        String sql = "DELETE FROM catatan WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Catatan getById( int id) {
        String sql = "SELECT * FROM catatan WHERE id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();    
            if (rs.next()) {
                Catatan catatan = new Catatan();
                catatan.id = rs.getInt("id");
                catatan.judul = rs.getString("judul");
                catatan.isi = rs.getString("isi");
                catatan.date_time = rs.getTimestamp("date_time");
                return catatan;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
     
    }

}
public static List<Catatan> getAll() {
        List<Catatan> catatanList = new ArrayList<>();
        String sql = "SELECT * FROM catatan";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Catatan catatan = new Catatan();
                catatan.id = rs.getInt("id");
                catatan.judul = rs.getString("judul");
                catatan.isi = rs.getString("isi");
                catatan.date_time = rs.getTimestamp("date_time");
                catatanList.add(catatan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catatanList;
    }