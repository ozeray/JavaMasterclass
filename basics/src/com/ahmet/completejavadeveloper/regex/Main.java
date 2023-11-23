package com.ahmet.completejavadeveloper.regex;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        System.out.println(format("%s %s", "Hello", "World"));

        String testString = "Anyone can Learn abc's, 123's, and any regular expression";
        String replacement = "(-)";
        String[] patterns = {"[abc]", "[123]", "[A]", "ab|bc", "a|b|c", "[a-z]", "[0-9]", "[A-Z]", "[a-zA-Z]",
        "[a-zA-Z]*", "[0-9]*", "[0-9]+", "[A-Z]*", "[0-9]{2}", "[a-zA-Z]*$", ".$", ".*$", "^.*", "^a", "^[aA]", "n$",
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

        String sentence = "I like B.M.W. motorcycles.";
        boolean matched = Pattern.matches("[A-Z].*[.]", sentence);
        System.out.println(matched + " : " + sentence);

        Pattern firstPattern = Pattern.compile("[A-Z].*[.]");
        var matcher = firstPattern.matcher(sentence);
        System.out.println(matcher.matches() + " : " + sentence);
        System.out.println(sentence.length());
        System.out.println(matcher.end());

        System.out.println(matcher.lookingAt() + " : " + sentence);
        System.out.println(matcher.end());

        Pattern secondPattern = Pattern.compile("[A-Z].*?[.]");
        var matcherReluctant = secondPattern.matcher(sentence);
        System.out.println(matcherReluctant.lookingAt() + " : " + sentence.substring(0, matcherReluctant.end()));
        System.out.println(matcherReluctant.end() );
        System.out.println("Group:" + matcherReluctant.group());

        System.out.println("Second match: " + matcherReluctant.find());
        System.out.println("Second matched string: " + sentence.substring(matcherReluctant.start(), matcherReluctant.end()));
        System.out.println("Group:" + matcherReluctant.group());

        matcherReluctant.reset();
        System.out.println("Second match after reset: " + matcherReluctant.find());
        System.out.println("Second matched string after reset: " + sentence.substring(matcherReluctant.start(), matcherReluctant.end()));
        System.out.println("Group:" + matcherReluctant.group());

        String htmlSnippet = """
                <H1>My Heading</H1>
                <h2>Sub-heading</h2>
                <p>This is a paragraph about something.</p>
                <p>This is another paragraph about something else.</p>
                <h3>Summary</h3>
                """;

//        Pattern headerPattern = Pattern.compile("<[hH](\\d)>(.*)</[hH]\\d>");
        Pattern headerPattern = Pattern.compile("<[hH](?<level>\\d)>(?<text>.*)</[hH]\\d>");
        Matcher headerMatcher = headerPattern.matcher(htmlSnippet);
        while (headerMatcher.find()) {
            System.out.println("group: " + headerMatcher.group());
            System.out.println("group0: " + headerMatcher.group(0));
            System.out.println("group1: " + headerMatcher.group(1));
            System.out.println(headerMatcher.group(1) + " " + headerMatcher.group(2));
            System.out.println(headerMatcher.group("level") + " " + headerMatcher.group("text"));
            System.out.println(headerMatcher.start("level") + " " + headerMatcher.end(2));
        }

        System.out.println("------------------------");
        headerMatcher.reset(); // Required, as find() method was used before
        headerMatcher.results().forEach(mr -> System.out.println(mr.group(1) + " " + mr.group(2)));

        String tabbedText = """
                header1\theader2\theader3
                1\t2\t3
                4\t5\t6
                """;
        tabbedText.lines().flatMap(l -> Pattern.compile("\\t").splitAsStream(l)).forEach(System.out::println);

        System.out.println("-------------------------");
        headerMatcher.reset();
        String updatedSnipped = headerMatcher.replaceFirst("First Header");
        System.out.println(updatedSnipped);
        System.out.println(headerMatcher.start() + " " + headerMatcher.end());
        System.out.println("Old header: " + headerMatcher.group(2));

        String tagReplaced = headerMatcher.replaceAll(mr -> "<em>" + mr.group(2) + "</em>");
        System.out.println(tagReplaced);

        // Change RegEx to use back reference:
        headerMatcher.usePattern(Pattern.compile("<([hH]\\d)>(.*)</\\1>"));

        headerMatcher.reset();
        System.out.println("-------------------");
        System.out.println("Using backreference:\n" + headerMatcher.replaceFirst("<em>$2</em>"));
        String tagReplacedWithBR = headerMatcher.replaceAll("<em>$2</em>");
        System.out.println(tagReplacedWithBR);

        headerMatcher.reset();
        System.out.println("-------------------------");
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while (headerMatcher.find()) {
            headerMatcher.appendReplacement(sb,
                    switch (headerMatcher.group(1).toLowerCase()) {
                        case "h1" -> "<head>$2</head>";
                        case "h2" -> "<em>$2</em>";
                        default -> "<$1>" + index++ + ". $2</$1>";
                    });
        }
        headerMatcher.appendTail(sb);
        System.out.println(sb);
    }

    private static String format(String string, String... args) {
        int index = 0;
        while (string.matches(".*%s.*")) {
            string = string.replaceFirst("%s", args[index++]);
        }
        return string;
    }
}
