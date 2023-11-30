package co.ayo.jmc.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

public class MathDemo {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(Math.abs(-5));
        System.out.println(Math.abs(-2147483647));

        System.out.println(Math.pow(2, 3));
        System.out.println(Math.sqrt(100));
        System.out.println(Math.random());

        System.out.println((char) 65);
        System.out.println((char) (65 + 25));
        System.out.println((char) 97);
        System.out.println((char) (97 + 25));
        System.out.printf("%1$d = %1$c%n", (int) (Math.random() * 26 + 65)); // Random char A-Z
        System.out.printf("%1$d = %1$c%n", (int) (Math.random() * 26 + 97)); // Random char a-z
        // OR:
        System.out.printf("%1$d = %1$c%n", new Random().nextInt(97, 123)); // Random char a-z

        BigDecimal bd = new BigDecimal("15.436");
        System.out.printf("%-10s %-10d %-5d %-5d%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        bd = new BigDecimal(15.436);
        System.out.printf("%-10s %-10d %-5d %-5d%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        bd = BigDecimal.valueOf(15.436);
        System.out.printf("%-10s %-10d %-5d %-5d%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        bd = bd.setScale(2, RoundingMode.CEILING);
        System.out.printf("%-10s %-10d %-5d %-5d%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        bd = bd.setScale(1, RoundingMode.FLOOR);
        System.out.printf("%-10s %-10d %-5d %-5d%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());

//        BigDecimal oneThird = BigDecimal.ONE.divide(BigDecimal.valueOf(3)); // Exception: 0.3333... infinitely
//        BigDecimal oneThird = BigDecimal.ONE.divide(BigDecimal.valueOf(3), MathContext.UNLIMITED); // Exception: 0.3333... infinitely
//        BigDecimal oneThird = BigDecimal.ONE.divide(BigDecimal.valueOf(3), MathContext.DECIMAL32); // 0.33..3 (7 digits)
//        BigDecimal oneThird = BigDecimal.ONE.divide(BigDecimal.valueOf(3), MathContext.DECIMAL64); // 0.33..3 (16 digits)
//        BigDecimal oneThird = BigDecimal.ONE.divide(BigDecimal.valueOf(3), MathContext.DECIMAL128); // 0.33..3 (34 digits)
        BigDecimal oneThird = BigDecimal.ONE.divide(BigDecimal.valueOf(3), new MathContext(60, RoundingMode.UP)); // 0.33..34 (60 digits)
        System.out.println(oneThird);

        BigDecimal remaining = BigDecimal.ONE.subtract(oneThird);
        System.out.println(remaining);

        BigDecimal one = new BigDecimal("1.00");
        one = one.setScale(4);
        System.out.println(one);
        BigDecimal half = one.divide(BigDecimal.TWO.setScale(1));
//        half = half.setScale(1);
        System.out.println(half);
    }
}
