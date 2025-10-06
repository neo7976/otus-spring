package ru.dsobin.quiz.service;

import java.util.Scanner;

public class ConsoleIOService {

    private final Scanner scanner = new Scanner(System.in);

    public void print(String message) {
        System.out.println(message);
    }

    public String readLine() {
        return scanner.nextLine().trim();
    }
}