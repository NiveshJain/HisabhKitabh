package com.example.hisabhkitabh.Model;

import java.util.Date;

/**
 * Created by LNJPC on 24-02-2016.
 */
public class Event {

    private Date date ;
    private String description ;
    private int event_id ;

    public Event(Date date, String description, int event_id) {
        this.date = date;
        this.description = description;
        this.event_id = event_id;
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

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }


}
