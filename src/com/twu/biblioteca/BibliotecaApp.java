package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaCore bibliotecaCore = new BibliotecaCore();
        System.out.println(bibliotecaCore.getWelcomeMessage());
        System.out.println(formatTheMenu(bibliotecaCore.getMenu()));
        System.out.println("Please enter your option:");
        Scanner scanner = new Scanner(System.in);
        while (bibliotecaCore.getState() != State.QUIT){
            String userInput = scanner.next();
            Object message = bibliotecaCore.handleUserInput(userInput);

        }
    }

    private static String formatTheMenu(List<String> menu){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < menu.size(); i++) {
            stringBuilder.append(i+"."+menu.get(i));
            stringBuilder.append("\n\r");
        }
        return stringBuilder.toString();
    }


}
