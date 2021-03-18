package _17_to_32._32_最长有效括号;

import java.rmi.ServerException;

/**
 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。

 示例 1：
 输入：s = "(()"
 输出：2
 解释：最长有效括号子串是 "()"
 示例 2：
 输入：s = ")()())"
 输出：4
 解释：最长有效括号子串是 "()()"
 示例 3：
 输入：s = ""
 输出：0
 
 提示：
 0 <= s.length <= 3 * 104
 s[i] 为 '(' 或 ')'

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/03/18
 **/
public class LongestValidParentheses {
    public static void main(String[] args) throws ServerException {
        String s = "(()";
        System.out.println("长度为："+longestValidParentheses_01(s));
    }

    /**
     *
     */
    private static int longestValidParentheses_01(String s) {
        if (s.length() < 2) return 0;
        return 1;
    }

}
