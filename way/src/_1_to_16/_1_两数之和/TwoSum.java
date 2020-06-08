package _1_to_16._1_两数之和;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 核心：占位寻找另一个值
 * @date 2019/8/21
 **/
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, -2, -3, -4, -5};
        int target = 0;

        int[] res = fastest_twoSum(nums, target);
        for (int num : res) {
            System.out.println(num);
        }
    }

    /**
     * 第一种自然就是暴力循环，时间复杂度为O（n^2）
     */
    private static int[] twoSum_1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 第二种可以用占位的方式，比如hashmap，空间换时间
     */
    private static int[] twoSum_2(int[] nums, int target) {
        HashMap<Integer, Integer> num2pos = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer targetNum = num2pos.get(target - nums[i]);
            if (targetNum != null && targetNum != i) {
                return new int[]{num2pos.get(target - nums[i]), i};
            }
            num2pos.put(nums[i], i);
        }

        return null;
    }

    /**
     * 第三种 抄作业时间
     * 也是类似于占位的方法，首先划分一个区域，然后按位存放数据，如果那个位置有对应数据就代表有数据在
     * 2047的选择是有讲究的，原码和补码全都是1；将两个判断变成一个判断，而且按位与直接锁定对应位置。
     */
    private static int[] fastest_twoSum(int[] nums, int target) {
        int max = 2047;
        int[] temp = new int[max + 1];
        int diffPos;
        for (int i = 0; i < nums.length; i++) {
            diffPos = (target - nums[i]) & max;
            if (temp[diffPos] != 0) {
                return new int[]{temp[diffPos] - 1, i};
            }
            temp[nums[i] & max] = i + 1;
        }

        throw new IllegalArgumentException("No two sum solution");
    }
}
