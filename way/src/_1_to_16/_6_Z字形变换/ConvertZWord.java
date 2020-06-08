package _1_to_16._6_Z字形变换;

import java.util.ArrayList;
import java.util.List;

/**
 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

 L   C   I   R
 E T O E S I I G
 E   D   H   N
 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

 请你实现这个将字符串进行指定行数变换的函数：

 string convert(string s, int numRows);
 
 示例 1:
 输入: s = "LEETCODEISHIRING", numRows = 3
 输出: "LCIRETOESIIGEDHN"
 
 示例 2:
 输入: s = "LEETCODEISHIRING", numRows = 4
 输出: "LDREOEIIECIHNTSG"
 解释:
 L     D     R
 E   O E   I I
 E C   I H   N
 T     S     G

 * @date 2019/9/2 
 **/
public class ConvertZWord {

    public static void main(String[] args) {
        String str = "LEETCODEISHIRING";
        int numRows = 4;
        System.out.println("源字符串：" + str);
        System.out.println("转换后的：" + ConvertZWord_03(str, numRows));
    }


    /**
     * 第一种方法：直接把它扔进n维数组当中，耗时92 ms
     * 学好暴力破解，走遍天下都不怕
     */
    private static String ConvertZWord_01(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        char[][] strRow = new char[numRows][s.length()];

        // 1. 按顺序插入到二维数组当中
        int posx = 0, posy = 0;
        boolean cut = false;
        for (int i = 0; i < s.length(); i++) {
            strRow[posx][posy] = s.charAt(i);

            if (posx == numRows - 1 || posx == 0) {
                cut = !cut;
                posy++;
            }
            posx = posx + (!cut ? -1 : 1);
        }

        // 2 二维数组按顺序循环输出
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < strRow[i].length; j++) {
                if (strRow[i][j] != '\0') {
                    sb.append(strRow[i][j]);
                }
            }
        }

        return sb.toString();
    }

    // region 抄作业时间

    /**
     * 第二种方法：官方按行排序塞入创建好的list当中
     * 只有一层循环，复杂度自然为O（n），耗时为25ms
     */
    private static String ConvertZWord_02(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        // 1. 创建非空行
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        // 2. 创建非空行中的数据，根据是哦福触碰到边界来进行向上向下读取数据的控制
        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        // 3. 把行一一取出
        StringBuilder ret = new StringBuilder();
        for (StringBuilder sb : rows) {
            ret.append(sb);
        }
        return ret.toString();
    }

    /**
     * 第三种方法：按行Z形读取字符串返回对应的字符串
     */
    private static String ConvertZWord_03(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder res = new StringBuilder();
        int len = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < len; j += cycleLen) {
                res.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < len) {
                    res.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        return res.toString();
    }


    // endregion
}
