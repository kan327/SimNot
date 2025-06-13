package models;

import config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableName {
    private int id;

    // Constructors
    public TableName() {}

    public TableName(int id) {
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

    public static TableName getById( ) {
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
