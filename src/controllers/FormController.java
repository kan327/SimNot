package controllers;

import lib.UserStorage;
import models.Catatan;
import models.User;
import views.FormView;

public class FormController {
  private FormView view;

  public FormController(FormView view) {
    this.view = view;
  }

  public static Catatan initData(int id) {
      return Catatan.getById(id);
  }

  public static boolean createNote(String title, String content) {
    User user = UserStorage.getLocalUser();
    if(title == null || content == null) {
      return false;
    }
    return Catatan.insert(user.getUser_id(), title, content);
  }

  public static boolean updateNote(int id, String title, String content) {
    if(title == null || content == null) {
      return false;
    }
    return Catatan.update(id, title, content);
  }
}
