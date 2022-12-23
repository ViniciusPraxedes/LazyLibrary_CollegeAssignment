package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;
public class InputValidator {
    Scanner sc = new Scanner(System.in);
    public String stringValidator(String item) {
        do {
            item = sc.nextLine();
            if (item.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        } while (true);
        return item;
    }
}

