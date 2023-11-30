package co.ayo.java8;

import java.util.Optional;

public class OptionalExamples {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        String ali = "Ali";
        String veli = null;

        Optional<String> opt = Optional.ofNullable(ali);
        Optional<String> opt2 = Optional.ofNullable(veli);

        System.out.println(opt.isEmpty());
        System.out.println(opt2.isEmpty());
        System.out.println(opt2.orElseGet(() -> "OrElse null"));
        System.out.println(opt2.orElseThrow());

        opt.ifPresent(System.out::println);
        opt2.ifPresentOrElse(System.out::println, () -> System.out.println("Null"));

    }
}
