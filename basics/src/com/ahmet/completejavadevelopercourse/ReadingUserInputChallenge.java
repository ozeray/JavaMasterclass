package com.ahmet.completejavadevelopercourse;

import java.util.Scanner;

public class ReadingUserInputChallenge {

    public static void main(String[] args) {
        int counter = 1, sum = 0;
        Scanner scanner = new Scanner(System.in);

        while (counter < 11) {
            System.out.println("Enter number #" + counter);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                sum += input;
                counter++;
            } else {
                System.out.println("Invalid Number");
                break;
            }

            // if some string is to be entered:
            // scanner.nextLine();
            // String name = scanner.nextLine();
            // System.out.println(name);
        }

        System.out.println("Sum so far is: " + sum);

        scanner.close();
    }
}
