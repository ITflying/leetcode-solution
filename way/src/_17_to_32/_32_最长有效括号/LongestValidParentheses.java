package _17_to_32._32_最长有效括号;

import java.rmi.ServerException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 * <p>
 * 提示：
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/03/18
 **/
public class LongestValidParentheses {
    public static void main(String[] args) throws ServerException {
        String s = ")(()()";
        System.out.println("长度为：" + longestValidParentheses_04(s));
    }

    /**
     * 遇到括号就是栈，然后深度就是动态规划;而我遇到就是暴力一把梭，往往会超时
     * 这个边界条件没看到答案前搞不懂
     * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
     */
    private static int longestValidParentheses_01(String s) {
        if (s.length() < 2) return 0;

        int len = s.length() / 2 * 2;
        for (int i = len; i >= 2; i = i - 2) {
            for (int j = 0; j <= s.length() - i; j++) {
                String cutS = s.substring(j, j + i);
                if (isValid(cutS)) {
                    return i;
                }
            }
        }
        return 0;
    }

    private static boolean isValid(String str) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 动态规划
     */
    private static int longestValidParentheses_02(String s) {
        if (s.length() < 2) return 0;

        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /**
     * 栈
     */
    private static int longestValidParentheses_03(String s) {
        if (s.length() < 2) return 0;

        int maxans = 0;
        // 双端队列
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    /**
     * 正向逆向结合法
     */
    private static int longestValidParentheses_04(String s) {
        if (s.length() < 2) return 0;

        int left = 0, right = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxLen;
    }

}
