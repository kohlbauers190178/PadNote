package net.htlgkr.kohlbauers190178.padnote.model;

import net.htlgkr.kohlbauers190178.padnote.util.MyTime;

public class Note {
    String title;
    String description;
    String text;
    long dateAndTime;
    boolean isDone;


    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }


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

    public long getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(long dateAndTime) {
        this.dateAndTime = dateAndTime;
    }


    public Note(String title, String description, String text) {
        this.title = title;
        this.description = description;
        this.text = text;
        this.dateAndTime = 0L;
        isDone = false;
    }
}
