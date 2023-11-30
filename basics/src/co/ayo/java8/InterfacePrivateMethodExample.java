package co.ayo.java8;

public class InterfacePrivateMethodExample {

    public static void main(String[] args) {
        new InterfacePrivateMethod<>() {}.twoPrints(); // Diamond op. in inner class is used
    }

    @SuppressWarnings("unused")
    private interface InterfacePrivateMethod<T extends String> {
        default void twoPrints() {
            printOne();
            printTwo();
        }

        private void printOne() {
            System.out.println("One");
        }

        private void printTwo() {
            System.out.println("Two");
        }
    }
}