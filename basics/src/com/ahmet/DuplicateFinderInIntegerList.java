package com.ahmet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicateFinderInIntegerList {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 27, 28, 10, 20, 80, 80, 80);
        Set<Integer> set = new HashSet<>();
        list.stream().filter(x -> !set.add(x)).collect(Collectors.toSet()).forEach(System.out::println);
        //Not: list.stream().filter(x -> !set.add(x)).forEach(System.out::println); This will print 10 80 80!
    }
}
