package com.ahmet.completejavadevelopercourse.interfacesabstractclasses;

public abstract class AbstractExample {

    private int time;
    private static int count;
    private final int complex = 19;
    public static String TITLE = "AHMET";

    abstract void test();

    void concrete() {
        System.out.println(time + ": " + count + ": " + complex + ": " + TITLE);
    }
}
