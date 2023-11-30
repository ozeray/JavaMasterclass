package com.ahmet.jmc.abstractclasses;

public abstract class ListItem {

    abstract ListItem next();
    abstract ListItem setNext(ListItem next);
    abstract ListItem previous();
    abstract ListItem setPrevious(ListItem previous);
    abstract int compareTo(ListItem item);

    protected ListItem leftLink;
    protected ListItem rightLink;

    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
