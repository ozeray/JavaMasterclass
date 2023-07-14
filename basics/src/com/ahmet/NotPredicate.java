package com.ahmet;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class NotPredicate {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Ali", "Veli", "\n", " ", "Asda", "\n \n");
        List<String> withoutBlank = list.stream().filter(Predicate.not(String::isBlank)).toList();
        System.out.println(withoutBlank);
    }
}
