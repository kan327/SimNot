package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://tramway.proxy.rlwy.net:57989/railway";
    private static final String USER = "root";
    private static final String PASS = "pkTNePlkjblmgkKsSZVojOxUgulITkuR";



    public static Connection getConnection() {
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver"); // pastikan mysql-connector .jar sudah ditambahkan
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal Koneksi DB!", e);

        }
    }
}
