package com.ahmet;

import java.util.Stack;

public class BalancedParentheses {

    public static void main(String[] args) {
        String expression1 = "{[ a; ( b; ) c; ]}";
        String expression2 = "{[ a; ( b; ) c; }]"; //incorrectly matched in last two characters
        String expression3 = "a{[ a; ( b; ) cc; ]}dd";

        System.out.println("expression1 is balanced: " + isBalanced(expression1));
        System.out.println("expression2 is balanced: " + isBalanced(expression2));
        System.out.println("expression3 is balanced: " + isBalanced(expression3));
    }

    private static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (ch == ')' || ch == '}' || ch == ']') {
                    char top = stack.pop();
                    if (ch == ')' && top != '(' ||
                        ch == '}' && top != '{' ||
                        ch == ']' && top != '[') {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }
}
