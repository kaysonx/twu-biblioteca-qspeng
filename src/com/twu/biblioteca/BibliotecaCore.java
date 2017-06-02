package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;


public class BibliotecaCore {

    private List<Book> bookList;

    public BibliotecaCore() {
        bookList = new ArrayList<Book>();
        bookList.add(new Book());
        bookList.add(new Book());
        bookList.add(new Book());
        bookList.add(new Book());
        bookList.add(new Book());
        bookList.add(new Book());
    }

    public String getWelcomeMessage() {
        return "Welcome to the Bibliteca!";
    }

    public List<Book> getListBooks() {
        return bookList;
    }
}
