package tr.com.ahmet.udemy.javathecompletejavadevelopercourse.interfacesabstractclasses;

public interface IExample {

    // Interface fields are inherently public, final and static
    int time = 0;
    int count = 1;

    void test();

    // private methods are possible as of Java 9.
    // Commonly used when two default methods share common code.
    private void say() {
        System.out.println("YEY!");
    }

    // static methods are possible as of Java 8
    private static void speak() {
        System.out.println("HMM..");
    }

    // default methods are possible as of Java 8
    default void tell() {
        say();
        speak();
    }

    // more than pne default method can be declared in an interface
    default void tellTwice() {
        say();
        say();
    }
}
