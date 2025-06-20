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
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getById(int userId) {
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE user_id = ?")
            ) {
                stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("user_name"),
                    rs.getTimestamp("date_time")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean update(String email, String password, String user_name, int user_id) {
        // Perhatikan bahwa user_name seharusnya String, bukan int
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE user SET email = ?, password = ?, user_name = ? WHERE user_id = ?")) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, user_name);
            stmt.setInt(4, user_id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int userId) {
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE user_id = ?")) 
            {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
