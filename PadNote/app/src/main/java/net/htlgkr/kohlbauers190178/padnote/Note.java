package net.htlgkr.kohlbauers190178.padnote;

public class Note {
    String title;
    String description;
    String text;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }

    public Note(String title, String description, String text) {
        this.title = title;
        this.description = description;
        this.text = text;
    }
}
