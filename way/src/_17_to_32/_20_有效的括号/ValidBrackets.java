package _17_to_32._20_有效的括号;

import Common.CheckUtil;
import Common.ListNode;

import java.rmi.ServerException;
import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2020/8/17
 **/
public class ValidBrackets {
    public static void main(String[] args) throws ServerException {
        String checkStr0 = "(])";
        String checkStr1 = "()";
        String checkStr2 = "()[]{}";
        String checkStr3 = "(]";
        String checkStr4 = "([)]";
        String checkStr5 = "{[]}";

        CheckUtil.checkIsEqual(checkStr0, isValid_01(checkStr0), false);
        CheckUtil.checkIsEqual(checkStr1, isValid_01(checkStr1), true);
        CheckUtil.checkIsEqual(checkStr2, isValid_01(checkStr2), true);
        CheckUtil.checkIsEqual(checkStr3, isValid_01(checkStr3), false);
        CheckUtil.checkIsEqual(checkStr4, isValid_01(checkStr4), false);
        CheckUtil.checkIsEqual(checkStr5, isValid_01(checkStr5), true);
    }

    /**
     * 循环判断是否为正确的
     *
     * @param s
     * @return
     */
    private static boolean isValid_01(String s) {
        if ("".equals(s.trim())) {
            return true;
        }

        int len = s.length();
        String left = "{[(";
        String right = "}])";
        List<Integer> leftNums = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int leftPos = left.indexOf(s.charAt(i));
            int rightPos = right.indexOf(s.charAt(i));
            int last = leftNums.size() - 1;
            if (leftPos != -1) {
                leftNums.add(leftPos);
            } else if (last < 0) {
                return false;
            } else if (rightPos == leftNums.get(leftNums.size() - 1)) {
                leftNums.remove(last);
            } else {
                return false;
            }
        }

        return leftNums.isEmpty();
    }

    /**
     * 使用栈来完成
     *
     * @param s
     * @return
     */
    private static boolean isValid_02(String s) {
        if ("".equals(s.trim())) {
            return true;
        }

        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        stack.add((int) s.charAt(0));
        for (int i = 1; i < len; i++) {
            if (stack.empty()) {
                stack.add((int) s.charAt(i));
            } else if ((int) s.charAt(i) - stack.peek() == 1 || (int) s.charAt(i) - stack.peek() == 2) {
                stack.pop();
            } else {
                stack.push((int) s.charAt(i));
            }
        }

        return stack.empty();
    }
}
