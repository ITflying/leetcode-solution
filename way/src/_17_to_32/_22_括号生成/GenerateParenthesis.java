package _17_to_32._22_括号生成;

import Common.CheckUtil;
import Common.ListNode;

import java.rmi.ServerException;
import java.util.*;

/**
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 * 
 * 提示：
 * 1 <= n <= 8
 * 
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
     * 这种2^n的问题，遍历用递归来解决
     */
    private static List<String> generateParenthesis_01(int n) {
        if (n == 1) {
            return Collections.singletonList("()");
        }

        List<String> res = new ArrayList<>();
        int len = n * 2;
        generateAll(new char[len], 0, res);
        return res;
    }

    /**
     * 递归构造所有条件，当递归到完成字符串则判断是否符合要求，符合要求则加入结果集
     * 基线条件(什么时候停止)：当长度达到要求的时候
     * 递归条件(继续递归)：长度不符合要求继续添加
     */
    private static void generateAll(char[] current, int pos, List<String> res) {
        if (pos == current.length) {
            if (valid(current)) {
                res.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, res);
            current[pos] = ')';
            generateAll(current, pos + 1, res);
        }
    }

    /**
     * 判断是否符合要求
     * 左括号数量恒大于或等于右括号数量即可
     */
    private static boolean valid(char[] current) {
        int left = 0;
        for (char c : current) {
            if ('(' == c) {
                left++;
            } else {
                left--;
            }
            if (left < 0) {
                return false;
            }
        }
        return left == 0;
    }

    /**
     * 使用回溯方法
     */
    private static List<String> generateParenthesis_02(int n) {
        if (n == 1) {
            return Collections.singletonList("()");
        }

        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    /**
     * 回溯遍历所有结果，及时返回正确的结果
     */
    private static void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 按括号序列的长度递归
     * todo:不理解
     */
    private static List<String> generateParenthesis_03(int n) {
        return checkAndGenerate(n);
    }

    /**
     * 递归，并且在递归过程中判断是否符合要求
     */
    static ArrayList[] cache = new ArrayList[100];

    private static List<String> checkAndGenerate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> res = new ArrayList<>();
        if (n == 0) {
            res.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left : checkAndGenerate(c)) {
                    for (String right : checkAndGenerate(n - 1 - c)) {
                        res.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = res;
        return res;
    }
}

