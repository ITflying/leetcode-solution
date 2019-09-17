package _1_to_16._14_最长公共前缀;

import java.util.Arrays;

/**
 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串 ""。

 示例 1:

 输入: ["flower","flow","flight"]
 输出: "fl"
 示例 2:

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。
 说明:

 所有输入只包含小写字母 a-z 。

 * @date 2019/9/10
 **/
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] s = new String[]{"flower", "flow", "flight"};
        System.out.println(Arrays.toString(s));
        System.out.println("最长公共前缀为：" + longestCommonPrefix_05(s));
    }

    /**
     * 第一种
     */
    private static String longestCommonPrefix_01(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        String ret = "";
        int i = 0;
        while (true) {
            if (i >= strs[0].length()) {
                return ret;
            }
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return ret;
                }
            }
            ret = ret + strs[0].charAt(i);
            i++;
        }
    }

    //  region 抄作业时间

    /**
     * 第二种方法：水平扫描法
     */
    private static String longestCommonPrefix_02(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    /**
     * 第三种方法：水平扫描法
     * 这其实就是第一种方法的简化版本，用第一个字符串去匹配后面的字符串
     */
    private static String longestCommonPrefix_03(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    private static String longestCommonPrefix_03_upgrade(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String s = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(s) != 0) {
                s = s.substring(0, s.length() - 1);
                if (s.isEmpty()) {
                    return "";
                }
            }
        }
        return s;
    }

    /**
     * 第四种方法：分治
     * 其实就是将问题拆分成两个部分，然后递归寻找左右两个字符串相似的部分
     */
    private static String longestCommonPrefix_04(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix_04_recursive(strs, 0, strs.length - 1);
    }

    private static String longestCommonPrefix_04_recursive(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        } else {
            int mid = (left + right) / 2;
            String lcpLeft = longestCommonPrefix_04_recursive(strs, left, mid);
            String lcpRight = longestCommonPrefix_04_recursive(strs, mid + 1, right);
            return longestCommonPrefix_04_common(lcpLeft, lcpRight);
        }
    }

    private static String longestCommonPrefix_04_common(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }

    /**
     * 第五种方法：二分查找法
     * 这其实就是把一个字符串按整体的一般长度进行匹配，不断切分匹配，直到找到最长公共前缀
     */
    private static String longestCommonPrefix_05(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (longestCommonPrefix_05_isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private static boolean longestCommonPrefix_05_isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }


    // endregoin
}
