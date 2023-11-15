package com.ahmet.completejavadeveloper;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray {

    public static void main(String[] args) {
        int[] array = getIntegers(5);
        System.out.println(Arrays.toString(array));
        reverse(array);
        System.out.println(Arrays.toString(array));
    }

    private static int[] getIntegers(int capacity) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[capacity];
        System.out.println("Enter " + capacity + " integer values:\r");
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();
        return array;
    }

    private static void reverse(int[] array) {
        int maxIndex = array.length - 1;
        int halfIndex = array.length / 2;
        for (int i = 0; i < halfIndex; i++) {
            int temp = array[i];
            array[i] = array[maxIndex - i];
            array[maxIndex - i] = temp;
        }
    }
}
