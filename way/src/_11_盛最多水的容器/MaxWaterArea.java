package _11_盛最多水的容器;

/**
 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 说明：你不能倾斜容器，且 n 的值至少为 2。
 
 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

 示例:
 输入: [1,8,6,2,5,4,8,3,7]
 输出: 49
 
 * @date 2019/9/9 
 **/
public class MaxWaterArea {

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea_02(height));

        height = new int[]{1, 1};
        System.out.println(maxArea_02(height));
    }

    /**
     * 第一种方法：暴力破解
     * 耗时657ms，时间复杂度为O(n^2)
     */
    public static int maxArea_01(int[] height) {
        int max = 0, width = 0, realHeight = 0;
        for (int x1 = 0; x1 < height.length; x1++) {
            for (int x2 = x1 + 1; x2 < height.length; x2++) {
                width = x2 - x1;
                realHeight = Math.min(height[x1], height[x2]);
                max = Math.max(max, width * realHeight);
                if (max == 56) {
                    System.out.println("test");
                }
            }
        }
        return max;
    }

    // region 双指针法

    /**
     * 第二种方法:
     * 1、 在首尾两个地方设置前后端点
     * 2、 如果左边高度小于右边，则往右移动；右边高度高于左边，则往左移动
     * 时间复杂度为O(n)
     */
    public static int maxArea_02(int[] height) {
        int maxArea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }

    public static int maxArea_02_01(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - 1) * height[i++]) :
                    Math.max(res, (j - 1) * height[j--]);
        }
        return res;
    }

    // endregion
}
