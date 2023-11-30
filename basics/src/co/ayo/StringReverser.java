package co.ayo;

public class StringReverser {

    public static void main(String[] args) {
        String orig = "original";

        String reversed = reverse(orig);
        System.out.println(reversed);

        reversed = manualReverse(orig);
        System.out.println(reversed);

        reversed = traditionalReverse(orig);
        System.out.println(reversed);
    }

    @SuppressWarnings("StringContatenationInLoop")
    private static String manualReverse(String orig) {
        String reverse = "";
        for (int i = 0; i < orig.length(); i++) {
            reverse = orig.charAt(i) + reverse; // Memory consumption high!
        }
        return reverse;
    }

    private static String reverse(String orig) {
        StringBuilder sb = new StringBuilder(orig);
        return sb.reverse().toString();
    }

    private static String traditionalReverse(String orig) {
        char[] chars = orig.toCharArray();
        int start = 0, end = chars.length - 1;
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
        return new String(chars);
    }
}
