package com.ahmet;

public class MethodLocalInterfaces {
    public static void main(String[] args) {
        interface Test {
            void m();
        }

        Test t = () -> System.out.println("mmmmmmmmmm");

        t.m();
    }
}
