package _10_正则表达式匹配;

/**
 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 '.' 匹配任意单个字符
 '*' 匹配零个或多个前面的那一个元素
重点： 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串！！

 说明:
 s 可能为空，且只包含从 a-z 的小写字母。
 p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 
 示例 1:
 输入:
 s = "aa"
 p = "a"
 输出: false
 解释: "a" 无法匹配 "aa" 整个字符串。
 
 示例 2:
 输入:
 s = "aa"
 p = "a*"
 输出: true
 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 
 示例 3:
 输入:
 s = "ab"
 p = ".*"
 输出: true
 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 
 示例 4:
 输入:
 s = "aab"
 p = "c*a*b"
 输出: true
 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 
 示例 5:
 输入:
 s = "mississippi"
 p = "mis*is*p*."
 输出: false

 
 * @date 2019/9/3 
 **/
public class RegularExpressionMatching {
    public static void main(String[] args) {
        String s = "aa";
        String p = "a*";
        System.out.println(isMatch_03(s, p));
    }

    // region 抄作业时间 
    // 学习链接：https://leetcode-cn.com/problems/regular-expression-matching/solution/hui-su-suan-fa-shi-yong-dong-tai-gui-hua-de-bei-wa/

    /**
     * 递归方法
     */
    private static boolean isMatch_01(String s, String p) {
        // 1. 首先判断边界条件
        if (s.equals(p)) {
            return true;
        }
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        // 2. 判断非*的情况，单个比较单个，如果是点的话就可以直接越过
        boolean first = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        // 3. 再考虑*的情况，可以把*看做多，也可以把*看做零个
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // 前者考虑是否为零的情况
            // 后者则考虑多个的意思
            // 核心就是判断后面一次是否能匹配，匹配就继续往下走，不匹配就回到原路继续向前匹配
            return isMatch_01(s, p.substring(2)) || (first && isMatch_01(s.substring(1), p));

        }
        return first && isMatch_01(s.substring(1), p.substring(1));
    }


    /**
     * 动态规划 - 自上而下，也是递归的方式 - 备忘录算法
     * 只需要4ms，200ms -> 4ms，巨大的提升，这也许就是算法的魅力所在
     */
    private static boolean isMatch_02(String s, String p) {
        int[][] note = new int[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p, note);
    }

    private static boolean dp(int i, int j, String s, String p, int[][] note) {
        if (j == p.length()) {
            return i == s.length();
        }
        if (note[i][j] == 1) {
            return false;
        }

        // 匹配字符或'.'
        boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        boolean ans;
        if ((j + 1 < p.length()) && p.charAt(j + 1) == '*') {
            ans = dp(i, j + 2, s, p, note) || (firstMatch && dp(i + 1, j, s, p, note));
        } else {
            ans = firstMatch && dp(i + 1, j + 1, s, p, note);
        }

        if (!ans) {
            note[i][j] = 1;
        }
        return ans;
    }

    /**
     * 动态规划 - 自下而上，也就是循环的方式
     */
    private static boolean isMatch_03(String s, String p) {
        boolean[][] b = new boolean[s.length() + 1][p.length() + 1];
        b[s.length()][p.length()] = true;

        for (int j = p.length() - 1; j >= 0; j--) {
            for (int i = s.length(); i >= 0; i--) {
                boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                if (j + 1 <= p.length() - 1 && p.charAt(j + 1) == '*') {
                    b[i][j] = b[i][j + 2] || (firstMatch && b[i + 1][j]);
                } else {
                    b[i][j] = firstMatch && b[i + 1][j + 1];
                }
            }
        }
        return b[0][0];
    }

    // endregion
}
