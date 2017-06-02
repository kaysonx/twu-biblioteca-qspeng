package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BibliotecaCore {

    private List<Book> bookList;
    private List<String> mainMenu;

    public BibliotecaCore() {
        bookList = new ArrayList<Book>();
        bookList.add(new Book("jlan,jiang", "2014.08", "ASP.NET MVC"));
        bookList.add(new Book("Keith,J", "2011.04", "JavaScript DOM"));
        bookList.add(new Book("dbo,lin", "2013.12", " HTML5+CSS3"));
        bookList.add(new Book("qsepng", "2017.06", "testBook"));

        mainMenu = new ArrayList<String>();
        mainMenu.add("List Books");
    }

    public String getWelcomeMessage() {
        return "Welcome to the Bibliteca!";
    }

    public List<Book> getListBooks() {
        return bookList;
    }

    public List<String> getMenu() {
        return mainMenu;
    }

    public List<String> order(int order) {
        List<String> orderMessage = new ArrayList<String>();
        if (mainMenu.size() < order) {
            orderMessage.add("Select a valid option!");
            return orderMessage;
        }
        return orderMessage;
    }
}
