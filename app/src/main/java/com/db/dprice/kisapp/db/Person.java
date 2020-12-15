package com.db.dprice.kisapp.db;

public class Person {

    private long id;

    private String text;

    private String path;

    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(final String name) {
        this.data = name;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", path='" + path + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
