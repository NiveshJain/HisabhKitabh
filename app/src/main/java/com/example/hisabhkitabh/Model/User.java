package com.example.hisabhkitabh.Model;

/**
 * Created by LNJPC on 23-02-2016.
 */
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private long contactNumber;

    public User() {
    }

    public User( String firstName, String lastName, long contactNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public int getUserId() {
        return userId;
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

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }


}
