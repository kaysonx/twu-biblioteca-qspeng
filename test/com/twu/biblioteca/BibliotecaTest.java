package com.twu.biblioteca;


import com.twu.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    public void should_get_book_details(){
        List<Book> bookList = bibliotecaCore.getListBooks();
        assertNotNull(bookList.get(0).getAuthor());
        assertNotNull(bookList.get(0).getPublished());
    }
}
