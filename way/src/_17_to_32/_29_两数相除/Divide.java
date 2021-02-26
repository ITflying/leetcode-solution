package _17_to_32._29_两数相除;


import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 返回被除数dividend除以除数divisor得到的商。
 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2

 示例1:
 输入: dividend = 10, divisor = 3
 输出: 3
 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 示例2:
 输入: dividend = 7, divisor = -3
 输出: -2
 解释: 7/-3 = truncate(-2.33333..) = -2

 提示：
 被除数和除数均为 32 位有符号整数。
 除数不为0。
 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231− 1]。本题中，如果除法结果溢出，则返回 231− 1。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/divide-two-integers
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/02/25
 **/
public class Divide {
    public static void main(String[] args) throws ServerException {
        int dividend = 1100540749;
        int divisor = -1090366779;
        System.out.println(dividend + "/" + divisor + "=" + divide_01(dividend, divisor));
    }

    /**
     * 移位想不出怎么做，那就暴力循环加法
     * 超过时间限制，但通过了所有测试用例
     */
    private static int divide_01(int dividend, int divisor) {
        if (divisor == 1 || divisor == -1) {
            if (dividend == Integer.MIN_VALUE && divisor == -1){
                return Integer.MAX_VALUE;
            }
            return divisor * dividend;
        }
        int ntDividend = dividend > 0 ? -dividend : dividend;
        int ntDivisor = divisor > 0 ? -divisor : divisor;
        if (ntDividend > ntDivisor) {
            return 0;
        }

        int flag = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0) ? 1 : -1;
        int sum = 0, times = 0;
        int pre = 0;
        while (true) {
            sum += ntDivisor;
            times++;

            // 处理越界
            if (times == Integer.MIN_VALUE) {
                if (flag == 1) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            if (sum > 0) {
                return flag * (times - 1);
            }

            if (sum == ntDividend) {
                return flag * times;
            } else if (sum < ntDividend) {
                break;
            }
            pre = sum;
        }

        if (pre - dividend > sum - dividend
                || (sum < 0 && dividend > 0 && sum - dividend > 0) || (sum > 0 && dividend < 0 && sum - dividend < 0)) {
            return flag * (times - 1);
        } else {
            return flag * times;
        }
    }

    /**
     * 利用  16 * 13 = 16 * (8 + 4 +  1 + 0) 的思路来遍历获取结果
     * div配合二分查找+递归迅速定位到结果
     * https://leetcode-cn.com/problems/divide-two-integers/solution/shi-yong-wei-yi-cao-zuo-qiu-jie-by-johnh/
     */
    private static int divide_02(int dividend, int divisor) {
        // 1、先转化为负数来处理越界问题
        int ntDividend = dividend > 0 ? -dividend : dividend;
        int ntDivisor = divisor > 0 ? -divisor : divisor;
        if (ntDividend > ntDivisor) {
            return 0;
        }

        // 2、循环遍历获取结果数组
        List<Integer> resList = new ArrayList<>();
        div(ntDividend, ntDivisor, resList);

        // 3、获取结果
        int res = 0;
        int symbol = (dividend ^ divisor) >> 31 == 0 ? 1 : -1;
        for (Integer tempRes : resList) {
            if (tempRes == 31) {
                if (symbol == 1) {
                    return Integer.MAX_VALUE;
                }
                return Integer.MIN_VALUE;
            }
            res += (1 << tempRes);
        }
        return symbol * res;
    }

    private static void div(int ntDividend, int ntDivisor, List<Integer> resList) {
        if (ntDividend > ntDivisor) {
            return;
        }

        // 二分查找
        int left = 0, right = 32;
        while (left + 1 != right) {
            int mid = (left + right) >> 1;

            // 判断是否直接设置右节点为中间位置，如果中间值都大于被除数，所以要更小一点负数
            int midVal = (-1 << (31 - mid));
            if (ntDivisor < midVal) {
                right = mid;
                continue;
            }

            // 逼近结果
            int tempVal = ntDivisor << mid;
            if (tempVal < ntDividend) {
                right = mid;
            } else {
                left = mid;
            }
        }
        resList.add(left);
        int newDividen = ntDividend - (ntDivisor << left);
        div(newDividen, ntDivisor, resList);
    }

}

