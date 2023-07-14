package com.ahmet;

import java.util.Arrays;
import java.util.List;

public class CollectionToArray {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Ali", "Veli");
        String[] strArr = list.toArray(String[]::new);
        System.out.println(Arrays.toString(strArr));
    }
}
