package tr.com.ahmet;

public class PreOrderedSinglyLinkedList extends SinglyLinkedList {

    public static void main(String[] args) {
        PreOrderedSinglyLinkedList sll = new PreOrderedSinglyLinkedList();
//        sll.head = new ListNode<>(1);
//        ListNode<Integer> a = new ListNode<>(1);
//        ListNode<Integer> b = new ListNode<>(1);
//        ListNode<Integer> c = new ListNode<>(1);
//        ListNode<Integer> d = new ListNode<>(2);
//        ListNode<Integer> e = new ListNode<>(2);
//        ListNode<Integer> f = new ListNode<>(2);
//        ListNode<Integer> g = new ListNode<>(2);
//        ListNode<Integer> h = new ListNode<>(3);
//        ListNode<Integer> i = new ListNode<>(3);
//        sll.head.setNext(a);
//        a.setNext(b);
//        b.setNext(c);
//        c.setNext(d);
//        d.setNext(e);
//        e.setNext(f);
//        f.setNext(g);
//        g.setNext(h);
//        h.setNext(i);
//
        sll.insertEnd(5);
        sll.insertEnd(7);
//
        sll.display();
//
//        sll.removeDuplicates();
//        sll.display();
//
//        sll.insert(5);
//        sll.display();
//
//        sll.insert(0);
//        sll.display();
//
        sll.insert(4);
        sll.display();

        sll.insert(6);
        sll.display();

        sll.insert(8);
        sll.display();

        sll.insert(1);
        sll.display();

        PreOrderedSinglyLinkedList sll2 = new PreOrderedSinglyLinkedList();
        sll2.insert(2);
        sll2.insert(3);
        sll2.display();

        PreOrderedSinglyLinkedList merged = new PreOrderedSinglyLinkedList();
        merged.head = merged.mergeTwoSortedLinkedLists(sll.head, sll2.head);
        merged.display();
    }

    private void removeDuplicates() {
        ListNode<Integer> current = head;
        while (current != null && current.getNext() != null) {
            if (current.getData().equals(current.getNext().getData())) {
                current.setNext(current.getNext().getNext());
            } else {
                current = current.getNext();
            }
        }
    }

    private void insert(int data) {
        ListNode<Integer> newNode = new ListNode<>(data);
        if (head == null) {
            head = newNode;
            return;
        }

        if (head.getData().compareTo(data) > 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            ListNode<Integer> current = head;
            while (current.getNext() != null &&
                   current.getData().compareTo(data) < 0 &&
                   current.getNext().getData().compareTo(data) < 0) {

                current = current.getNext();
            }
            ListNode<Integer> next = current.getNext();
            current.setNext(newNode);
            newNode.setNext(next);
        }
    }

    private ListNode<Integer> mergeTwoSortedLinkedLists(ListNode<Integer> h1, ListNode<Integer> h2) {
        ListNode<Integer> merged = new ListNode<>(0);
        ListNode<Integer> tail = merged;
        while (h1 != null && h2 != null) {
            if (h1.getData().compareTo(h2.getData()) > 0) {
                tail.setNext(h2);
                h2 = h2.getNext();
            } else {
                tail.setNext(h1);
                h1 = h1.getNext();
            }
            tail = tail.getNext();
        }
        if (h1 == null) {
            tail.setNext(h2);
        } else {
            tail.setNext(h1);
        }
        return merged.getNext();

    }
}
