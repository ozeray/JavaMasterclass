package com.ahmet;

public class Constructors {

    public static void main(String[] args) {
        A a = new A("Test");

        System.out.println("-----------------");

        A a2 = new A(5);

        System.out.println("-----------------");

        B b = new B();

        System.out.println("-----------------");

        B b2 = new B("Test B");

        System.out.println("-----------------");

        B b4 = new B(7);

        System.out.println("-----------------");

        B b3 = new B(4d);

        System.out.println("-----------------");

        C c = new C();
    }
}

class A {

    A() {
        System.out.println("No-args const A called");
    }

    A(String s) {
        System.out.println("Str const A called: " + s);
    }

    A(int a) {
        this();
        System.out.println("Int const A called: " + a);
    }

    A(double d) {
        System.out.println("Double const A called: " + d);
    }
}

class B extends A {

    B() {
        System.out.println("No-args const B called");
    }

    B(String s) {
        System.out.println("Str const B called: " + s);
    }

    B(int i) {
        super(i);
        System.out.println("Int const B called: " + i);
    }

    B(double d) {
        super(d);
        System.out.println("Double const B called: " + d);
    }
}

class C extends B {

    C() {
        System.out.println("No-args const C called");
    }
}