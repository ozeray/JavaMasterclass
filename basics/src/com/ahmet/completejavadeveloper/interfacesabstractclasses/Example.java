package com.ahmet.completejavadeveloper.interfacesabstractclasses;

public class Example implements IExample {
    @Override
    public void test() {
        System.out.println(time + ": " + count);
    }
}
