package com.db.dprice.kisapp.resview;

public class Note {

    private long id;

    private String text;

    private String path;

    private String date;

    public Note(final long id, final String name, final String path, final String date) {
        this.id = id;
        this.text = name;
        this.path = path;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return text;
    }

    public void setName(final String name) {
        this.text = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String name) {
        this.path = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String name) {
        this.date = name;
    }
}
