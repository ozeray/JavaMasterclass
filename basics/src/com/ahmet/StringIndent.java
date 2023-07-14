package com.ahmet;

public class StringIndent {

    public static void main(String[] args) {
        String s = "Hello World!\nAhmet Özer";
        s = s.indent(4);
        System.out.println(s);

        System.out.println("done");
        s = s.indent(-10);
        System.out.println(s);
        System.out.println("done");
    }
}
