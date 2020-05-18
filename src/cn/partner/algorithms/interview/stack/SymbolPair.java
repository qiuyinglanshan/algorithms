package cn.partner.algorithms.interview.stack;

import java.util.Stack;

/**
 * 给定一个只包括 (，)，[，] 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 1、左括号必须用相同类型的右括号闭合。
 * 2、左括号必须以正确的顺序闭合。
 * "()"     -> true
 * "()[]" -> true
 * "([])"   -> true
 * "(]"     -> false
 * "([)]"   -> false
 * "("      -> false
 */
public class SymbolPair {
    public static void main(String[] args) {
        System.out.println(isPair("(()(])([]))"));
    }

    public static boolean isPair(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException();
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            System.out.printf("开始判断第%d个元素\n", i);
            char c = str.charAt(i);
            if (!stack.empty()) {
                Character top = stack.peek();
                if (top == '(' && c == ')' || top == '[' && c == ']') {
                    stack.pop();
                    continue;
                }
            }

            // 如果没有匹配上的话，还是一个关闭符号，直接返回false
            if (c == ')' || c == ']') {
                return false;
            }

            stack.push(c);
        }
        return stack.empty();
    }
}
