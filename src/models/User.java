package models;

import config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;

    // Constructors
    public User() {}

    public User(int id) {
        this.id = id;
    }

    // Getters
    public int getId( ) { return id; }


    // ===== CRUD Methods =====
    public boolean insert( ) {
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("", Statement.RETURN_GENERATED_KEYS)
            ) {
            // ...
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
