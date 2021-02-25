package _17_to_32._28_实现strStr;

import Common.ListNode;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;

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
        String haystack = "aabaaabaaac", needle = "aabaaac";
        int index = strStr_KMP(haystack, needle);
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
     * 这下面的算法看的头疼，抄完就算成功
     * https://leetcode-cn.com/problems/implement-strstr/solution/zhe-ke-neng-shi-quan-wang-zui-xi-de-kmp-8zl57/
     */
    private static int strStr_KMP(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (haystack.length() == 0) return -1;
        char[] hArr = haystack.toCharArray();
        char[] nArr = needle.toCharArray();
        int hLen = haystack.length();
        int nLen = needle.length();

        return kmp(hArr, hLen, nArr, nLen);
    }

    private static int kmp(char[] hArr, int hLen, char[] nArr, int nLen) {
        // 获取next数组
        int[] next = getKmpNext(nArr, nLen);
        int j = 0;
        for (int i = 0; i < hLen; ++i) {
            while (j > 0 && hArr[i] != nArr[j]) {
                j = next[j - 1] + 1;
                if (nLen - j + i > hLen) {
                    return -1;
                }
            }
            if (hArr[i] == nArr[j]) {
                ++j;
            }
            if (j == nLen) {
                return i - nLen + 1;
            }
        }
        return -1;
    }

    private static int[] getKmpNext(char[] nArr, int nLen) {
        int[] next = new int[nLen];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < nLen; i++) {
            while (k != -1 && nArr[k + 1] != nArr[i]) {
                k = next[k];
            }
            if (nArr[k+1] == nArr[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }
//    //定义 next 数组
//    int[] next = new int[len];
//    // 初始化
//    next[0] = -1;
//    int k = -1;
//        for (int i = 1; i < len; ++i) {
//        //我们此时知道了 [0,i-1]的最长前后缀，但是k+1的指向的值和i不相同时，我们则需要回溯
//        //因为 next[k]就时用来记录子串的最长公共前后缀的尾坐标（即长度）
//        //就要找 k+1前一个元素在next数组里的值,即next[k+1]
//        while (k != -1 && needle[k + 1] != needle[i]) {
//            k = next[k];
//        }
//        // 相同情况，就是 k的下一位，和 i 相同时，此时我们已经知道 [0,i-1]的最长前后缀
//        //然后 k - 1 又和 i 相同，最长前后缀加1，即可
//        if (needle[k+1] == needle[i]) {
//            ++k;
//        }
//        next[i] = k;
//
//    }
//        return next;
//
//    作者：chefyuan
//    链接：https://leetcode-cn.com/problems/implement-strstr/solution/zhe-ke-neng-shi-quan-wang-zui-xi-de-kmp-8zl57/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * Sunday算法实现
     * https://leetcode-cn.com/problems/implement-strstr/solution/javashi-xian-sundaysuan-fa-xiang-xi-zhu-shi-by-da-/
     */
    private static int strStr_sunnday(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int hLen = haystack.length(), nLen = needle.length();
        if (hLen < nLen) return -1;

        // 创建偏移表
        Map<Character, Integer> offsetMap = new HashMap<>();
        for (int i = 0; i < nLen; i++) {
            offsetMap.put(needle.charAt(i), nLen - i);
        }

        int idx = 0;
        while (idx + nLen <= hLen) {
            String cutHay = haystack.substring(idx, idx + nLen);
            if (cutHay.equals(needle)) {
                return idx;
            } else {
                if (idx + nLen >= hLen) return -1;
                if (offsetMap.containsKey(haystack.charAt(idx + nLen))) {
                    idx += offsetMap.get(haystack.charAt(idx + nLen));
                } else {
                    idx += nLen;
                }
            }
        }
        return -1;
    }

    /**
     * Boyer-Moore字符串搜索算法
     * 从后往前比较
     * https://leetcode-cn.com/problems/implement-strstr/solution/boyer-mooresuan-fa-shi-xian-bi-kmpsuan-f-ayo8/
     */
    private static int strStr_BM(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        BoyerMoore bm = new BoyerMoore(needle);
        return bm.search(haystack, needle);
    }

    static class BoyerMoore {
        int[] right;

        public BoyerMoore(String needle) {
            right = new int[26];
            for (int i = 0; i < 26; i++) {
                right[i] = -1;
            }
            for (int i = 0; i < needle.length(); i++) {
                right[needle.charAt(i) - 'a'] = i;
            }
        }

        public int search(String mainStr, String patternStr) {
            int jump = 0;
            for (int i = 0; i <= mainStr.length() - patternStr.length(); i += jump) {
                jump = 0;
                for (int j = patternStr.length() - 1; j >= 0; j--) {
                    if (patternStr.charAt(j) != mainStr.charAt(i + j)) {
                        jump = j - right[mainStr.charAt(i + j) - 'a'];
                        if (jump < 1) jump = 1;
                        break;
                    }
                }
                if (jump == 0) {
                    return i;
                }
            }
            return -1;
        }
    }
}

