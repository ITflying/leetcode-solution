package _12_整数转罗马数字;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

 字符          数值
 I             1
 V             5
 X             10
 L             50
 C             100
 D             500
 M             1000
 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

 I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

 示例 1:
 输入: 3
 输出: "III"
 
 示例 2:
 输入: 4
 输出: "IV"
 
 示例 3:
 输入: 9
 输出: "IX"
 
 示例 4:
 输入: 58 
 输出: "LVIII"
 解释: L = 50, V = 5, III = 3.
 
 示例 5:
 输入: 1994
 输出: "MCMXCIV"
 解释: M = 1000, CM = 900, XC = 90, IV = 4.

 * @date 2019/9/9 
 **/
public class IntToRomanNum {

    public static void main(String[] args) {
        int num = 3;
        System.out.println(convertIntToRoman_02(num));
    }

    /**
     * 第一种方法：暴力解析
     */
    private static String convertIntToRoman_01(int num) {
        int[] numList = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanList = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder ret = new StringBuilder();
        int diff;

        int i = 0;
        while (num > 0) {
            diff = num / numList[i];
            if (diff > 0) {
                for (int j = 0; j < diff; j++) {
                    ret.append(romanList[i]);
                }
                num = num - diff * numList[i];
            } else {
                i++;
            }
        }
        return ret.toString();
    }

    // region 抄作业时间

    /**
     * 第二种方法：更为暴力的解题方式
     */
    private static String convertIntToRoman_02(int num) {
        String[] bit1 = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] bit10 = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] bit100 = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] bit1000 = new String[]{"", "M", "MM", "MMM"};
        return bit1000[num / 1000] + bit100[num % 1000 / 100] + bit10[num % 100 / 10] + bit1[num % 10];
    }

    // endregion
}
