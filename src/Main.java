import lib.UserStorage;
import models.User;
import views.LoginView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
        User user = UserStorage.getLocalUser();
        if (user == null) {
            new LoginView();
        } else {
            new views.HomeView();
        }
        });
    }
}
