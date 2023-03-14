package tr.com.ahmet.udemy.javathecompletejavadevelopercourse.interfacesabstractclasses;

public class Example implements IExample {
    @Override
    public void test() {
        System.out.println(time + ": " + count);
    }
}
