package _14_最长公共前缀;

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
        String[] s = new String[]{"flower","flow","flight"};
        System.out.println(Arrays.toString(s));
        System.out.println("最长公共前缀为："+longestCommonPrefix_02(s));
    }

    /**
     * 第一种
     */
    private static String longestCommonPrefix_01(String[] strs) {
        if (strs.length == 0){
            return "";
        }
        
        String ret = "";
        int i = 0;
        while (true){
            if (i >= strs[0].length()){
                return ret;
            }
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) !=  strs[0].charAt(i)){
                    return ret;
                }
            }
            ret = ret + strs[0].charAt(i);
            i++;
        }
    }
    
    //  region 抄作业时间

    /**
     * 水平扫描法
     */
    private static String longestCommonPrefix_02(String[] strs) {
        if (strs.length == 0){
            return "";
        }
        
        String prefix = strs[0];
        for (int i = 1; i < strs.length;i++){
            while (strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    /**
     * 水平扫描法
     */
    private static String longestCommonPrefix_03(String[] strs) {
        if (strs.length == 0){
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length;i++){
            while (strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
    
    // endregoin
}
