package tr.com.ahmet.udemy.javathecompletejavadevelopercourse;

public class DiagonalStar {

    public static void main(String[] args) {
        printSquareStar(21);
    }

    public static void printSquareStar(int number) {
        if (number < 5) {
            System.out.println("Invalid Value");
            return;
        }

        for (int row = 1; row <= number; row++) {
            for (int column = 1; column <= number; column++) {
                if (row == 1 || row == number ||
                        column == 1 || column == number ||
                        row == column || column == number - row + 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
