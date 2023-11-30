package com.ahmet.jmc;

public class DigitSum {
    public static void main(String[] args) {
        System.out.println(sum(125));
        System.out.println(sum(-125));
        System.out.println(sum(1025));
    }

    private static int sum(int number) {
        if (number < 10) {
            return -1;
        }

        int sum = 0;

        while (number > 0) {
            int leastSignificantDigit = number % 10;
            sum += leastSignificantDigit;
            number /= 10;
        }

        return sum;
    }
}
