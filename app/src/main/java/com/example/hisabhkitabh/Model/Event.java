package com.example.hisabhkitabh.Model;

import java.util.Date;

/**
 * Created by LNJPC on 24-02-2016.
 */
public class Event {

    private Date date ;
    private String description ;


    public Event(Date date, String description) {
        this.date = date;
        this.description = description;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
