package models;

import config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Frama {
    private int id;

    // Constructors
    public Frama() {}

    public Frama(int id) {
        this.id = id;
    }

    // Getters
    public int getId( ) { return id; }


    // ===== CRUD Methods =====
    public boolean insert( ) {
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Frama (id, dateTime) VALUES (?, NOW())", Statement.RETURN_GENERATED_KEYS)
            ) {
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update( ) {
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Frama SET ... WHERE id = ?")
            ) {
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete( ) {
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Frama WHERE id = ?")) {
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Frama getById( ) {
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Frama WHERE id = ?")
            ) {
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
