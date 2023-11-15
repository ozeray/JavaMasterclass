package com.ahmet.completejavadeveloper;

import java.util.ArrayList;

public class ArrayListExample {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Test");
        list.remove("asdad");
        if (list.contains("Test")) {
            System.out.println("Bingo");
        }
    }


}
