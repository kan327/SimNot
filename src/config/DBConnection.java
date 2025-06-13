package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/simnot_db"; // ganti dengan route database Anda
    private static final String USER = "root"; // user MySQL Anda
    private static final String PASS = "";     // password MySQL Anda

    public static Connection getConnection() {
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver"); // pastikan mysql-connector .jar sudah ditambahkan
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal Koneksi DB!", e);

        }
    }
}
