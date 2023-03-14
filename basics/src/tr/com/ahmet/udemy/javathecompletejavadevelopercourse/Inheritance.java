package tr.com.ahmet.udemy.javathecompletejavadevelopercourse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Inheritance {

    public class Parent {

        protected void test() {
            System.out.println("Test");
        }

        public List<String> test2() {
            return Collections.emptyList();
        }

        public void test3(List<String> list) {

        }
    }

    public class Child extends Parent {

        @Override
//        private void test() { // Private not permitted
        public void test() { // Public permitted
            super.test();
        }

        @Override
//        public ArrayList<String> test2() throws IOException { // throws not permitted, since the parent method does not throw exception
        public ArrayList<String> test2() { // Covariant type permitted in overriden method.
            return new ArrayList<>();
        }

        @Override
//        public void test3(ArrayList<String> list) { // Parameters have to be of same type.
        public void test3(List<String> list) {
            super.test3(list);
        }
    }

}
