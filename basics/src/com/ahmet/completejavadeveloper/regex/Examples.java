package com.ahmet.completejavadeveloper.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Examples {

    public static void main(String[] args) {
        String phoneList = """
                (800) 123-4567
                (800)123-4567
                (800) 123 4567
                (800 123-4567
                800) 123-4567
                800 123-4567
                800 123 4567
                8001234567
                """;

        Pattern phonePattern = Pattern.compile("\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}");
        Matcher phoneMatcher = phonePattern.matcher(phoneList);
        phoneMatcher.results().forEach(mr -> System.out.println(mr.group()));

        // Now use less restricted format:
        phoneMatcher.usePattern(Pattern.compile("\\(*[0-9]{3}\\)* [0-9]{3}-[0-9]{4}"));
        phoneMatcher.reset();
        phoneMatcher.results().forEach(mr -> System.out.println(mr.group()));

        // Now much less restricted format:
        phoneMatcher.usePattern(Pattern.compile("\\(*[0-9]{3}[)\\s]*[0-9]{3}-[0-9]{4}"));
        phoneMatcher.reset();
        phoneMatcher.results().forEach(mr -> System.out.println(mr.group()));

        // Now most flexible format:
        System.out.println("------------------------------------");
        phoneMatcher.usePattern(Pattern.compile("\\(*[0-9]{3}[)\\s-]*[0-9]{3}[\\s-]*[0-9]{4}"));
        phoneMatcher.reset();
        phoneMatcher.results().forEach(mr -> System.out.println(mr.group()));

        // Using \\d:
        System.out.println("------------------------------------");
        // Just an example. Try to be consistent inside the regex.
        phoneMatcher.usePattern(Pattern.compile("\\(*[0-9]{3}[)\\s-]*\\d{3}[\\s-]*\\p{Digit}{4}"));
        phoneMatcher.reset();
        phoneMatcher.results().forEach(mr -> System.out.println(mr.group()));

        System.out.println("-------------------------");
        String htmlSnippet = """
                <H1>My Heading</h1>
                <h2>Sub-heading</h2>
                <p>paragraph</p>
                <p>paragraph two</p>
                <p style="abc">paragraph two</p>
                <h3 id="third">Summary</h3>
                <br/>
                <p>Testing</p>
                <br>
                """;

        Pattern tagPattern = Pattern.compile("<(\\w+)[^>]*>([^\\v</>]*)(</\\1>)*");
        tagPattern.matcher(htmlSnippet).results().forEach(mr -> System.out.println("Full tag: " + mr.group(0)
                + "\n\tType:" + mr.group(1)
                + "\n\tText:" + mr.group(2)
                + "\n\tClosing:" + mr.group(3)));

        System.out.println("---------------------");
        Pattern tagPattern2 = Pattern.compile("<(\\w+)[^>]*>([^\\v</>]*)((?i)</\\1>)*");
        tagPattern2.matcher(htmlSnippet).results().forEach(mr -> System.out.println("Full tag: " + mr.group(0)
                + "\n\tType:" + mr.group(1)
                + "\n\tText:" + mr.group(2)
                + "\n\tClosing:" + mr.group(3)));

        System.out.println("---------------------");
        Pattern tagPattern3 = Pattern.compile("<(\\w+)[^>]*>([^\\v</>]*)(</\\1>)*",Pattern.CASE_INSENSITIVE);
        tagPattern3.matcher(htmlSnippet).results()
                .filter(mr -> mr.group(1).toLowerCase().startsWith("h"))
                .forEach(mr -> System.out.println("Full tag: " + mr.group(0)
                    + "\n\tType:" + mr.group(1)
                    + "\n\tText:" + mr.group(2)
                    + "\n\tClosing:" + mr.group(3)));


    }
}
