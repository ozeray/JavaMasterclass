package com.ahmet.completejavadevelopercourse.linkedlists;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListExample2 {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
//        list.add("Sydney");
//        list.add("Melbourne");
//        list.add("Brisbane");
//        list.add("Perth");
//        list.add("Canberra");
//        list.add("Adelaide");
//        list.add("Darwin");
        addInOrder(list, "Sydney");
        addInOrder(list, "Melbourne");
        addInOrder(list, "Brisbane");
        addInOrder(list, "Perth");
        addInOrder(list, "Canberra");
        addInOrder(list, "Adelaide");
        addInOrder(list, "Darwin");

        printList(list);

        list.add(1, "Alice Springs");

        printList(list);

        list.remove(4);

        printList(list);
    }

    private static void printList(LinkedList<String> list) {
        for (String s : list) {
            System.out.println("Now visiting " + s);
        }
        System.out.println("==========================");

    }

    private static void addInOrder(LinkedList<String> list, String newCity) {
        ListIterator<String> iterator = list.listIterator();

        while (iterator.hasNext()) {
            int comparison = iterator.next().compareTo(newCity);
            if (comparison == 0) {
                System.out.println(newCity + " already included as a destination");
                return;
            } else if (comparison > 0) {
                iterator.previous();
                iterator.add(newCity);
                return;
            }  // move on to next city

        }

        // no intermediary entries to insert into
        iterator.add(newCity);
    }
}
