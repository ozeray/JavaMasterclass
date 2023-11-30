package co.ayo.jmc.abstractclasses;

public class MyLinkedList implements NodeList {

    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if (newItem == null) {
            return false;
        }
        if (this.root == null) {
            this.root = newItem;
            this.root.setPrevious(null);
            this.root.setNext(null);
            return true;
        } else {
            ListItem currentItem = this.root;
            while (currentItem != null) {
                int comparison = currentItem.compareTo(newItem);
                if (comparison < 0) {
                    if (currentItem.next() != null) {
                        currentItem = currentItem.next();
                    } else {
                        currentItem.setNext(newItem)
                                   .setPrevious(currentItem);
                        return true;
                    }
                } else if (comparison > 0) {
                    if (currentItem.previous() != null) {
                        currentItem.previous().setNext(newItem)
                                              .setPrevious(currentItem.previous());
                        newItem.setNext(currentItem)
                               .setPrevious(newItem);
                    } else {
                        newItem.setNext(this.root).setPrevious(newItem);
                        this.root = newItem;
                    }
                    return true;
                } else {
                    System.out.println(newItem.getValue() + " is already present, not added");
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if (comparison == 0) {
                if (currentItem.equals(this.root)) {
                    this.root = currentItem.next();
                    if (root != null) {
                        this.root.setPrevious(null);
                    }
                } else {
                    currentItem.previous().setNext(currentItem.next());
                    if (currentItem.next() != null) {
                        currentItem.next().setPrevious(currentItem.previous());
                    }
                }
                return true;
            } else if (comparison < 0) {
                currentItem = currentItem.next();
            } else {
                System.out.println(item.getValue() + " not found, not removed");
                return false;
            }
        }
        return false;
    }

    @Override
    public void traverse(ListItem base) {
        System.out.println("==================");
        if (this.root == null) {
            System.out.println("The list is empty");
        } else {
            while (base != null) {
                System.out.println(base.getValue());
                base = base.next();
            }
        }
        System.out.println("==================");
    }
}
