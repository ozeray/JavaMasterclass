package co.ayo;

public class SinglyLinkedList {

    ListNode<Integer> head;

    static class ListNode<T> {
        private T data;
        private ListNode<T> next;

        public ListNode(T data) {
            this.data = data;
            this.next = null;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public ListNode<T> getNext() {
            return next;
        }

        public void setNext(ListNode<T> next) {
            this.next = next;
        }
    }

    void display() {
        ListNode<Integer> current = head;
        int counter = 0;
        while (current != null && counter < 10) {
            System.out.print(current.getData() + " --> ");
            current = current.getNext();
            counter++;
        }
        System.out.println("null");
    }

    private int length() {
        int count = 0;
        ListNode<Integer> current = head;
        while (current != null && count < 20) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    private void insertStart(Integer data) {
        ListNode<Integer> ln = new ListNode<>(data);
        ln.setNext(head);
        head = ln;
    }

    void insertEnd(Integer data) {
        ListNode<Integer> ln = new ListNode<>(data);
        if (head == null) {
            head = ln;
            return;
        }
        ListNode<Integer> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(ln);
    }

    private void insertAt(int position, Integer data) {
        if (position < 1 || head == null) {
            throw new IllegalArgumentException("Invalid position");
        }
        if (position == 1) {
            insertStart(data);
            return;
        }
        ListNode<Integer> ln = new ListNode<>(data);
        ListNode<Integer> current = head;
        int counter = 2;
        while (counter < position) {
            current = current.getNext();
            counter++;
        }
        ListNode<Integer> next = current.getNext();
        current.setNext(ln);
        ln.setNext(next);
    }

    private void deleteFirst() {
        if (head == null) {
            System.out.println("No element to delete");
            return;
        }
        ListNode<Integer> tmp = head;
        head = head.getNext();
        tmp.setNext(null);
        System.out.println(tmp.getData() + " deleted");
    }

    private void deleteLast() {
        if (head == null) {
            System.out.println("No element to delete");
            return;
        }

        ListNode<Integer> current = head;
        ListNode<Integer> previous = null;
        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }
        if (previous == null) {
            head = null;
        } else {
            previous.setNext(null);
        }
        System.out.println(current.getData() + " deleted");
    }

    private void deleteAt(int position) {
        if (head == null || position < 1) {
            System.out.println("No element to delete / position invalid");
            return;
        }

        if (position == 1) {
            deleteFirst();
        } else {
            ListNode<Integer> previous = head;
            int counter = 2;
            while (counter < position) {
                previous = previous.getNext();
                counter++;
            }
            ListNode<Integer> current = previous.getNext();
            previous.setNext(current.getNext());
            System.out.println(current.getData() + " deleted");
        }
    }

