package tr.com.ahmet.udemy.javathecompletejavadevelopercourse.interfacesabstractclasses;

public class Main {

    public static void main(String[] args) {
        Example ex = new Example();
        ex.test();
        ex.tell();
        ex.tellTwice();

        AbstractExample ae = new AbstractExample() {
            @Override
            void test() {
                concrete();
            }
        };
        AbstractExample.TITLE = "asdas";
        ae.test();
        ae.concrete();
    }
}
