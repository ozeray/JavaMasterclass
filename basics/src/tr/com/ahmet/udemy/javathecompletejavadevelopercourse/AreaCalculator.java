package tr.com.ahmet.udemy.javathecompletejavadevelopercourse;

public class AreaCalculator {

    public static void main(String[] args) {
        System.out.println(area(5));
    }

    public static double area(double radius) {
        return radius < 0 ? -1 : Math.PI * Math.pow(radius, 2);
    }

    public static double area(double x, double y) {
        return (x < 0 || y < 0) ? -1 : x * y;
    }
}
