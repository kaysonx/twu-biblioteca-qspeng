package com.twu.biblioteca.model;

import java.util.Date;

public class Book {
    private String Author;
    private Date Published;
    private String Name;

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Date getPublished() {
        return Published;
    }

    public void setPublished(Date published) {
        Published = published;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

