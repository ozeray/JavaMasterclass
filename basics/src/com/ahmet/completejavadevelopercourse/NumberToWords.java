package com.ahmet.completejavadevelopercourse;

public class NumberToWords {

    public static void main(String[] args) {
        numberToWords(-11);
        numberToWords(1);
        numberToWords(123);
        numberToWords(1010);
        numberToWords(1000);
    }

    public static void numberToWords(int number) {
        if (number < 0) {
            System.out.println("Invalid Value");
        }

        int reverse = reverse(number);
        int zerosCount = number == 0 ? 1 : getDigitCount(number) - getDigitCount(reverse);

        while (reverse > 0) {
            int digit = reverse % 10;
            switch (digit) {
                case 0:
                    System.out.println("Zero");
                    break;
                case 1:
                    System.out.println("One");
                    break;
                case 2:
                    System.out.println("Two");
                    break;
                case 3:
                    System.out.println("Three");
                    break;
                case 4:
                    System.out.println("Four");
                    break;
                case 5:
                    System.out.println("Five");
                    break;
                case 6:
                    System.out.println("Six");
                    break;
                case 7:
                    System.out.println("Seven");
                    break;
                case 8:
                    System.out.println("Eight");
                    break;
                case 9:
                    System.out.println("Nine");
                    break;
            }
            reverse /= 10;
        }

        while (zerosCount > 0) {
            System.out.println("Zero");
            zerosCount--;
        }

    }

    public static int getDigitCount(int number) {
        if (number < 0) {
            return -1;
        }

        int digitCount = 0;

        do {
            digitCount++;
            number /= 10;
        } while (number > 0);

        return digitCount;
    }

    public static int reverse(int number) {
        int reverse = 0;
        int absolute = Math.abs(number);
        int sign = number < 0 ? -1 : 1;

        while (absolute > 0) {
            int leastSignificantDigit = absolute % 10;
            reverse *= 10;
            reverse += leastSignificantDigit;
            absolute /= 10;
        }
        return sign * reverse;
    }
}
