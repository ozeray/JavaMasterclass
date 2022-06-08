package tr.com.ahmet.udemy.javathecompletejavadevelopercourse.innerclasses;

public class Main {

    public static void main(String[] args) {
        OuterAndInnerClass outer = new OuterAndInnerClass(3, "sdad");
        // This is how we instantiate an inner class object:
        OuterAndInnerClass.Inner inner = outer.new Inner(2, 2);

        System.out.println(inner.getNumber());
    }
}
