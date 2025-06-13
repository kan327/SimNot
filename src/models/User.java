package models;

import config.DBConnection;
import java.sql.*;

public class User {
    private int user_id;
    private String email;
    private String password;
    private String user_name;
    private Timestamp date_time;
    //belum ditambah disini nih

    // Constructors
    public User() {}

    public User(int user_id, String email, String password, String user_name, Timestamp date_time) {
        this.user_id = user_id ;
        this.email = email;
        this.password = password;
        this.user_name = user_name;
        this.date_time = date_time;
    }

    // Getters
    public int getUser_id( ) { return user_id; }
    public String getEmail( ) { return email; }
    public String getPassword( ) { return password; }
    public String getUser_name( ) { return user_name; }
    public Timestamp getDate_time( ) { return date_time; }

    // ===== CRUD Methods =====
    public static boolean insert(String email, String password, String user_name) {
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (email,password,user_name) VALUE (?,?,?)", Statement.RETURN_GENERATED_KEYS)
            ) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, user_name);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update( ) {
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("")
            ) {
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete( ) {
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("")) {
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getById( ) {
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("")
            ) {
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
