package com.ahmet;

import java.util.Scanner;

public class SimpleMathHelper {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                Welcome to the simple math helper.
                What would you like to calculate?
                1. Sqrt
                2. Log
                3. Factorial
                4. Quit""");

            if (scanner.hasNextInt()) {
                int operation = scanner.nextInt();
                OperationType operationType = OperationType.SQRT;
                String numberRequest = "Enter the number to ";
                switch (operation) {
                    case 1 -> {
                        numberRequest += "sqrt:";
                    }
                    case 2 -> {
                        numberRequest += "log:";
                        operationType = OperationType.LOG;
                    }
                    case 3 -> {
                        numberRequest += "factorial:";
                        operationType = OperationType.FACTORIAL;
                    }
                    case 4 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        continue;
                    }
                }
                System.out.println(numberRequest);
                if (scanner.hasNextInt()) {
                    int number = scanner.nextInt();
                    if (number >= 0) {
                        System.out.println(calculateResult(operationType, number));
                    } else {
                        System.out.println("Please enter a non-negative number.");
                    }
                } else {
                    System.out.println("Invalid number.");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Invalid option.");
            }
            scanner.nextLine();
        }
    }

    private static double calculateResult(OperationType operationType, int number) {
        return switch (operationType) {
            case SQRT -> Math.sqrt(number);
            case LOG -> Math.log10(number);
            case FACTORIAL -> factorial(number);
        };
    }

    private static long factorial(long number) {
        if (number < 0) {
            return 0;
        } else if (number == 0) {
            return 1;
        } else {
            int result = 1;
            for (int i = 2; i <= number; i++) {
                result *= i;
            }
            return result;
        }
    }

    private enum OperationType {
        SQRT,
        LOG,
        FACTORIAL
    }
}
