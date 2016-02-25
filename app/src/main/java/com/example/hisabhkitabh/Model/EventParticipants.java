package com.example.hisabhkitabh.Model;

/**
 * Created by LNJPC on 24-02-2016.
 */
public class EventParticipants {

    private User lender ;
    private User borrower;
    private double amount ;
    private Event event ;

    public EventParticipants(User lender, User borrower, double amount, Event event) {
        this.lender = lender;
        this.borrower = borrower;
        this.amount = amount;
        this.event = event;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
