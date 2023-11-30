package com.ahmet.jmc.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReadingText {
    public static void main(String[] args) {
        Path path = Path.of("basics/files/fixedWidth.txt");
        try {
            System.out.println(new String(Files.readAllBytes(path)));
            System.out.println("------------------------------");
            System.out.println(Files.readString(path));

            System.out.println("-----------------------------------");
            Pattern pattern = Pattern.compile("(.{15})(.{3})(.{12})(.{8})(.{2}).*");
            Set<String> departments = new TreeSet<>();
            Files.readAllLines(path).forEach(s -> {
                if (!s.startsWith("Name")) {
                    Matcher m = pattern.matcher(s);
                    if (m.matches()) {
                        departments.add(m.group(3).trim());
                    }
                }}
            );
            System.out.println(departments);

            try(var stringStream = Files.lines(path)) {
                var results = stringStream
                        .skip(1)
                        .map(pattern::matcher)
                        .filter(Matcher::matches)
                        .map(m -> m.group(3).trim())
                        .distinct()
                        .sorted()
                        .toArray(String[]::new);
                System.out.println(Arrays.toString(results));
            }

            System.out.println("---------------------------------");
            try(var stringStream = Files.lines(path)) {
                var employeesCountPerDepartment = stringStream
                        .skip(1)
                        .map(pattern::matcher)
                        .filter(Matcher::matches)
                        .collect(Collectors.groupingBy(m -> m.group(3).trim(), Collectors.counting()));
                employeesCountPerDepartment.entrySet().forEach(System.out::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
