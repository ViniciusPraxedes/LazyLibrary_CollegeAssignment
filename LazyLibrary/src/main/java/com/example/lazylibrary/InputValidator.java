package com.example.lazylibrary;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    Scanner sc = new Scanner(System.in);
    public int inputValidator(int input, String message){
        do {
            try{
                System.out.println(message);
                input = sc.nextInt();
                if (input == (int)input){
                    break;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input");
            }
            sc.nextLine();
        } while (true);
        return input;
    }
}
