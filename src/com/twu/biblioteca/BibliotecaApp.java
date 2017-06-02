package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaCore bibliotecaCore = new BibliotecaCore();
        System.out.println(bibliotecaCore.run());
        System.out.println("Please enter your option:");
        Scanner scanner = new Scanner(System.in);
        while (bibliotecaCore.getState() != State.QUIT){
            String userInput = scanner.nextLine();
            String message = bibliotecaCore.handleUserInput(userInput);
            System.out.println(message);
        }
    }

}
