package tr.com.ahmet.udemy.javathecompletejavadevelopercourse;

public class FactorPrinter {

    public static void main(String[] args) {
        printFactors(12);
        printFactors(0);
        printFactors(-4);
        printFactors(6);
        printFactors(32);
        printFactors(10);
    }

    public static void printFactors(int number) {
        if (number < 1) {
            System.out.println("Invalid Value");
        }

        int counter = 1;
        while (counter <= number) {
            if (number % counter == 0) {
                System.out.println(counter);
            }
            if (counter == number / 2) {
                counter *= 2;
            } else {
                counter++;
            }
        }
    }
}
