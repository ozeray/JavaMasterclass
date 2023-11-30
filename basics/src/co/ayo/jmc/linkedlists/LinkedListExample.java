package co.ayo.jmc.linkedlists;

import java.util.LinkedList;

public class LinkedListExample {

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(5);
        ll.add(0, 2);
        ll.addFirst(3);
        ll.addLast(14);
        ll.add(ll.size(), 45);
        ll.add(5, 25);
        ll.add(0, 33);
        ll.push(55);
        ll.add(155);
        ll.offer(66);
        ll.offerFirst(222);

        print(ll);

        System.out.println("Peek:" + ll.peek());
        System.out.println("Pop:" + ll.pop());
        System.out.println("Poll:" + ll.poll());
        System.out.println("Poll last:" + ll.pollLast());
        System.out.println("Get last:" + ll.getLast());
        System.out.println("Get last:" + ll.getLast());
        System.out.println("Get first:" + ll.getFirst());
        System.out.println("Get first:" + ll.getFirst());
    }

    private static void print(LinkedList<Integer> linkedList) {
        for (Integer i : linkedList) {
            System.out.println(i);
        }
    }
}
