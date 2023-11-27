package com.ahmet.completejavadeveloper.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class ScannerDemo {

    public static void main(String[] args) {
//        try (Scanner scanner = new Scanner(new File("basics/files/file.txt"))) {
        try (Scanner scanner = new Scanner(new File("basics/files/fixedWidth.txt"))) {
//            while (scanner.hasNextLine()) {
//                System.out.println(scanner.nextLine());
//            }
            //OR:
//            scanner.useDelimiter("$"); // end of line
//            scanner.tokens().forEach(System.out::println);

//            scanner.findAll("[A-Za-z]{10,}")
//                    .map(MatchResult::group)
//                    .distinct()
//                    .sorted()
//                    .forEach(System.out::println);

            var states = scanner.findAll("(.{15})(.{3})(.{12})(.{8})(.{2}).*")
                    .skip(1) // Ignore header line
                    .map(m -> m.group(5))
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
            System.out.println(Arrays.toString(states));

            scanner.reset();
            var departments = scanner.findAll("(.{15})(.{3})(.{12})(.{8})(.{2}).*")
                    .skip(1) // Ignore header line
                    .map(m -> m.group(3).trim())
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
            System.out.println(Arrays.toString(departments));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
