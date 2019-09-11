package _13_罗马数字转整数;

import java.util.HashMap;
import java.util.Map;

/**
 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

 字符          数值
 I             1
 V             5
 X             10
 L             50
 C             100
 D             500
 M             1000
 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

 I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

 示例 1:

 输入: "III"
 输出: 3
 示例 2:

 输入: "IV"
 输出: 4
 示例 3:

 输入: "IX"
 输出: 9
 示例 4:

 输入: "LVIII"
 输出: 58
 解释: L = 50, V= 5, III = 3.
 示例 5:

 输入: "MCMXCIV"
 输出: 1994
 解释: M = 1000, CM = 900, XC = 90, IV = 4.

 * @date 2019/9/10 
 **/
public class RomanNumToInt {

    public static void main(String[] args) {
        String s = "CMLII";
        System.out.println(convertRomanToInte_02(s));
    }

    /**
     * 第一种方法：暴力解析
     * 丑陋
     */
    private static int convertRomanToInte_01(String s) {
        int[] numList = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanList = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        String temp;
        int ret = 0;
        boolean flag = false;
        while (s.length() > 0) {
            flag = false;
            if (s.length() >= 2) {
                temp = s.substring(0, 2);
            } else {
                temp = s.substring(0, 1);
            }
            for (int j = 0; j < romanList.length; j++) {
                if (romanList[j].equals(temp)) {
                    ret = ret + numList[j];
                    s = s.substring(temp.length());
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                temp = s.substring(0, 1);
                for (int j = 0; j < romanList.length; j++) {
                    if (romanList[j].equals(temp)) {
                        ret = ret + numList[j];
                        s = s.substring(temp.length());
                        break;
                    }
                }
            }
        }

        return ret;
    }

    /**
     * 第一种方法：暴力解析
     * 丑陋
     */
    private static int convertRomanToInte_02(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                ret = ret + map.get(s.substring(i, i + 2));
                i++;
            } else {
                ret = ret + map.get(s.substring(i, i + 1));
            }
        }

        return ret;
    }


    // region 抄作业时间

    /**
     * 第二种方法：不需要存储全部组合
     * 这个就是抓住了核心，如果前面比后面的小，那么减去其对应的数值即可得到两个数值的和
     */
    private static int convertRomanToInte_03(String s) {
        Map<Character, Integer> romaNumber = new HashMap<>();
        romaNumber.put('I', 1);
        romaNumber.put('V', 5);
        romaNumber.put('X', 10);
        romaNumber.put('L', 50);
        romaNumber.put('C', 100);
        romaNumber.put('D', 500);
        romaNumber.put('M', 1000);

        int firstVal = 0, nextVal = 0;
        int ret = 0;

        for (int i = 0; i < s.length(); i++) {
            firstVal = romaNumber.get(s.charAt(i));
            if (i == s.length() - 1) {
                ret = ret + firstVal;
            } else {
                nextVal = romaNumber.get(s.charAt(i + 1));
                if (firstVal >= nextVal) {
                    ret = ret + firstVal;
                } else {
                    ret = ret - firstVal;
                }
            }
        }

        return ret;
    }

    /**
     * 最快的方法，使用switch case
     */
    private static int convertRomanToInte_04(String s) {
        int ret = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    if (i + 1 < len && (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X')) {
                        ret--;
                    } else {
                        ret++;
                    }
                    break;
                case 'V':
                    ret += 5;
                    break;
                case 'X':
                    if (i + 1 < len && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')) {
                        ret -= 10;
                    } else {
                        ret += 10;
                    }
                    break;
                case 'L':
                    ret += 50;
                    break;
                case 'C':
                    if (i + 1 < len && (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M')) {
                        ret -= 100;
                    } else {
                        ret += 100;
                    }
                    break;
                case 'D':
                    ret += 500;
                    break;
                case 'M':
                    ret += 1000;
                    break;
                default:
                    break;

            }
        }

        return ret;
    }


    // endregion
}
