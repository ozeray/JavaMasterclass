package com.ahmet.completejavadeveloper.abstractclasses;

public class AbstractClassChallenge {

    public static void main(String[] args) {
        ListItem item1 = new Node("Ahmet");
        ListItem item2 = new Node("Mehmet");
        ListItem item3 = new Node("Aaaa");
        ListItem item4 = new Node("Ali");
        ListItem item5 = new Node("Mustafa");
        ListItem item6 = new Node(",,,,");

        MyLinkedList list = new MyLinkedList(null);
        list.addItem(item1);
        list.removeItem(item1);

//        list.addItem(item2);
        list.addItem(item2);
        list.addItem(item1);
        list.addItem(item3);
//        list.addItem(item6);
        list.addItem(item4);
        list.addItem(item5);

        list.traverse(list.getRoot());

        list.removeItem(item1);
        list.removeItem(item4);
        list.removeItem(item2);
        list.removeItem(item3);

        list.traverse(list.getRoot());

        String data = "Veli Nagihan Emre Çağlar Mehmet Ahmet Aaaa";
        for (String s : data.split(" ")) {
            list.addItem(new Node(s));
        }

        list.traverse(list.getRoot());

        BinarySearchTree st = new BinarySearchTree(null);
        data = "5 7 3 9 8 2 1 0 4 6";
//        data = "Burhan Veli Nagihan Emre Çağlar Mehmet Ahmet Aaaa";
        for (String s : data.split(" ")) {
            st.addItem(new Node(s));
        }
        System.out.println("==================");
        st.traverse(st.getRoot());
        System.out.println("==================");
    }
}
