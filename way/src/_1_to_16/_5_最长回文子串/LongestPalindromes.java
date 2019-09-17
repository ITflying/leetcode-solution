package _1_to_16._5_最长回文子串;


/**
 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

 示例 1：
 输入: "babad"
 输出: "bab"
 注意: "aba" 也是一个有效答案。
 
 * @date 2019/8/29  
 **/
public class LongestPalindromes {

    public static void main(String[] args) {
        String str = "bb";
        System.out.println("最长的回文子串：" + longestPalindrome_07(str));
    }

    /**
     * 第一种方法：暴力破解法
     * 老方法，暴力一时爽，一直暴力一直爽，不过这次因为时间限制未通过检验
     */
    public static String longestPalindrome_01(String s) {
        if ("".equals(s.trim())) {
            return s;
        }

        String maxStr = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String cutStr = s.substring(i, j + 1);
                if (checkIsPalindrome(cutStr) && cutStr.length() > maxStr.length()) {
                    maxStr = cutStr;
                }
            }
        }

        return maxStr;
    }

    private static boolean checkIsPalindrome(String string) {
        int i = 0, j = string.length() - 1;
        while (i < j) {
            if (string.charAt(i) != string.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 第二种方法：暴力不可取，就原字符串直接对比
     * 还是会超时，不过取巧的方式通过，执行了612ms
     */
    public static String longestPalindrome_02(String s) {
        if ("".equals(s.trim())) {
            return s;
        }

        String maxStr = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    String cutStr = s.substring(i, j + 1);
                    if (checkIsPalindrome(cutStr) && cutStr.length() > maxStr.length()) {
                        maxStr = cutStr;
                        if (maxStr.equals(s)) {
                            return s;
                        }
                    }
                }
            }
        }

        return maxStr;
    }

    // region 抄作业时间

    /**
     * 第三种方法：最长公共子串
     * 就是将字符串导致获取公共子串，然后判断每个子串是否符合要求，因为公共子串不一定是回文串
     * 第一种方式：查看未倒置子串末尾字符和首字符是否相等，花费168ms，时间复杂度和空间复杂度都是 O(n^2)
     * 第二种方式：空间复杂度为O(n)，因为循环到第三列的时候(i=3)，第一列的数据已经不需要用了，只需要arr[i - 1][j - 1]的数据，所以可以改成一维数组
     */
    public static String longestPalindrome_03_01(String s) {
        if ("".equals(s) || s.length() <= 1) {
            return s;
        }
        String reversStr = new StringBuffer(s).reverse().toString();
        int len = s.length();

        // 这是个二维坐标，连接的线段长度越长说明公共子串越长；同时利用两个值记录起始结束下标
        int[][] arr = new int[len][len];
        int maxLen = 0, maxEnd = 0;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (s.charAt(i) == reversStr.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                }
                if (arr[i][j] > maxLen) {
                    // 做了个校验，校验未未倒置前的字符和首字符是否相同
                    int beforePre = len - 1 - j;
                    int orginalLastPos = beforePre + arr[i][j] - 1;
                    if (orginalLastPos == i) {
                        maxLen = arr[i][j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    public static String longestPalindrome_03_02(String s) {
        if ("".equals(s) || s.length() <= 1) {
            return s;
        }
        String reversStr = new StringBuffer(s).reverse().toString();
        int len = s.length();

        // 使用一个一维数组来记录长度
        int[] arr = new int[len];
        int maxLen = 0, maxEnd = 0;

        for (int i = 0; i < len; i++) {
            // 现在按列滑动的思路来记录长度，不断平移到下一列
            for (int j = len - 1; j >= 0; j--) {
                if (s.charAt(i) == reversStr.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[j] = 1;
                    } else {
                        arr[j] = arr[j - 1] + 1;
                    }
                } else {
                    // 要初始化值，否则下一列要用到的时候就不准确了
                    arr[j] = 0;
                }
                if (arr[j] > maxLen) {
                    // 做了个校验，校验未未倒置前的字符和首字符是否相同
                    int beforePre = len - 1 - j;
                    int orginalLastPos = beforePre + arr[j] - 1;
                    if (orginalLastPos == i) {
                        maxLen = arr[j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    /**
     * 第四种方法：暴力破解优化，用到了动态规划，耗时1078ms
     * 这个类似于构建二维坐标，利用查找最长线段获得长度，用空间换区时间
     */
    public static String longestPalindrome_04(String s) {
        int strLen = s.length();

        // 构造一个长宽为字符串长度的正方表格，二维
        boolean[][] dp = new boolean[strLen][strLen];
        int maxLen = 0;
        String maxPal = "";

        // 从1行0列开始
        for (int curLen = 1; curLen <= strLen; curLen++) {
            for (int start = 0; start < strLen; start++) {
                // 结尾
                int end = start + curLen - 1;
                if (end >= strLen) {
                    break;
                }
                // 获取当前节点是否跟之前的节点是否相似，如果当前位置为1或者2则直接返回ture，然后判断首尾连个元素是否相等
                dp[start][end] = (curLen == 1 || curLen == 2 || dp[start + 1][end - 1]) && s.charAt(start) == s.charAt(end);
                if (dp[start][end] && curLen > maxLen) {
                    maxPal = s.substring(start, end + 1);
                }
            }
        }
        return maxPal;
    }


    /**
     * 第四种方法：暴力破解优化，用到了动态规划，优化空间复杂度，耗时137ms
     * 只需要前一列的数据
     */
    public static String longestPalindrome_05(String s) {
        int strLen = s.length();
        String res = "";
        boolean[][] dp = new boolean[strLen][strLen];

        for (int i = strLen - 1; i >= 0; i--) {
            for (int j = i; j < strLen; j++) {
                // 判断首尾是否相同，同时考虑位置是否存在
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    /**
     * 第五种方法：拓展中心，耗时13ms
     * 回文串是对称的，循环选择一个中心，然后往左右遍历，考虑到奇偶的情况，所以会有n+n-1个中心
     * 这其实就是另一种暴力循环，选定中心往左右延伸获取长度，主要这个奇偶情况的处理需要考虑
     */
    public static String longestPalindrome_06(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * 第六种方法：Manacher's Algorithm 马拉车算法
     * 第一步将字符串改造为奇数长度，在字符中间和首尾增加特殊字符
     * 第二步再循环构造回文子串长度数组，通过中心拓展的方式获取其长度
     * 第三步则确定最长的回文子串长度并计算原字符串的做坐标
     */
    public static String longestPalindrome_07(String s) {
        // 1. 第一步：为字符串中间和首尾添加上特殊字符，改造为奇数的字符串
        String addStr = preProcess(s);

        int strlen = addStr.length();
        int[] dp = new int[strlen];
        int center = 0, right = 0;

        for (int i = 1; i < strlen - 1; i++) {
            int i_mirror = 2 * center - i;

            // 如果右节点大于当前位置，则需要获取之前得到最长回文子串长度，否则置为0
            if (right > i) {
                dp[i] = Math.min(right - i, dp[i_mirror]);
            } else {
                dp[i] = 0;
            }

            // 不断循环匹配左右两个位置，跟中心拓展一样的思想，计算该中心点的回文子串长度
            while (addStr.charAt(i + 1 + dp[i]) == addStr.charAt(i - 1 - dp[i])) {
                dp[i]++;
            }

            // 更新为更长的回文子串
            if (i + dp[i] > right) {
                center = i;
                right = i + dp[i];
            }
        }

        // 循环遍历获取最长的回文子串长度
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < addStr.length(); i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                centerIndex = i;
            }
        }

        // 获取原坐标
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    /**
     * 前期处理，在字符之间和字符串首尾添加特殊字符
     */
    public static String preProcess(String s) {
        if (s == null) {
            return "^$";
        }
        String res = "^";
        for (int i = 0; i < s.length(); i++) {
            res = res + "#" + s.charAt(i);
        }
        res = res + "#$";
        return res;
    }

    // endregion
}
