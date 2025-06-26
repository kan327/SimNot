package controllers;

import java.util.List;
import models.Catatan;
import views.HomeView;

public class HomeController {
  private HomeView view;

  public HomeController(HomeView view) {
    this.view = view;
  }


  public static List<Catatan> getAllCatatan() {
    return Catatan.getAll();
  }

  
}
