package _33_to_48._38_外观数列;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * <p>
 * 示例 1：
 * 输入：n = 1
 * 输出："1"
 * 解释：这是一个基本样例。
 * 示例 2：
 * 输入：n = 4
 * 输出："1211"
 * 解释：
 * countAndSay(1) = "1"
 * countAndSay(2) = 读 "1" = 一 个 1 = "11"
 * countAndSay(3) = 读 "11" = 二 个 1 = "21"
 * countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-and-say
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/03/29
 **/
public class CountAndSay {
    public static void main(String[] args) throws ServerException {
        System.out.println(countAndSay_01(4));
    }

    /**
     * 迭代
     */
    private static String countAndSay_01(int n) {
        String ret = "1";
        for (int i = 1; i < n; i++) {
            int num = 1;
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < ret.length(); j++) {
                char temp = ret.charAt(j);
                while (j + 1 < ret.length() && temp == ret.charAt(j + 1)) {
                    j++;
                    num++;
                }
                sb.append(num).append(temp);
                num = 1;
            }
            ret = sb.toString();
        }
        return ret;
    }

    /**
     * 递归
     */
    private static String countAndSay_02(int n) {
        return recursion(n);
    }

    /**
     * 基线条件：== 1
     * 递归条件：f(n) = w(f(n-1))
     */
    private static String recursion(int n) {
        if (n == 1) {
            return "1";
        }
        String last = recursion(n - 1);
        int num = 1;
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < last.length(); j++) {
            char temp = last.charAt(j);
            while (j + 1 < last.length() && temp == last.charAt(j + 1)) {
                j++;
                num++;
            }
            sb.append(num + "" + temp);
            num = 1;
        }
        return sb.toString();
    }
}


