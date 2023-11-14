package com.ahmet.completejavadevelopercourse.collections.maps;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record Course(String courseId, String name, String subject) {
}

record Purchase(String courseId, int studentId, double price, int year, int dayOfYear) {
    public LocalDate purchaseDate() {
        return LocalDate.ofYearDay(year, dayOfYear);
    }
}

public class Student {

    public static int lastId = 1;
    private final String name;
    private final int id;
    private final List<Course> courseList;

    public Student(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
        this.id = lastId++;
    }

    public Student(String name, Course course) {
        this(name, new ArrayList<>(List.of(course)));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    @Override
    public String toString() {
        String[] courseNames = new String[courseList.size()];
        Arrays.setAll(courseNames, i -> courseList.get(i).name());
        return "[%d]: %s".formatted(id, String.join(", ", courseNames));
    }
}
