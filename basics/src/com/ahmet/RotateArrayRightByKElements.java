package com.ahmet;

import java.util.Arrays;

public class RotateArrayRightByKElements {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        System.out.println("Rotate right whole: " + Arrays.toString(rotated(arr, arr.length - 1)));
        System.out.println("Rotate right by 2 elements: " + Arrays.toString(rotated(arr, 2)));
    }

    private static int[] rotated(int[] arr, int num) {
        int length = arr.length;
        int[] newArr = new int[length];
        int normalizedNum = num % length;

        for (int i = 0; i < normalizedNum; i++) {
            newArr[i] = arr[length - i - 1];
        }
        System.out.println(Arrays.toString(newArr));
        System.arraycopy(arr, 0, newArr, normalizedNum, length - num);
        return newArr;
    }
}
