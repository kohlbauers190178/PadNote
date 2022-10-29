package net.htlgkr.kohlbauers190178.padnote;

public class NoteModel {
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

    public NoteModel(String title, String description, String text) {
        this.title = title;
        this.description = description;
        this.text = text;
    }
}
