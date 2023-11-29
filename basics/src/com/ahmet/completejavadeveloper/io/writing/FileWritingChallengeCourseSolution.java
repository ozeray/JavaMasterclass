package com.ahmet.completejavadeveloper.io.writing;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWritingChallengeCourseSolution {
    public static void main(String[] args) {
        Course jmc = new Course("JMC", "Java Masterclass");
        Course pymc = new Course("PYC", "Python Masterclass");

        String delimiter = "," + System.lineSeparator();
        String result = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(2)
                .map(Student::toJson)
                .collect(Collectors.joining(delimiter, "[", "]"));

        System.out.println(result);
    }
}
