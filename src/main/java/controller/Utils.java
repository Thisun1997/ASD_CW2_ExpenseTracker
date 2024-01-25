package controller;

import java.util.Scanner;

public class Utils {
    public static int getCommand(Scanner scanner, String message, int start, int end){
        int input;
        while (true){
            try {
                System.out.print(message);
                input = Integer.parseInt(scanner.nextLine());
                if(input >= start && input <= end) break;
                else {
                    System.out.println("Please enter a correct value.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return input;
    }

    public static void printSeparator(){
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
