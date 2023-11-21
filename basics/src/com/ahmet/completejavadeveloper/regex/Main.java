package com.ahmet.completejavadeveloper.regex;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Main {

    public static void main(String[] args) {
        System.out.println(format("%s %s", "Hello", "World"));

        String testString = "Anyone can Learn abc's, 123's, and any regular expression";
        String replacement = "(-)";
        String[] patterns = {"[abc]", "[123]", "[A]", "ab|bc", "a|b|c", "[a-z]", "[0-9]", "[A-Z]", "[a-zA-Z]",
        "[a-zA-Z]*", "[0-9]*", "[0-9]+", "[A-Z]*", "[0-9]{2}", "[a-zA-Z]*$", ".$", ".*$", "^.*", "^a", "n$",
        "^[a-zA-Z]", "^[a-zA-Z]*", "^[a-zA-Z]{3}", "[aA]ny", "[aA]ny\\b"};

        for (String pattern : patterns) {
            String output = testString.replaceFirst(pattern, replacement);
            System.out.println("Pattern: " + pattern + " => " + output);
        }

        String paragraph = """
                Double, double toil and trouble;
                Fire burn and caldron bubble.
                Fillet of a fenny snake,
                In the caldron boil and bake
                Eye of newt and toe of frog,
                Wool of bat and tongue of dog,
                Adder's fork and blind-worm's sting,
                Lizard's leg and howlet's wing,
                For a charm of powerful trouble,
                Like a hell-broth boil and bubble.
                """;

//        String[] lines = paragraph.split("\n");
        String[] lines = paragraph.split("\\R");
        System.out.println("Number of lines: " + lines.length);
        String[] words = paragraph.split("\\s");
        System.out.println("Number of words: " + words.length);

        System.out.println(paragraph.replaceAll("[a-zA-Z]+ble", "[GRUB]"));

        Scanner scanner = new Scanner(paragraph);
        System.out.println(scanner.delimiter());
        scanner.useDelimiter("\\R");
//        while (scanner.hasNext()) {
//            System.out.println(scanner.next());
//        } // OR:
//        scanner.tokens().forEach(System.out::println);
        // How many words in each line:
//        scanner.tokens().map(s -> s.split("\\s+").length).forEach(System.out::println); OR:
//        scanner.tokens().map(s -> Arrays.stream(s.split("\\s+")).count()).forEach(System.out::println);
        //Print words ending with 'ble':
        /*
        scanner.tokens()
                .map(s -> s.replaceAll("\\p{Punct}", "")) // matches() looks for entire matching!!! So remove punctuations first.
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .filter(s -> s.matches("[a-zA-Z]+ble")).forEach(System.out::println);
        */

//  Searches ONLY the first line (first token before the delimiter):
//        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));
//        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));
//        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));

        // Searches all tokens:
        System.out.println(Arrays.toString(scanner.findAll("[a-zA-Z]+ble").map(MatchResult::group).toArray()));

        scanner.close();

//        System.out.println("Hello, World!".matches("Hello, World!"));

//        String regEx = "[A-Z].*\\.";
//        String regEx = "[A-Z].*[.]";
//        String regEx = "[A-Z][a-zA-Z\\s\\p{Punct}]+\\p{Punct}";
        String regEx = "[A-Z]\\p{all}+\\p{Punct}";
        System.out.println("The bike is red.".matches(regEx));
        System.out.println("The bike is red, and has flat tires.".matches(regEx));
        System.out.println("I am a new student.".matches(regEx));
        System.out.println("Hello, friends and family: Welcome!".matches(regEx));
        System.out.println("How are you, Mary?".matches(regEx));
        System.out.println("i am a new student.".matches(regEx));
        System.out.println("I'm a new student.".matches(regEx));
        System.out.println("Am i a new student?".matches(regEx));
        System.out.println("I love being a new L.P.A. student!".matches(regEx));
    }

    private static String format(String string, String... args) {
        int index = 0;
        while (string.matches(".*%s.*")) {
            string = string.replaceFirst("%s", args[index++]);
        }
        return string;
    }
}
