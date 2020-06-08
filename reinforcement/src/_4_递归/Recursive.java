package _4_递归;

/**
 * 递归的使用，老是忘记怎么使用
 * 两个条件
 * 1、基线条件；也就是停止条件
 * 2、递归条件：递归关系的表述，也就是可以提炼去相对应的公司
 *
 * @date 2020/6/8
 **/
public class Recursive {
    public static void main(String[] args) {
        System.out.println(getMaxLenByTwoStr("abc","bc"));
    }

    /**
     * 斐波那契数列
     * 后一个数是前面两个数的总和，提炼出公式就是 ni = ni-1 + ni-2
     */
    private static long fac(long i) {
        if (i == 1 || i == 0) {
            return i;
        }
        if (i < 0) {
            return -1;
        }
        return fac(i - 1) + fac(i - 2);
    }

    /**
     * 台阶问题
     * 以前面试的时候遇到的问题：一次只能走一步或者两步，问到第N级阶梯有多少种走法
     * 第一层只有一种走法，第二层有两种走法，第三层就开始复杂，不过追究到底不过是 f(n)=f(n-1)+f(n-2)
     * 换个思维，总和就是往前一步f(n-1)的结果+往前两步f(n-2)的结果，也就是斐波那契数列的变种
     */
    private static long step(long n) {
        if (n <= 2) {
            return n;
        }
        return step(n - 1) + step(n - 2);
    }

    /**
     * 阶乘问题
     * 也就是 10!=1*2*3*4*5*6*7*8*9*10
     * 提取公式为 n!=n*(n-1)*(n-2)...
     * n!=n*(n-1)!
     */
    private static long factorial(long n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        if (n < 0) {
            return -1;
        }
        return n * factorial(n - 1);
    }


    /**
     * 汉诺塔
     * 假设是两个就很简单，FROM ->  BUFFER, BUFFER->TO，最后一个FROM -> TO
     * 那么如果有n个，也可以这么想 FROM(n-1) ->  BUFFER,FROM(n) -> TO,BUFFER(n-1)->TO
     * 一直拆分下去，终止条件就是 n==1，直接从from移动到to
     * 递归条件就是 上面讲述的，先利用to让from到buffer，然后将最底下的移动到to, 然再利用from从buffer到to
     */
    private static void hanoi(int n, String from, String buffer, String to) {
        // 1. 基线条件
        if (n > 0) {
            // 2. 递归条件
            hanoi(n - 1, from, to, buffer);
            System.out.println("将盘子" + n + ",从" + from + "到" + to);
            hanoi(n - 1, buffer, from, to);
        }
    }

    /**
     * 倒叙输出一个正整数
     * 先输出个位上的数字，然后不断除以10达到目的
     * 基线条件：大于10
     * 递归条件：n % 10 + (n / 10) % 10 ...
     */
    private static void revertPrintNum(int num){
        System.out.print(num % 10);
        if (num > 10){
            revertPrintNum(num / 10);
        }
    }

    /**
     * 排序问题
     * 用递归实现：输入一个字符串，打印出该字符串中字符的所有排列。例如输入字符串abc，则输出由字符a、b、c所能排列出来的所有字符串abc、acb、bac、bca、cab和cba。
     * 其实就是不断把数字固定，求下一个的排列组合，比如a+p{b,c,d} -> [a,b]+p{c,d} -> [a,b,c]+p{d} 这种思路 
     * 类似于 固定ABC交换D，固定AB交换CD，固定A交换BCD的思路
     * 基线条件：当前的替换位置为当前字符串长度
     * 递归提交：交换字符，回溯字符
     * 有点不太理解，但感觉又有点理解，突然想到如果是两个字符，那么ab和ba就是两种情况，一个字符就是一种情况，所以最简单的情况也规划化为n-1 n-2
     * 同时也就是交换和非交换两种情况
     */
    private static void sortPrintStr(char[] str, int k){
        // 1. 基线条件，全部交换完成，输出值
        if (k == str.length){
            System.out.println(str);
        }
        
        // 2. 递归交换
        for (int i = k;i<str.length;i++){
            // 先交换
            char temp= str[k];
            str[k] = str[i];
            str[i] = temp;
            
            sortPrintStr(str, k + 1);

            // 再恢复，这也算一种情况
            temp= str[k];
            str[k] = str[i];
            str[i] = temp;
        }
    }

    /**
     * m个球中取出n个球的可能情况数
     * 利用假设法，如果有一个幸运球，把所有情况罗列出来，那么就变成取出幸运球f(m-1,n-1)，没取出幸运球两种情况f(m - 1,n)，相加就是最终的情况数
     * 基线条件：如果是m=n，则只有一个情况，如果n=0则也只有一个情况，如果n>m则不存在这种情况
     * 递归条件：f(m-1,n-1) + f(m-1,n)
     */
    private static int getNFromM(int m, int n){
        if (n>m){
            return 0;
        }
        if (m==n || n ==0){
            return 1;
        }
        return getNFromM(m-1, n-1) + getNFromM(m-1, n);
    }

    /**
     * 求两个子串的最大公共序列长度
     * 思路我觉得就是不断的比较，如果有一方的长度为0了，那么就停止比较
     * 基线条件：一个字符串的长度为0
     * 递归条件：比较首位是否相等
     */
    private static int getMaxLenByTwoStr(String s1, String s2){
        // 停止条件
        if (s1.length()==0 || s2.length()==0){
            return 0;
        }
        
        // 递归对比
        if (s1.charAt(0) == s2.charAt(0)){
            return getMaxLenByTwoStr(s1.substring(1), s2.substring(1)) + 1;
        }else{
            // 不相等则子串+1
            return Math.max(getMaxLenByTwoStr(s1.substring(1),s2),getMaxLenByTwoStr(s1,s2.substring(1)));
        }
    }
}
