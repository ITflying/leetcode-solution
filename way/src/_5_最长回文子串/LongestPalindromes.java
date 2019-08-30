package _5_最长回文子串;

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
        String str = "aba";
        System.out.println("最长的回文子串：" + longestPalindrome_03_01(str));
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
                    if (orginalLastPos == i){
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
            for (int j = len-1; j >= 0; j--) {
                if (s.charAt(i) == reversStr.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[j] = 1;
                    } else {
                        arr[j] = arr[j - 1] + 1;
                    }
                }else{
                    // 要初始化值，否则下一列要用到的时候就不准确了
                    arr[j] = 0;
                }
                if (arr[j] > maxLen) {
                    // 做了个校验，校验未未倒置前的字符和首字符是否相同
                    int beforePre = len - 1 - j;
                    int orginalLastPos = beforePre + arr[j] - 1;
                    if (orginalLastPos == i){
                        maxLen = arr[j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    /**
     * 第四种方法：暴力破解优化，用到了动态规划
     * 
     */
    public static String longestPalindrome_04(String s) {
        return null;
    }

    public static String longestPalindrome_05(String s) {
        return null;
    }


    // endregion
}
