package _17_to_32._29_两数相除;


import java.rmi.ServerException;
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
        int dividend = 10;
        int divisor = 3;
        System.out.println(dividend + "/" + divisor + "=" + divide_01(dividend, divisor));
    }

    /**
     * 除法，不能使用乘除取余，就限定在数字本身上面，那么二进制移位可以替代乘除
     * 但边界条件和一些处理做不出来，回去再想想
     */
    private static int divide_01(int dividend, int divisor) {
        // todo
        return -1;
    }

}

