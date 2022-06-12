package tr.com.ahmet.udemy.javathecompletejavadevelopercourse.abstractclasses;

public interface NodeList {

    ListItem getRoot();
    boolean addItem(ListItem item);
    boolean removeItem(ListItem item);
    void traverse(ListItem base);

}
