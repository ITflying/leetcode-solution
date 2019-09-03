package _7_整数反转;

/**
 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

 示例 1:
 输入: 123
 输出: 321
 
 示例 2:
 输入: -123
 输出: -321
 注意:

 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

 * @date 2019/9/2  
 **/
public class reverseNum {

    public static void main(String[] args) {
        int num = 123;
        System.out.println("原数字：" + num);
        System.out.println("转换后：" + reverseNum_02(num));
    }

    /**
     * 第一种方法：直接翻转，耗时21ms，击败了百分之五的用户
     */
    public static int reverseNum_01(int x) {
        String str = String.valueOf(x);
        boolean isNegative = false;
        if (str.contains("-")) {
            isNegative = true;
            str = str.replaceAll("-", "");
        }
        StringBuilder sb = new StringBuilder(str);
        sb = sb.reverse();
        if (isNegative) {
            sb = sb.insert(0, "-");
        }
        try {
            return Integer.parseInt(sb.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    // region 抄作业时间

    /**
     * 官方解法：弹出和推入数字 & 溢出前进行检查
     */
    public static int reverseNum_02(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    // endregion 
}
