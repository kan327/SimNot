package controllers;

import models.Catatan;
import views.DetailNoteView;

public class DetailNoteController {
  private DetailNoteView view;

  public DetailNoteController(DetailNoteView view) {
    this.view = view;
  }

  public static Catatan initData(int id) {
    return Catatan.getById(id);
  }
}
