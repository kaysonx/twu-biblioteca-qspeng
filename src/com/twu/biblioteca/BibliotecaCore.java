package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;

import java.util.*;
import java.util.stream.Collectors;


public class BibliotecaCore {
    public static final String lineSeparator = new Properties(System.getProperties()).getProperty("line.separator");
    private List<Book> bookList;
    private List<String> mainMenu;
    private List<Movie> movieList;
    private List<User> userList;
    private User currentUser = null;
    private Map<String, String> HINTINFO;

    public State getState() {
        return state;
    }

    private State state;

    public BibliotecaCore() {
        initBook();
        initMovie();
        initUser();
        initMenu();
        initHintInfo();
        state = State.MAIN;

    }

    private void initHintInfo() {
        HINTINFO = new HashMap<>();
        HINTINFO.put("WELCOME","Welcome to the Bibliteca!");
        HINTINFO.put("OPTION_NOTE","Please enter your option:");

        HINTINFO.put("CHECKOUT_BOOK_NOTE","Please input the book name to checkoutBook:");
        HINTINFO.put("CHECKOUT_BOOK_SUCCESS","Thank you! Enjoy the book");
        HINTINFO.put("CHECKOUT_BOOK_FAILED","That book is not available");

        HINTINFO.put("RETURN_BOOK_NOTE","Please input the book name to return:");
        HINTINFO.put("RETURN_BOOK_SUCCESS","Thank you for returning the book");
        HINTINFO.put("RETURN_BOOK_FAILED","That is not a valid book to return");

        HINTINFO.put("CHECKOUT_MOVIE_NOTE","Please input the movie name to checkoutBook:");
        HINTINFO.put("CHECKOUT_MOVIE_SUCCESS", "Thank you! Enjoy the movie");
        HINTINFO.put("CHECKOUT_MOVIE_FAILED","That movie is not available");

        HINTINFO.put("LOGIN_NOTE","Please input the library number,user password(like xxx-xxxx,xxx) to login:");
        HINTINFO.put("LOGIN_SUCCESS","login successful!");
        HINTINFO.put("LOGIN_FAILED","login failed!");

        HINTINFO.put("INVALID_OPTION_NOTE","Select a valid option!");
        HINTINFO.put("QUIT_NOTE","bye");
    }

    private void initMenu() {
        mainMenu = new ArrayList<>();
        mainMenu.add("List Books");
        mainMenu.add("Checkout Book");
        mainMenu.add("Return Book");
        mainMenu.add("List Movies");
        mainMenu.add("Checkout Movies");
        mainMenu.add("Login");
        mainMenu.add("Quit");
    }

    private void initUser() {
        userList = new ArrayList<>();
        userList.add(new User("qspeng", "123-4567", "pwd"));
        userList.add(new User("test", "123-4568", "4567"));
        userList.add(new User("admin", "123-4569", "123"));
    }

    private void initMovie() {
        movieList = new ArrayList<>();
        movieList.add(new Movie("One", "2010", "Warner Bros", "6"));
        movieList.add(new Movie("Two", "2011", "20th Century Fox", "7"));
        movieList.add(new Movie("Three", "2012", "Universal   ", "9"));
        movieList.add(new Movie("Four", "2017", "Walt Disney", "10"));
    }

    private void initBook() {
        bookList = new ArrayList<>();
        bookList.add(new Book("jlan,jiang", "2014.08", "ASP.NET MVC"));
        bookList.add(new Book("Keith,J", "2011.04", "JavaScript DOM"));
        bookList.add(new Book("dbo,lin", "2013.12", "HTML5+CSS3"));
        bookList.add(new Book("qsepng", "2017.06", "testBook"));
    }

