package tr.com.ahmet.udemy.javathecompletejavadevelopercourse;

import java.util.Scanner;

public class InputAverageCalculator {

    public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }

    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);
        long sum = 0, avg = 0;
        int counter = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                sum += number;
                counter++;
            } else {
                break;
            }

            scanner.nextLine();
        }

        avg = Math.round((double) sum / counter);
        System.out.println("SUM = " + sum + " AVG = " + avg);

        scanner.close();
    }

}
