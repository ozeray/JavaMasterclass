package com.ahmet;

import java.util.Arrays;

public class MoveZerosToEndOfArray {

    public static void main(String[] args) {

//        int[] arr = {0, 12, 0, 65, 44, 67, 0, 1};
//        int[] arr = {1, 2, 0, 0, 0, 3, 6};
        int[] arr = {0, 0, 0, 0, 0, 0, 0, 3, 6};

        System.out.println(Arrays.toString(new MoveZerosToEndOfArray().zerosToEnd(arr)));
        System.out.println(Arrays.toString(new MoveZerosToEndOfArray().zerosToEnd2(arr))); // BEST. Space complexity is lower!

    }

    private int[] zerosToEnd(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must have elements");
        }

        int[] newArr = new int[arr.length];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                newArr[j] = arr[i];
                j++;
            }
        }

        return newArr;
    }

    private int[] zerosToEnd2(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must have elements");
        }

        for (int i = 0, j = 0; i < arr.length; i++) {
            if (arr[i] != 0 && arr[j] == 0) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
            if (arr[j] != 0) {
                j++;
            }
        }

        return arr;
    }
}
