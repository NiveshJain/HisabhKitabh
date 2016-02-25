package com.example.hisabhkitabh.Model;

/**
 * Created by LNJPC on 23-02-2016.
 */
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String contactNumber;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String contactNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


}
