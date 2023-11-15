package com.ahmet.completejavadeveloper;

public class LargestPrime {

    public static void main(String[] args) {
        System.out.println(getLargestPrime(15));
        System.out.println(getLargestPrime(7));
        System.out.println(getLargestPrime(1));
        System.out.println(getLargestPrime(0));
        System.out.println(getLargestPrime(-3));
        System.out.println(getLargestPrime(30));
        System.out.println(getLargestPrime(21));
        System.out.println(getLargestPrime(217));
        System.out.println(getLargestPrime(45));
    }

    public static int getLargestPrime(int number) {
        if (number < 2) {
            return -1;
        }

        for (int i = number; i > 1; i--) {
            if (number % i == 0) {
                boolean isPrime = true;
                for (int j = i / 2; j > 1; j--) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    return i;
                }
            }
            if (i == number) {
                i = number / 2 + 1;
            }
        }

        return -1;
    }
}
