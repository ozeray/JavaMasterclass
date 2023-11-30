package co.ayo.dsa;

import java.util.Arrays;

public class ArrayReverser {

    public static void main(String[] args) {
        ArrayReverser arrayReverser = new ArrayReverser();

        int[] arr0 = {-1, 2, 11, 5, 10, 7, 8};
        System.out.println(Arrays.toString(arr0));
        arrayReverser.reverse2(arr0);
        System.out.println(Arrays.toString(arr0));

        int[] arr = {2, 11, 5, 10, 7, 8};
        System.out.println(Arrays.toString(arr));
        arrayReverser.reverse2(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {2, -11, 10, 7, 8};
        System.out.println(Arrays.toString(arr2));
        arrayReverser.reverse2(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {2};
        System.out.println(Arrays.toString(arr3));
        arrayReverser.reverse2(arr3);
        System.out.println(Arrays.toString(arr3));

        int[] arr4 = {};
        System.out.println(Arrays.toString(arr4));
        arrayReverser.reverse2(arr4);
        System.out.println(Arrays.toString(arr4));


        int[] arr5 = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            arr5[i] = i;
        }

        long now = System.currentTimeMillis();
        arrayReverser.reverse(arr5);
        System.out.println("Reverse 1: " + (System.currentTimeMillis() - now) + " msecs"); // GOOD

        arr5 = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            arr5[i] = i;
        }

        now = System.currentTimeMillis();
        arrayReverser.reverse2(arr5);
        System.out.println("Reverse 2: " + (System.currentTimeMillis() - now) + " msecs"); // BEST


        now = System.currentTimeMillis();
        arrayReverser.reverseToNewArray(arr5);
        System.out.println("Reverse With New: " + (System.currentTimeMillis() - now) + " msecs"); // WORST
    }

    private void reverse(int[] arr) {
        int end = arr.length;
        int halfLength = end / 2;
        for (int i = 0; i < halfLength; i++) {
            int rightIndex = end - i - 1;
            int tmp = arr[rightIndex];
            arr[rightIndex] = arr[i];
            arr[i] = tmp;
        }
        /*
            2
            3
            1 + 3*(n/2+1) + 3*(n/2) = 3n + 4
            n/2(5) = 2.5n
            n/2(3) = 1.5n
            n/2(3) = 1.5n
            n/2(4)= 2n
            ---------------------------------------
            10.5n + 9 => O(n)
         */
    }

    private void reverse2(int[] arr) {
        for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
        }
        /*
            4
            n/2(3) = 1.5n
            n/2(3) = 1.5n
            n/2(3) = 1.5n
            n/2(5) = 2.5n
            n/2(4) = 2n
            ---------------------------------------
            9n + 4 => O(n)
         */
    }

    private int[] reverseToNewArray(int[] arr) {
        int size = arr.length;
        int[] rev = new int[size];

        for (int i = 0; i < size; i++) {
            rev[size - i - 1] = arr[i];
        }
        return rev;
    }
}
