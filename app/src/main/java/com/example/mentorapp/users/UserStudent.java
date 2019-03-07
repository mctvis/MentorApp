package com.example.mentorapp.users;

public class UserStudent {
    private String userName;
    private String email;
    private String password;
    private String subjectInterested;
    private String contactNumber;


    public UserStudent(String userName, String email,String password,String subjectInterested,String contactNumber) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.subjectInterested = subjectInterested;
        this.contactNumber = contactNumber;
    }

    public String getUserName(){
        return userName;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getSubjectInterested(){
        return subjectInterested;
    }
    public String getContactNumber(){
        return contactNumber;
    }
}
