package co.ayo.jmc.interfacesabstractclasses;

public class Example implements IExample {
    @Override
    public void test() {
        System.out.println(time + ": " + count);
    }
}
