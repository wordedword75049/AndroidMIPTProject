package com.db.dprice.kisapp.database;

public class Person {

    private long id;

    private String text;

    private String path;

    private String date;

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

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", path='" + path + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
