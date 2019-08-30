package _2_动态规划;

import java.util.HashMap;

/**
 * 
 * @date 2019/8/30  
 **/
public class DynamicProgramming {
    public static void main(String args[]) {
        testGold();
    }
    
    public static void testClimbStairs(){
        for (int i = 0; i < 10;i++) {
            int res = climbStairs(i);
            System.out.print(res+"\t");
        }

        System.out.println();
        for (int i = 0; i < 10;i++) {
            int res = climbStairsOfNote(i, new HashMap<>());
            System.out.print(res+"\t");
        }

        System.out.println();
        for (int i = 0; i < 10;i++) {
            int res = climbStairsByBottom(i);
            System.out.print(res+"\t");
        }
    }
    
    // todo：动态规划的思想还是不太熟练，之后再慢慢研究吧
    
    // region 爬楼梯问题

    /**
     * 有n个台阶，如果一次只能上1个或2个台阶，求一共有多少种上法
     * 1、每次只能上1个台阶或2个台阶，那么到第n个台阶，就可以从n-1个台阶上一步到达，也可以从n-2个台阶上两步到达
     * 2、公式就变成了f(n) = f(n-1)+f(n-2) f就是计算到达那个台阶有多少种方法的函数
     * 3、一直延续下去就可以延续到 f(1),f(2)，而f(1)=1，f(2)=2，就可以返回到上一层进行计算了
     */
    public static int climbStairs(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        return climbStairs(n-1) + climbStairs(n-2);
    }


    /**
     * 备忘录算法，遇到重复计算的数值就保存起来，然后等到用的时候就可以拿出来用了
     */
    public static int climbStairsOfNote(int n, HashMap<Integer, Integer> note){
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (note.containsKey(n)){
            return note.get(n);
        }else{
            int value =  climbStairsOfNote(n-1, note) + climbStairsOfNote(n-2, note);
            note.put(n, value);
            return value;
        }
    }
    
    /**
     * 自底而上的算法，引入两个变量从底往上进行计算
     */
    public static int climbStairsByBottom(int n){
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        int i = 1, j = 2,temp =0;

        for (int k = 0; k < n - 2; k++) {
            temp = i + j;
            i = j;
            j = temp;
        }
        return temp;
    }
    
    // endregion
    
    // region 金矿问题

    /**
     * 有一个国家发现了5座金矿，每座金矿的黄金储量不同，需要参与挖掘的工人数也不同。
     * 参与挖矿工人的总数是10人。每座金矿要么全挖，要么不挖，不能派出一半人挖取一半金矿。
     * 要求用程序求解出，要想得到尽可能多的黄金，应该选择挖取哪几座金矿？最多黄金数量为多少？
     * 金矿编号	黄金储量	需要人数
     * 1	400	5
     * 2	500	5
     * 3	200	3
     * 4	300	4
     * 5	350	3
     */
    public static void testGold(){
        int n = 5;
        int w = 10;
        int[] g = new int[]{400,500,200,300,35};
        int[] p = new int[]{5,5,3,4,3};

        int maxGold = getMaxGold(n,w,g,p);
        System.out.println(maxGold);

        int maxGold2 = getMaxGoldOfNote(n,w,g,p, new HashMap<>());
        System.out.println(maxGold2);

        int maxGold3 = getMaxGoldByBottom(n,w,g,p);
        System.out.println(maxGold3);
    }

    /**
     * 首先是递归，时间复杂度为O(n*w)
     */
    public static int getMaxGold(int n,int w,int[] g, int[] p){
        if (n <= 1 && w <p[0]){
            return 0;
        }
        if (n == 1){
            return g[0];
        }
        if (n > 1 && w < p[n-1]){
            return getMaxGold(n-1, w , g, p);
        }
        
        int a = getMaxGold(n-1 ,w,g,p);
        int b = getMaxGold(n-1 ,w - p[n-1],g,p) + g[n-1];

        return Math.max(a, b);
    }

    /**
     * 备忘录模式
     */
    public static int getMaxGoldOfNote(int n, int w, int[] g, int[] p, HashMap<String, Integer> note) {
        if (n <= 1 && w < p[0]) {
            return 0;
        }
        if (n == 1) {
            return g[0];
        }
        if (n > 1 && w < p[n - 1]) {
            return getMaxGold(n - 1, w, g, p);
        }
        int a = 0, b = 0;

        String key1 = n + String.valueOf(w);
        if (note.containsKey(key1)) {
            a = note.get(key1);
        } else {
            a = getMaxGold(n - 1, w, g, p);
            note.put(key1, a);
        }

        String key2 = n + String.valueOf(w - p[n - 1]);
        if (note.containsKey(key2)) {
            b = note.get(key2);
        } else {
            b = getMaxGold(n - 1, w - p[n - 1], g, p) + g[n - 1];
            note.put(key2, b);
        }
        return a > b ? a : b;
    }

    /**
     * 自底而上的算法
     */
    public static int getMaxGoldByBottom(int n,int w,int[] g, int[] p){
        int col = w + 1;
        int[] preRes = new int[col];
        int[] result = new int[col];
        
        // 第一行的数据填充
        for (int i = 0; i < col;i++){
            if (i < p[0]){
                preRes[i] = 0;
            }else{
                preRes[i] = g[0];
            }
        }
        
        // 如果只有一个矿，直接返回
        if (n == 1){
            return preRes[w];
        }
        
        for (int i = 1;  i< n; i++){
            for (int j =0; j <col; j++){
                if (j < p[i]){
                    result[j] = preRes[j];
                }else{
                    result[j] = Math.max(preRes[j], preRes[j-p[i]]+g[i]);
                }
            }
            
            for (int k = 0; k < col; k++){
                preRes[k] = result[k];
            }
        }
        
        return result[w];
    }

    // endregion
}
