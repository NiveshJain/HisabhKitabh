package com.example.hisabhkitabh.Model;

/**
 * Created by LNJPC on 24-02-2016.
 */
public class EventParticipants {

    private User lender ;
    private User borrower;
    private double amount ;


    public EventParticipants(User lender, User borrower, double amount) {
        this.lender = lender;
        this.borrower = borrower;
        this.amount = amount;

    }

    public User getLender() {
        return lender;
    }

    public void setLender(User lender) {
        this.lender = lender;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
