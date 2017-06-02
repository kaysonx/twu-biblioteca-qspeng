package com.twu.biblioteca.model;

import java.util.Date;

public class Book {
    private String Author;
    private String Published;
    private String Name;

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPublished() {
        return Published;
    }

    public void setPublished(String published) {
        Published = published;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Book(String author, String published, String name) {
        Author = author;
        Published = published;
        Name = name;
    }
}

