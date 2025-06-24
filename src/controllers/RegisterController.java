package controllers;

import models.User;
import views.RegisterView;

public class RegisterController {
  private RegisterView view;

  public RegisterController(RegisterView view) {
    this.view = view;
  }

  public static boolean registerAdd(String email, String password, String username) {
    boolean value = User.insert(email, password, username);

    return value;

    
  }
}
