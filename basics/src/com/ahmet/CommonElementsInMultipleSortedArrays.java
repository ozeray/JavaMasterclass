package com.ahmet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonElementsInMultipleSortedArrays {

    public static void main(String[] args) {
        Integer[][] arrays = {
                {1, 2, 3, 4, 5},
                {2, 4, 6, 8},
                {2, 3, 4, 7},
                {4, 5, 8, 9}
        };

        List<Integer> commonElements = findCommonElements(arrays);
        System.out.println("Common elements in the arrays: " + commonElements);
    }

    private static List<Integer> findCommonElements(Integer[][] arrays) {
        List<Integer> commonElements = Arrays.asList(arrays[0]);

        for (int i = 1; i < arrays.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int num : arrays[i]) {
                if (commonElements.contains(num)) {
                    list.add(num);
                }
            }
            commonElements = list;
        }
        return commonElements;
    }
}
