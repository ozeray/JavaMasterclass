package co.ayo;

public class StringTransform {

    public static void main(String[] args) {
        String s = "ahmet";

        String transformed = s.transform(text -> new StringBuilder(text).reverse().toString());
        System.out.println(transformed);
    }
}