    public String getWelcomeMessage() {
        return HINTINFO.get("WELCOME");
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
                this.state = State.CHECKOUTBOOK;
                return HINTINFO.get("CHECKOUT_BOOK_NOTE");
            case "2":
                this.state = State.RETURNBOOK;
                return HINTINFO.get("RETURN_BOOK_NOTE");
            case "3":
                this.state = State.LISTMOVIE;
                return getFormatListMovies() + lineSeparator + getFormatTheMenu();
            case "4":
                this.state = State.CHECKOUTMOVIE;
                return HINTINFO.get("CHECKOUT_MOVIE_NOTE");
            case "5":
                this.state = State.LOGIN;
                return HINTINFO.get("LOGIN_NOTE");
            case "6":
                this.state = State.QUIT;
                return HINTINFO.get("QUIT_NOTE");
            case "7":
                this.state = State.MAIN;
                return currentUser.toString() + getFormatTheMenu();
            default:
                this.state = State.MAIN;
                return HINTINFO.get("INVALID_OPTION_NOTE");
        }
    }

    public String handleUserInput(String userInput) {
        if (state == State.CHECKOUTBOOK) {
            return checkoutBookService(userInput);
        }
        if (state == State.LOGIN) {
            return loginService(userInput);
        }
        if (state == State.CHECKOUTMOVIE) {
            return checkoutMovieService(userInput);
        }
        if (state == State.RETURNBOOK) {
            return returnBookService(userInput);
        }
        return order(userInput);
    }

    private String returnBookService(String userInput) {
        String returnBookMessage = returnBook(userInput);
        if (state == State.MAIN) {
            returnBookMessage = returnBookMessage + lineSeparator + getFormatTheMenu();
        }
        return returnBookMessage;
    }

    private String checkoutMovieService(String userInput) {
        if (currentUser == null) {
            state = State.MAIN;
            return "please login!" + lineSeparator + getFormatTheMenu();
        }
        String checkoutMessage = checkoutMovie(userInput);
        if (state == State.MAIN) {
            checkoutMessage = checkoutMessage + lineSeparator + getFormatTheMenu();
        }
        return checkoutMessage;
    }

    private String loginService(String userInput) {
        String userNumber = userInput.split(",")[0];
        String userPwd = userInput.split(",")[1];
        if (login(userNumber, userPwd)) {
            state = State.MAIN;
            return HINTINFO.get("LOGIN_SUCCESS") + lineSeparator + getFormatTheMenu();
        }
        return HINTINFO.get("LOGIN_FAILED");
    }

    private String checkoutBookService(String userInput) {
        String checkoutMessage = checkoutBook(userInput);
        if (state == State.MAIN) {
            checkoutMessage = checkoutMessage + lineSeparator + getFormatTheMenu();
        }
        return checkoutMessage;
    }

    public String checkoutBook(final String bookName) {
        Optional<Book> findBook = bookList.stream().filter(book -> book.getName().equals(bookName)).findFirst();
        if (findBook.isPresent()) {
            findBook.get().setCheckout(true);
            state = State.MAIN;
            return HINTINFO.get("CHECKOUT_BOOK_SUCCESS");
        }
        return HINTINFO.get("CHECKOUT_BOOK_FAILED");
    }

    public String returnBook(String bookName) {
        Optional<Book> findBook = bookList.stream().filter(book -> book.getName().equals(bookName)).findFirst();
        if (findBook.isPresent()) {
            findBook.get().setCheckout(false);
            state = State.MAIN;
            return HINTINFO.get("RETURN_BOOK_SUCCESS");
        }
        return HINTINFO.get("RETURN_BOOK_FAILED");
    }

    public boolean isExistBook(String bookName) {
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
        return getWelcomeMessage() + lineSeparator + getFormatTheMenu() + HINTINFO.get("OPTION_NOTE");
    }

    public List<Movie> getListMovies() {
        return movieList.stream().filter(movie -> !movie.isCheckout()).collect(Collectors.toList());
    }

    public String checkoutMovie(String movieName) {
        Optional<Movie> findMovie = movieList.stream().filter(movie -> movie.getName().equals(movieName)).findFirst();
        if (findMovie.isPresent()) {
            findMovie.get().setCheckout(true);
            state = State.MAIN;
            return HINTINFO.get("CHECKOUT_MOVIE_SUCCESS");
        }
        return HINTINFO.get("CHECKOUT_MOVIE_FAILED");
    }

    public boolean isExistMovie(String movieName) {
        return movieList.stream().anyMatch(movie -> movie.getName().equals(movieName) && !movie.isCheckout());
    }

    public boolean login(String userNumber, String userPwd) {
        Optional<User> loginUser = userList.stream().filter(user -> user.getLibraryNumber().equals(userNumber)).findFirst();
        if (!loginUser.isPresent()) {
            return false;
        }
        if (!loginUser.get().getPassword().equals(userPwd)) {
            return false;
        }
        this.currentUser = loginUser.get();
        this.mainMenu.add("My Info");
        return true;
    }

    public User getLoginUserInfo() {
        return currentUser;
    }

    public String getFormatListMovies() {
        List<Movie> movieList = getListMovies();
        return movieList.stream().map(movie -> movie.toString()).collect(Collectors.joining(lineSeparator));
    }
}
