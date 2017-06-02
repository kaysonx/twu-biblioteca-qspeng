package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;

import java.util.*;
import java.util.stream.Collectors;


public class BibliotecaCore {

    private List<Book> bookList;
    private List<String> mainMenu;

    public State getState() {
        return state;
    }

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

    public String order(String order) {
        Map<String, Object> orderMap = new HashMap<>();
        switch (order) {
            case "0":
                this.state = State.LISTBOOK;
                return getFormatListBooks();
            case "1":
                this.state = State.CHECKOUT;
                return "Please input the book name to checkout:";
            case "2":
                this.state = State.RETURN;
                return "Please input the book name to return:";
            case "3":
                this.state = State.QUIT;
                return "bye";
            default:
                this.state = State.MAIN;
                return "Select a valid option!";
        }
    }

    public String handleUserInput(String userInput) {
        if (state == State.CHECKOUT) {
            return checkout(userInput);
        }
        if (state == State.RETURN) {
            return returnBook(userInput);
        }
        return order(userInput);
    }

    public String checkout(final String bookName) {
        Optional<Book> findBook = bookList.stream().filter(book -> book.getName().equals(bookName)).findFirst();
        if (findBook.isPresent()) {
            findBook.get().setCheckout(true);
            state = State.MAIN;
            return "Thank you! Enjoy the book";
        }
        return "That book is not available";
    }

    public String returnBook(String bookName) {
        Optional<Book> findBook = bookList.stream().filter(book -> book.getName().equals(bookName)).findFirst();
        if (findBook.isPresent()) {
            findBook.get().setCheckout(false);
            state = State.MAIN;
            return "Thank you for returning the book";
        }
        return "That is not a valid book to return";
    }

    public boolean isExist(String bookName) {
        return bookList.stream().anyMatch(book -> book.getName().equals(bookName) && !book.isCheckout());
    }

    public String getFormatListBooks() {
        List<Book> bookList = getListBooks();
        return bookList.stream().map(book -> book.toString()).collect(Collectors.joining("\n\r"));
    }
}
