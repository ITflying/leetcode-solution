package _1_to_16._3_无重复字符的最长子串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3 
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 
 * 核心：滑动窗口，对窗口左右区间的确定
 *
 * @date 2019/8/28  
 **/
public class LengthOfLongestSubstrings {

    public static void main(String[] args) {
        String testStr = "abcaca";
        System.out.println("无重复字符的最长子串长度为：" + lengthOfLongestSubstring_04(testStr));
    }

    /**
     * 第一种方法，使用set来存储数据,依次循环下去，巨慢，240ms
     * 因为遇到重复的就会到重复的下一元素开始遍历重复判断是否重复，时间复杂度应该为o(2n)或o(n)
     * 看了题解后发现这就是滑动窗口的思路，只是处理窗口位置太粗糙了
     */
    private static int lengthOfLongestSubstring_01(String s) {
        int maxLen = 0;
        HashMap<Character, Integer> pos = new HashMap<>();
        Set<Character> characterSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char word = s.charAt(i);
            if (!characterSet.add(s.charAt(i))) {
                if (characterSet.size() > maxLen) {
                    maxLen = characterSet.size();
                }
                i = pos.get(word) + 1;
                pos = new HashMap<>();
                characterSet = new HashSet<>();
                characterSet.add(s.charAt(i));
                pos.put(s.charAt(i), i);
            } else {
                pos.put(word, i);
            }
        }
        if (characterSet.size() > maxLen) {
            return characterSet.size();
        }
        return maxLen;
    }

    /**
     * 第二种方法：纯粹的暴力解法，划分出n个区域，然后看n个区域是否重复，重复判断，时间复杂度为o(n^3)
     * 会超出时间限制
     */
    private static int lengthOfLongestSubstring_02(String s) {
        int len = s.length(), maxLen = 0;
        Set<Character> characterSet = null;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String substr = s.substring(i, j);
                characterSet = new HashSet<>();
                boolean notHaveDiff = true;
                for (int k = 0; k < substr.length(); k++) {
                    if (!characterSet.add(substr.charAt(k))) {
                        notHaveDiff = false;
                        break;
                    }
                }
                if (notHaveDiff && substr.length() > maxLen) {
                    maxLen = substr.length();
                }
            }
        }
        return maxLen;
    }

    // region 抄作业时间
    
    /**
     * 第三种方法：滑动窗口方式，这其实是第一种方法的优化方式，更灵巧的处理了起始于结尾位置，时间复杂度也为o(n)
     */
    private static int lengthOfLongestSubstring_03(String s) {
        int len = s.length();
        Set<Character> characterSet = new HashSet<>();
        int max = 0, i = 0, j = 0;
        while (i < len && j < len) {
            if (!characterSet.contains(s.charAt(j))) {
                characterSet.add(s.charAt(j++));
                max = Math.max(max, j - i);
            } else {
                characterSet.remove(s.charAt(i++));
            }
        }
        return max;
    }

    /**
     * 第四种方法：优化后的滑动窗口方式，这是对第一种方法的优化
     * 起始就是跳过已经计算过的数据，直接在重复后面进行继续计算。
     * 比如abcaca,第一次a重复，i变为1，第二层c重复，i变为3，直接越过了2的b开始重新计算最长无重复字串
     */
    private static int lengthOfLongestSubstring_04(String s) {
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; j < len; j++) {
            char temp = s.charAt(j);
            if (map.containsKey(temp)) {
                i = Math.max(map.get(temp), i);
            }
            max = Math.max(max, j - i + 1);
            map.put(temp, j + 1);
        }
        return max;
    }

    /**
     * 第五种方法：
     * 默认创建128个空间的数据，然后不断循环获取每个无重复子串的长度,空间换时间，9ms
     */
    private static int lengthOfLongestSubstring_05(String s) {
        int len = s.length();
        int[] index = new int[128];
        int max = 0;
        for (int i = 0, j = 0; j < len; j++) {
            i = Math.max(index[s.charAt(j)], i);
            max = Math.max(max, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return max;
    }

    /**
     * 第六种方法：循环对前面的子串进行判断
     */
    private static int lengthOfLongestSubstring_06(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return 0;
        }
        int max = 0;
        int baseIndex = 0;
        int i, j;

        for (i = baseIndex + 1; i < chars.length; i++) {
            for (j = baseIndex; j < i; j++) {
                if (chars[j] == chars[i]) {
                    max = Math.max((i - baseIndex), max);
                    baseIndex = j + 1;
                    break;
                }
            }
        }

        max = Math.max((i - baseIndex), max);
        return max;
    }
    
    // endregion
}
