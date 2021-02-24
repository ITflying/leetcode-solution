package _17_to_32._28_实现strStr;

import Common.ListNode;

import java.rmi.ServerException;

/**
 实现strStr()函数。
 给定一个haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。

 示例 1:
 输入: haystack = "hello", needle = "ll"
 输出: 2
 示例 2:
 输入: haystack = "aaaaa", needle = "bba"
 输出: -1
 说明:

 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 对于本题而言，当needle是空字符串时我们应当返回 0 。这与C语言的strstr()以及 Java的indexOf()定义相符。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/implement-strstr
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/02/24
 **/
public class StrStr {
    public static void main(String[] args) throws ServerException {
        String haystack = "a", needle = "a";
        int index = strStr_02(haystack, needle);
        System.out.println("index=" + index);
    }

    /**
     * 暴力遍历
     */
    private static int strStr_01(String haystack, String needle) {
//        return haystack.indexOf(needle); // 年轻人不讲武德，好自为之
        if (needle.length() == 0) {
            return 0;
        }
        int pos = -1;
        int hlen = haystack.length();
        for (int i = 0; i < hlen; i++) {
            if (hlen - i < needle.length()) {
                break;
            }
            if (haystack.charAt(i) == needle.charAt(0) &&
                    haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return pos;
    }

    /**
     * KMP算法实现
     */
    private static int strStr_02(String haystack, String needle) {
        // todo
        return 0;
    }

}

