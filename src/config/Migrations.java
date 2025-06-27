package config;
import java.sql.Connection;
import java.sql.Statement;

public class Migrations {
    public static void main(String[] args) {
        try (
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement()
            ) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
                System.out.println("Running migrations...");

            // users Migrations =========================================
            stmt.executeUpdate("DROP TABLE IF EXISTS user");
            String createTableSql = """
                CREATE TABLE user (
                    user_id INT PRIMARY KEY AUTO_INCREMENT ,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    password VARCHAR(255) NOT NULL,
                    user_name VARCHAR(100) NOT NULL, 
                    date_time DATETIME DEFAULT CURRENT_TIMESTAMP
                );
            """;
            System.out.println("Berhasil jalankan tabel user");
            stmt.executeUpdate(createTableSql);
            // ===========================================================

            // Catatan Migration =========================================
            stmt.executeUpdate("DROP TABLE IF EXISTS catatan");
            String createCatatan = """
                CREATE TABLE catatan (
                    catatan_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                    isi TEXT,
                    judul VARCHAR(100) NOT NULL, 
                    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    user_id INT NOT NULL,
                    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
                );
            """;
            System.out.println("Berhasil jalankan tabel catatan");
            stmt.executeUpdate(createCatatan);
            // ===========================================================

            // Menampilkan pesan sukses
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            System.out.println("Migrations berhasil dijalankan!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
