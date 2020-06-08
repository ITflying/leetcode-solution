package _3_回溯;

import java.util.HashMap;

/**
 * 回溯算法：优选搜索法，按优选条件向前搜索，以达到目标。
 * 当达不到就返回上一步，本质是穷举法，但是会有剪枝函数帮忙去除不可能到达的点。
 * @date 2019/9/24
 **/
public class BackTracking {
    public static void main(String args[]) {
        testBackTracking(1);
    }


    private static void testBackTracking(int flag) {
       switch (flag){
           case 1:
                
               break;
           default:
               System.out.println("test");
               break;
       }
    }

    /**
     * 八皇后问题
     * 十九世纪著名的数学家高斯1850年提出：在8X8格的国际象棋上摆放八个皇后（棋子），使其不能互相攻击，
     * 即任意两个皇后都不能处于同一行、同一列或同一斜线上。
     */
    public static void eightQueeen(String whatMater){
        
    }
}
