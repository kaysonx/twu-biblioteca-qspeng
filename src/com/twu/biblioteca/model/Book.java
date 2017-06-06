package com.twu.biblioteca.model;

public class Book {
    private String Author;
    private String Published;
    private String Name;
    private boolean isCheckout;

    public boolean isCheckout() {
        return isCheckout;
    }

    public void setCheckout(boolean checkout) {
        isCheckout = checkout;
    }

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
        isCheckout = false;
    }

    @Override
    public String toString() {
        return "Author:" + Author + ", Published:" + Published + ", Name:" + Name;
    }
}

