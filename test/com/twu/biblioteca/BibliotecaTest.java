package com.twu.biblioteca;


import com.twu.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BibliotecaTest {
    BibliotecaCore bibliotecaCore;

    @Before
    public void setUp() {
        bibliotecaCore = new BibliotecaCore();
    }

    @Test
    public void should_get_welcome_message() {
        assertEquals("Welcome to the Bibliteca!", bibliotecaCore.getWelcomeMessage());
    }

    @Test
    public void should_get_list_books() {
        List<Book> bookList = bibliotecaCore.getListBooks();
        assertNotNull(bookList);
        assertTrue(bookList.size() > 0);
    }

    @Test
    public void should_get_book_details() {
        List<Book> bookList = bibliotecaCore.getListBooks();
        assertNotNull(bookList.get(0).getAuthor());
        assertNotNull(bookList.get(0).getPublished());
    }

    @Test
    public void should_get_menu() {
        List<String> menuItems = bibliotecaCore.getMenu();
        assertNotNull(menuItems);
        assertTrue(menuItems.contains("List Books"));
    }

    @Test
    public void should_get_invalid_menu_option_when_invalid_order() {
        Map<String, Object> orderMap = bibliotecaCore.order("-1");
        assertEquals(orderMap.get("message"), "Select a valid option!");
    }

    @Test
    public void should_have_quit_option() {
        List<String> menuItems = bibliotecaCore.getMenu();
        assertNotNull(menuItems);
        assertTrue(menuItems.contains("Quit"));
    }

    @Test
    public void should_checkout() {
        String bookName = "ASP.NET MVC";
        bibliotecaCore.checkout(bookName);
        assertFalse(bibliotecaCore.isExist(bookName));
    }

    @Test
    public void should_get_checkout_success_message() {
        String bookName = "ASP.NET MVC";
        String checkoutMessage = bibliotecaCore.checkout(bookName);
        assertEquals(checkoutMessage, "Thank you! Enjoy the book");
    }

    @Test
    public void should_get_checkout_failed_message() {
        String bookName = "Error Book";
        String checkoutMessage = bibliotecaCore.checkout(bookName);
        assertEquals(checkoutMessage, "That book is not available");
    }

    @Test
    public void should_return_book() {
        String bookName = "ASP.NET MVC";
        bibliotecaCore.returnBook(bookName);
        assertTrue(bibliotecaCore.isExist(bookName));
    }

    @Test
    public void should_get_successful_message_when_return_book_success() {
        String bookName = "ASP.NET MVC";
        String returnBookMessage = bibliotecaCore.returnBook(bookName);
        assertEquals(returnBookMessage, "Thank you for returning the book");
    }

    @Test
    public void should_get_failed_message_when_return_book_fail() {
        String bookName = "ASP.NET";
        String returnBookMessage = bibliotecaCore.returnBook(bookName);
        assertEquals(returnBookMessage, "That is not a valid book to return");
    }

}
