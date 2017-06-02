package com.twu.biblioteca;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BibliotecaTest {
    @Test
    public void should_get_welcome_message(){
        BibliotecaCore bibliotecaCore = new BibliotecaCore();
        assertEquals("Welcome to the Bibliteca!",bibliotecaCore.getWelcomeMessage());
    }
}
