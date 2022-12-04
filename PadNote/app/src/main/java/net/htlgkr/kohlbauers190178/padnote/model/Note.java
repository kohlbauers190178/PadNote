package net.htlgkr.kohlbauers190178.padnote.model;

import net.htlgkr.kohlbauers190178.padnote.util.MyTime;

public class Note {
    String title;
    String description;
    String text;
    long date;
    MyTime myTime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public MyTime getMyTime() {
        return myTime;
    }

    public void setMyTime(MyTime myTime) {
        this.myTime = myTime;
    }


    public Note(String title, String description, String text) {
        this.title = title;
        this.description = description;
        this.text = text;
        this.date = 0L;
        this.myTime = null;
    }
}
