package _17_to_32._22_括号生成;

import Common.CheckUtil;
import Common.ListNode;

import java.rmi.ServerException;
import java.util.*;

/**
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 提示：
 * 1 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/02/18
 **/
public class GenerateParenthesis {
    public static void main(String[] args) throws ServerException {
        int n = 2;
        List<String> res = generateParenthesis_01(n);
        res.forEach(System.out::println);
    }

    /**
     * 循环构造，最前面和最后面是固定的，中间是必须先有左括号，再有右括号
     * 1、循环构造所有可能，然后剔除掉不符合要求的
     */
    private static List<String> generateParenthesis_01(int n) {
        if (n == 1) {
            return Collections.singletonList("()");
        }

        int len = 2 * n;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            StringBuilder sb = new StringBuilder();
            for (int t = 0; t < len; t++) {
                for (int j = 0; j < 2; j++) {
                    sb.append(j == 0 ? "(" : ")");
                }
            }
            res.add(sb.toString());
        }

        return res;
    }

    /**
     *
     */
    private static List<String> generateParenthesis_02(int n) {
        return null;
    }

    /**
     *
     */
    private static List<String> generateParenthesis_03(int n) {
        return null;
    }
}