    private boolean exists(int data) {
        if (head == null) {
            throw new IllegalArgumentException("No element to search");
        }
        ListNode<Integer> current = head;
        while (current != null) {
            if (current.getData() == data) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    private void reverse() {
        if (head == null || head.getNext() == null) {
            throw new IllegalArgumentException("At least two elements required");
        }

        ListNode<Integer> previous = null;
        ListNode<Integer> current = head;
        ListNode<Integer> next;
        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        head = previous;
    }

    private Integer findMiddle() {
        if (head == null) {
            System.out.println("No element to search");
            return null;
        } else {
            ListNode<Integer> fastNode = head;
            ListNode<Integer> slowNode = head;
            while (fastNode != null && fastNode.getNext() != null) {
                fastNode = fastNode.getNext().getNext();
                slowNode = slowNode.getNext();
            }
            return slowNode.getData();
        }
    }

    private Integer findLastNth(int position) {
        if (head == null) {
            System.out.println("No element to search");
            return null;
        }
        if (position < 1) {
            System.out.println("Invalid position");
            return null;
        }
        ListNode<Integer> refPtr = head;
        ListNode<Integer> mainPtr = head;
        int counter = 0;
        boolean exceedsSize = false;
        while (counter < position) {
            if (refPtr == null) {
                exceedsSize = true;
                break;
            }
            refPtr = refPtr.getNext();
            counter++;
        }
        if (exceedsSize) {
            System.out.println("Position exceeds size");
            return null;
        }
        while (refPtr != null) {
            refPtr = refPtr.getNext();
            mainPtr = mainPtr.getNext();
        }

        return mainPtr.getData();
    }

    private void delete(int data) {
        if (head == null) {
            System.out.println("No element to delete");
            return;
        }
        if (head.getData().equals(data)) {
            head = head.getNext();
            System.out.println(data + " deleted");
            return;
        }

        ListNode<Integer> current = head;
        while (current.getNext() != null && !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }
        if (current.getNext() == null) {
            System.out.println("No matching element to delete");
            return;
        }
        System.out.println(data + " deleted");
        current.setNext(current.getNext().getNext());
    }

    private boolean checkLoop() {
        // This algorithm is called Floyd's cycle detection algorithm:
        ListNode<Integer> fastPtr = head;
        ListNode<Integer> slowPtr = head;

        while (fastPtr != null && fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext().getNext();
            slowPtr = slowPtr.getNext();
            if (fastPtr == slowPtr) {
                return true;
            }
        }

        return false;
    }

    private ListNode<Integer> getStartingNodeOfLoop() {
        ListNode<Integer> fastPtr = head;
        ListNode<Integer> slowPtr = head;

        while (fastPtr != null && fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext().getNext();
            slowPtr = slowPtr.getNext();
            if (fastPtr == slowPtr) {
                ListNode<Integer> temp = head;
                while (slowPtr != temp) {
                    temp = temp.getNext();
                    slowPtr = slowPtr.getNext();
                }
                return temp;
            }
        }

        return null;
    }

    private void removeLoop() {
        ListNode<Integer> fastPtr = head;
        ListNode<Integer> slowPtr = head;

        while (fastPtr != null && fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext().getNext();
            slowPtr = slowPtr.getNext();
            if (fastPtr == slowPtr) {
                ListNode<Integer> temp = head;
                while (slowPtr != temp) {
                    temp = temp.getNext();
                    slowPtr = slowPtr.getNext();
                }
                slowPtr.setNext(null);
            }
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList sll = new SinglyLinkedList();
        sll.head = new ListNode<>(10);
        ListNode<Integer> second = new ListNode<>(1);
        ListNode<Integer> third = new ListNode<>(0);
        ListNode<Integer> forth = new ListNode<>(11);
        sll.head.setNext(second);
        second.setNext(third);
        third.setNext(forth);

        //Testing loop:
        forth.setNext(sll.head);

        sll.display();
        System.out.println("Length: " + sll.length());
        System.out.println("Loop exists: " + sll.checkLoop());
        ListNode<Integer> startingNode = sll.getStartingNodeOfLoop();
        System.out.println("Loop start: " + (startingNode != null ? startingNode.getData() : "-"));

        sll.removeLoop();
        System.out.println("Deleted loop");
        System.out.println("Loop exists: " + sll.checkLoop());

//        sll.insertStart(5);
//        sll.display();
//        System.out.println("Length: " + sll.length());
//
//        sll.insertEnd(15);
//        sll.display();
//        System.out.println("Length: " + sll.length());
//
//        sll.insertAt(7, 25);
//        sll.display();
//        System.out.println("Length: " + sll.length());

//        sll.deleteFirst();
//        sll.display();
//        System.out.println("Length: " + sll.length());
//
//        sll.deleteLast();
//        sll.display();
//        System.out.println("Length: " + sll.length());
//
//        sll.deleteAt(2);
//        sll.display();
//        System.out.println("Length: " + sll.length());
//
//        sll.deleteAt(3);
//        sll.display();
//        System.out.println("Length: " + sll.length());

//        System.out.println("1 exists: " + sll.exists(1));
//        System.out.println("2 exists: " + sll.exists(2));
//        System.out.println("10 exists: " + sll.exists(10));
//
//        sll.deleteAt(2);
//        sll.display();
//        System.out.println("Length: " + sll.length());
//
//        System.out.println("1 exists: " + sll.exists(1));
//
//        sll.insertAt(2, 1);
//        sll.display();
//        System.out.println("Length: " + sll.length());
//
//        System.out.println("1 exists: " + sll.exists(1));

//        sll.reverse();
//        sll.display();

//        sll.insertAt(2, 15);
//        sll.display();
//        System.out.println("Length: " + sll.length());
//
//        System.out.println("Middle node: " + sll.findMiddle());

//        sll.deleteLast();
//        sll.display();

//        System.out.println("Middle node: " + sll.findMiddle());

//        System.out.println("2nd node from end: " + sll.findLastNth(2));
//        System.out.println("1st node from end: " + sll.findLastNth(1));
//        System.out.println("3rd node from end: " + sll.findLastNth(3));
//        System.out.println("4th node from end: " + sll.findLastNth(4));

//        sll.insertStart(5);
//        sll.display();
//        System.out.println("Length: " + sll.length());
//        System.out.println("3rd node from end: " + sll.findLastNth(3));

//        sll.delete(5);
//        sll.display();


//        sll.display();
    }
}
