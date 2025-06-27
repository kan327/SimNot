package lib;

import java.sql.Timestamp;
import java.util.prefs.Preferences;
import models.User;

public class UserStorage {
    private static final Preferences prefs = Preferences.userRoot().node("MyApp");

    // Menyimpan data ke local storage
    public static void setLocalUser(int userId, String email, String password, String userName, Timestamp dateTime) {
        prefs.putInt("user_id", userId);
        prefs.put("email", email);
        prefs.put("password", password);
        prefs.put("user_name", userName);
        prefs.putLong("date_time", dateTime.getTime()); // disimpan sebagai long millis
    }

    // Mengambil data dari local storage
    public static User getLocalUser() {
        int userId = prefs.getInt("user_id", -1); // -1 = default jika belum ada
        String email = prefs.get("email", null);
        String password = prefs.get("password", null);
        String userName = prefs.get("user_name", null);
        long dateTimeMillis = prefs.getLong("date_time", 0);
        Timestamp dateTime = new Timestamp(dateTimeMillis);

        if (userId == -1 || email == null) return null; // belum tersimpan
        return new User(userId, email, password, userName, dateTime);
    }

    // Menghapus data (optional)
    public static void clearLocalUser() {
        prefs.remove("user_id");
        prefs.remove("email");
        prefs.remove("password");
        prefs.remove("user_name");
        prefs.remove("date_time");
    }
}
