package com.ahmet.jmc.encoding;

import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset().aliases());
    }
}
