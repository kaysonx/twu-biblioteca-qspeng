package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;

import java.util.*;
import java.util.stream.Collectors;


public class BibliotecaCore {
    public static final String lineSeparator = new Properties(System.getProperties()).getProperty("line.separator");
    private List<Book> bookList;
    private List<String> mainMenu;
    private List<Movie> movieList;

    public State getState() {
        return state;
    }

    private State state;

    public BibliotecaCore() {
        bookList = new ArrayList<>();
        bookList.add(new Book("jlan,jiang", "2014.08", "ASP.NET MVC"));
        bookList.add(new Book("Keith,J", "2011.04", "JavaScript DOM"));
        bookList.add(new Book("dbo,lin", "2013.12", "HTML5+CSS3"));
        bookList.add(new Book("qsepng", "2017.06", "testBook"));

        movieList = new ArrayList<>();
        movieList.add(new Movie("One","2010","Warner Bros","6"));
        movieList.add(new Movie("Two","2011","20th Century Fox","7"));
        movieList.add(new Movie("Three","2012","Universal   ","9"));
        movieList.add(new Movie("Four","2017","Walt Disney","10"));


        mainMenu = new ArrayList<>();
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
        switch (order) {
            case "0":
                this.state = State.LISTBOOK;
                return getFormatListBooks() + lineSeparator + getFormatTheMenu();
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
            String checkoutMessage = checkout(userInput);
            if (state == State.MAIN) {
                checkoutMessage = checkoutMessage + lineSeparator + getFormatTheMenu();
            }
            return checkoutMessage;
        }
        if (state == State.RETURN) {
            String returnBookMessage = returnBook(userInput);
            if (state == State.MAIN) {
                returnBookMessage = returnBookMessage + lineSeparator + getFormatTheMenu();
            }
            return returnBookMessage;
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
        return bookList.stream().map(book -> book.toString()).collect(Collectors.joining(lineSeparator));
    }

    private String getFormatTheMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mainMenu.size(); i++) {
            stringBuilder.append(i + "." + mainMenu.get(i));
            stringBuilder.append(lineSeparator);
        }
        return stringBuilder.toString();
    }

    public String run() {
        return getWelcomeMessage() + lineSeparator + getFormatTheMenu();
    }

    public List<Movie> getListMovies() {
        return movieList.stream().filter(movie -> !movie.isCheckout()).collect(Collectors.toList());
    }

    public String checkoutMovie(String movieName) {
        Optional<Movie> findMovie = movieList.stream().filter(movie -> movie.getName().equals(movieName)).findFirst();
        if (findMovie.isPresent()) {
            findMovie.get().setCheckout(true);
            state = State.MAIN;
            return "Thank you! Enjoy the movie";
        }
        return "That movie is not available";
    }

    public boolean isExistMovie(String movieName) {
        return movieList.stream().anyMatch(movie -> movie.getName().equals(movieName) && !movie.isCheckout());
    }

    public boolean login(String userName, String userPwd) {
        return false;
    }

    public String getUserInfo(String userName) {
        return null;
    }
}
