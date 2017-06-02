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
//        while (true){
//            String userInput = scanner.next();
//            if(state == State.ORDER){
//                int order = Integer.parseInt(userInput);
//            }else{
//
//            }
//
//        }
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
