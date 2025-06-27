package controllers;

import java.util.List;

import lib.UserStorage;
import models.Catatan;
import models.User;
import views.HomeView;

public class HomeController {
  private HomeView view;

  public HomeController(HomeView view) {
    this.view = view;
  }

  public static List<Catatan> getAllCatatan() {
    User user = UserStorage.getLocalUser();

    if (user != null) {
      return Catatan.getAll(user.getUser_id());
    }
    
    return null;
  }

  public static boolean destroyCatatan(int id) {
    return Catatan.delete(id);
  }

  
}
