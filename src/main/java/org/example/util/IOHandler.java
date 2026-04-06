package org.example.util;

import java.util.Scanner;

public class IOHandler {

    private final Scanner scanner;

    public IOHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readText() {
        return scanner.nextLine();
    }

    public void print(String message) {
        System.out.println(message);
    }

}
