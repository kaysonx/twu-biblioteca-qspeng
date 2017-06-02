package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;

import java.util.*;
import java.util.stream.Collectors;


public class BibliotecaCore {

    private List<Book> bookList;
    private List<String> mainMenu;
    private State state;

    public BibliotecaCore() {
        bookList = new ArrayList<Book>();
        bookList.add(new Book("jlan,jiang", "2014.08", "ASP.NET MVC"));
        bookList.add(new Book("Keith,J", "2011.04", "JavaScript DOM"));
        bookList.add(new Book("dbo,lin", "2013.12", " HTML5+CSS3"));
        bookList.add(new Book("qsepng", "2017.06", "testBook"));

        mainMenu = new ArrayList<String>();
        mainMenu.add("List Books");
        mainMenu.add("Checkout Book");
        mainMenu.add("Return Book");
        mainMenu.add("Quit");

        state = State.MAIN;
    }

    public String getWelcomeMessage() {
        return "Welcome to the Bibliteca!";
    }

    public List<Book> getListBooks() {
        return bookList.stream().filter(book -> !book.isCheckout()).collect(Collectors.toList());
    }

    public List<String> getMenu() {
        return mainMenu;
    }

    public Map<String, Object> order(String order) {
        Map<String, Object> orderMap = new HashMap<>();

        switch (order) {
            case "0":
                orderMap.put("message", getListBooks());
                this.state = State.LISTBOOK;
                break;
            case "1":
                orderMap.put("message", "Please input the book name to checkout:");
                this.state = State.CHECKOUT;
                break;
            case "2":
                orderMap.put("message", "Please input the book name to return:");
                this.state = State.RETURN;
                break;
            case "3":
                orderMap.put("message", "bye!");
                this.state = State.QUIT;
                break;
            default:
                orderMap.put("message", "Select a valid option!");
                this.state = State.MAIN;
                break;
        }
        return orderMap;
    }

    public String checkout(final String bookName) {
        Optional<Book> findBook = bookList.stream().filter(book -> book.getName().equals(bookName)).findFirst();
        if (findBook.isPresent()) {
            findBook.get().setCheckout(true);
            return "Thank you! Enjoy the book";
        }
        return "That book is not available";
    }

    public String returnBook(String bookName) {
        Optional<Book> findBook = bookList.stream().filter(book -> book.getName().equals(bookName)).findFirst();
        if (findBook.isPresent()) {
            findBook.get().setCheckout(false);
            return "Thank you for returning the book";
        }
        return "That is not a valid book to return";
    }

    public boolean isExist(String bookName) {
        return bookList.stream().anyMatch(book -> book.getName().equals(bookName) && !book.isCheckout());
    }
}
