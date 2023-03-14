package tr.com.ahmet.udemy.javathecompletejavadevelopercourse;

import java.util.Arrays;
import java.util.Scanner;

public class SortArrays {

    public static void main(String[] args) {
        int[] array = getIntegers(5);
        printArray(sortIntegers(array));
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

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element " + i + " contents " + array[i]);
        }
    }

    private static int[] sortIntegers(int[] array) {
//        int[] sortedArray = new int[array.length];
        int[] sortedArray;

        // Copy:
//        for (int i = 0; i < sortedArray.length; i++) {
//            sortedArray[i] = array[i];
//        }
        // OR:
        sortedArray = Arrays.copyOf(array, array.length);
        // OR:
//        System.arraycopy(array, 0, sortedArray, 0, array.length);

        boolean flag = true;
        int temp, counter = 1;
        while (flag) {
            flag = false;
            for (int i = 0; i < sortedArray.length - counter; i++) {
                if (sortedArray[i] < sortedArray[i + 1]) {
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = temp;
                    flag = true;
                }
            }
            // No need to check for already sorted smallest integers..
            counter++;
        }
        return sortedArray;
    }
}
