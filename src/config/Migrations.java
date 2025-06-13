package config;
import java.sql.Connection;
import java.sql.Statement;

public class Migrations {
    public static void main(String[] args) {
        try (
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement()
            ) {
                System.out.println("Running migrations...");

            // users Migrations =========================================
            stmt.executeUpdate("DROP TABLE IF EXISTS your_table_name");
            String createTableSql = """
                CREATE TABLE your_table_name (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    dateTime DATETIME DEFAULT CURRENT_TIMESTAMP
                );
            """;
            System.out.println("Created table your_table_name !");
            stmt.executeUpdate(createTableSql);
            // ===========================================================

            // Menampilkan pesan sukses
            System.out.println("Migrations berhasil dijalankan!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
