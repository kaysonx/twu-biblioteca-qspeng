package com.twu.biblioteca;


import com.twu.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
    public void should_get_menu(){
        List<String> menuItems = bibliotecaCore.getMenu();
        assertNotNull(menuItems);
        assertTrue(menuItems.contains("List Books"));
    }

    @Test
    public void should_get_invalid_menu_option_when_invalid_order(){
        List<String> orderMessage = bibliotecaCore.order(-1);
        assertEquals(orderMessage.size(),1);
        assertTrue(orderMessage.get(0).equals("Select a valid option!"));
    }

    @Test
    public void should_have_quit_option(){
        List<String> menuItems = bibliotecaCore.getMenu();
        assertNotNull(menuItems);
        assertTrue(menuItems.contains("Quit"));
    }

    @Test
    public void should_checkout(){
        int sourceBooksCount = bibliotecaCore.getListBooks().size();
        String bookName = "ASP.NET MVC";
        bibliotecaCore.checkout(bookName);
        assertEquals(sourceBooksCount - 1,bibliotecaCore.getListBooks().size());
        assertFalse(bibliotecaCore.getListBooks().contains(bookName));

    }
}
