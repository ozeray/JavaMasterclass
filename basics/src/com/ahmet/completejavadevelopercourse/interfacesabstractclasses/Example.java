package com.ahmet.completejavadevelopercourse.interfacesabstractclasses;

public class Example implements IExample {
    @Override
    public void test() {
        System.out.println(time + ": " + count);
    }
}
