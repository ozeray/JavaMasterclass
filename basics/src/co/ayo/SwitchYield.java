package co.ayo;

public class SwitchYield {

    public static void main(String[] args) {
        var me = 4;
        System.out.println(op(me, "double"));
        System.out.println(op(me, "square"));
    }

    private static int op(int me, String type) {
        var result = switch (type) {
            case "double" -> me * 2;
            case "square" -> {
                System.out.println("square");
                yield me * me;
            }
            default -> me;
        };
        System.out.println();
        return result;
    }
}
