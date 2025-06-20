package controllers;

import models.User;

public class TestJamesController {
  public static void main(String[] args) {
    
        // User.insert("jamesadetiya2@gmaoil", "2yesaya4110", "2james19");
        // User.insert("jamesadetiy1a@gmaoil", "yesaya41101", "james19");

        User user = User.getById(1);

        User.delete(2);



        if (user != null) {

            System.out.println("User ID: " + user.getUser_id());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Username: " + user.getUser_name());
            System.out.println("Date Time: " + user.getDate_time());
        } else {
            System.out.println("User not found.");
        } 
  }

  // ...
}
