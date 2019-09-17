package _1_to_16._9_回文数;

/**
 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

 示例 1:
 输入: 121
 输出: true
 
 示例 2:
 输入: -121
 输出: false
 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 
 示例 3:
 输入: 10
 输出: false
 解释: 从右向左读, 为 01 。因此它不是一个回文数。

 * @date 2019/9/3  
 **/
public class CheckIsPalindrome {
    public static void main(String[] args) {
        int num = -2147483638;
        System.out.println("原数字：" + num);
        System.out.println("转换后：" + (isPalindrome_02(num) ? "是" : "不是"));
    }

    /**
     * 第一种方法：暴力破解，转化为字符串再循环遍历首尾是否相同
     * 传统艺能不能丢
     */
    public static boolean isPalindrome_01(int x) {
        if (x < 0) {
            return false;
        }
        String s = String.valueOf(x);
        int len = s.length();
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 第二种：直接分解数字组成倒叙，然后判断是否相同
     * 耗时46ms，比上一种更慢
     * 得保证不越界
     */
    public static boolean isPalindrome_02(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }

        long sum = 0;
        int temp = x;
        int add;
        while (temp != 0) {
            add = temp % 10;
            sum = 10 * sum + add;
            temp = temp / 10;
        }

        if (sum == x) {
            return true;
        } else {
            return false;
        }
    }

    // region 抄作业时间

    /**
     * 第三种：官方解法，只判断半般的数字是否符合要求
     * 时间复杂度：O(log10(n))
     * 空间复杂度为O(1)
     * 这里巧妙地利用了一半数字进行对比，核心在于如果数字大于构建的数字时就代表已经过去了一半数字，同时要考虑奇偶的情况
     */
    public static boolean isPalindrome_03(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }


        long sum = 0;
        while (x > sum) {
            sum = 10 * sum + x % 10;
            x = x / 10;
        }

        // 需要考虑奇数的情况，所以要除以10
        return x == sum || x == sum / 10;
    }
    
    // endregion
}
