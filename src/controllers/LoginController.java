package controllers;

import models.User;
import views.LoginView;

public class LoginController {
  private LoginView view;

  public LoginController(LoginView view) {
    this.view = view;
  }

  public static User login(String email, String password) {
    
    User user = User.login(email, password);
    return user;
  }
}
