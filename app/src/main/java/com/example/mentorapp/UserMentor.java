package com.example.mentorapp;

public class UserMentor {
  public String username,email,password;

    public  UserMentor(){

    }
    public UserMentor(String display_name, String email, String password) {
        this.username = display_name;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
