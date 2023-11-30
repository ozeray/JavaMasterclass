package com.ahmet.jmc.innerclasses;

public class OuterAndInnerClass {

    private int number;
    private String title;

    public OuterAndInnerClass(int number, String title) {
        this.number = number;
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Actually, declaring an inner class as public is meaningless.
    // And when an inner class is declared as private, you cannot access the inner
    // class from outside the outer class. In this case, you can only instantiate
    // it inside the outer class.
    public class Inner {
        private int number;
        private int count;

        public Inner(int number, int count) {
            this.number = number;
            this.count = count;
            title = ""; // Outer class field - direct access
            OuterAndInnerClass.this.number = 0; // Outer class field - indirect access,
            // because the inner class shadows the same.
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getNumber() {
            return number;
        }
    }
}
