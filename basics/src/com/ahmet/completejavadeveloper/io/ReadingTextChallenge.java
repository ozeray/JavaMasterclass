package com.ahmet.completejavadeveloper.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReadingTextChallenge {

    public static void main(String[] args) {
//        Path path = Path.of("basics/files/ai.txt");
        Path path = Path.of("basics/files/pytest.txt");
        printTokens(path);
        printTokensWithoutPunctuation(path);
        printTokensWithoutPunctuationLongThanFiveChars(path);
        printTopTenTokens(path);

        printNumberOfWords(path);
        printTopTenTokensUsingBufferedReader(path);
    }

    private static void printTokens(Path path) {
        try {
            Scanner scanner = new Scanner(path);
            scanner.tokens().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTokensWithoutPunctuation(Path path) {
        try {
            System.out.println("----------------------------");
            String[] tokens = Files.readString(path)
                    .replaceAll("\\p{Punct}", "")
                    .split("\\p{javaWhitespace}");
            System.out.println(Arrays.toString(tokens));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTokensWithoutPunctuationLongThanFiveChars(Path path) {
        try {
            System.out.println("----------------------------");
            Scanner scanner = new Scanner(path);
            scanner.findAll("\\w{6,}\\p{javaWhitespace}")
                    .forEach(m -> System.out.println(m.group()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTopTenTokens(Path path) {
        try {
            System.out.println("------------------------------");
            Scanner scanner = new Scanner(path);
            scanner.tokens()
                    .map(token -> token.replaceAll("\\p{Punct}", ""))
                    .filter(s -> s.length() > 5)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                    .limit(10)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printNumberOfWords(Path path) {
        try {
            System.out.println("---------------------------------");
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");
            /*
            System.out.printf("%,d words in file%n",
                    br.lines()
//                            .flatMap(pattern::splitAsStream)
                            .flatMap(line -> Arrays.stream(line.split(pattern.toString())))
                            .count()); */

            System.out.printf("%,d words in file%n",
                    br.lines()
                            .mapToLong(line -> line.split(pattern.toString()).length)
                            .sum());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printTopTenTokensUsingBufferedReader(Path path) {
        try {
            System.out.println("------------------------------");
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
            br.lines()
                    .flatMap(s -> Pattern.compile("\\p{javaWhitespace}+").splitAsStream(s))
                    .map(w -> w.replaceAll("\\p{Punct}", ""))
                    .filter(w -> w.length() > 5)
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .limit(10)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
