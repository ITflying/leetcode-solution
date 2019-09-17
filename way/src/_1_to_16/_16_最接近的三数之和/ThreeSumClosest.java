package _1_to_16._16_最接近的三数之和;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * @date 2019/9/16
 **/
public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};

        System.out.println("数字数组为：" + Arrays.toString(nums));
        System.out.println("与 target 最接近的三个数的和为：" + threeSumClosest_O2(nums, 1));
    }

    private static int threeSumClosest_O1(int[] nums, int target) {
        Integer closest = null;
        int closestDiff = Integer.MAX_VALUE, sum, diff;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    sum = nums[i] + nums[j] + nums[k];
                    diff = Math.abs(target - sum);
                    if (closest == null || diff < closestDiff) {
                        if (diff == 0) {
                            return sum;
                        }
                        closest = sum;
                        closestDiff = diff;
                    }
                }
            }
        }
        return closest;
    }

    private static int threeSumClosest_O2(int[] nums, int target) {
        // 1. 先排序
        Arrays.sort(nums);

        // 2. 从中间往右遍历
        int len = nums.length;
        Integer sum = null, diff = null;
        for (int i = 0; i < len; i++) {
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int tempSum = nums[i] + nums[left] + nums[right];
                int tempDiff = Math.abs(target - tempSum);
                if (sum == null || tempDiff < diff) {
                    sum = tempSum;
                    diff = tempDiff;
                }
                if (tempSum > target) {
                    right--;
                } else if (tempSum < target) {
                    left++;
                } else {
                    return sum;
                }
            }
        }
        return sum;
    }

    // region 抄作业时间，题解的基本思路就是十五题的思路，双指针循环的思路来做的

    /**
     * 最快的方法：感觉就是增加两个判断方法，更快的抛出结果
     */
    private static int threeSumClosest_fastest(int[] nums, int target) {
        if (nums.length < 3) {
            return target;
        } else if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        } else {
            int res = nums[0] + nums[1] + nums[2];
            int cSum;

            // 1. 先排序
            Arrays.sort(nums);

            // 2. 再循环数组
            for (int i = 0; i < nums.length - 2; i++) {
                int j = i + 1;
                int k = nums.length - 1;

                // 1. 最后面的两个数字和当前数字的小于目标值
                if ((nums[i] + nums[nums.length - 2] + nums[nums.length - 1]) < target) {
                    cSum = nums[i] + nums[nums.length - 2] + nums[nums.length - 1];
                    res = check(target, res, cSum);
                    continue;
                }
                // 2. 当前数字和后面的两个数字如果大于目标值则直接返回数据
                if ((nums[i] + nums[i + 1] + nums[i + 2]) > target) {
                    cSum = nums[i] + nums[i + 1] + nums[i + 2];
                    return check(target, res, cSum);
                }

                // 3. 双指针法遍历
                while (j < k) {
                    cSum = nums[i] + nums[j] + nums[k];
                    if (cSum == target) {
                        return cSum;
                    }
                    res = check(target, res, cSum);
                    if (cSum < target) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
            return res;
        }
    }

    /**
     * 比较大小获取接近的值
     */
    private static int check(int target, int res, int cSum) {
        if (Math.abs(target - cSum) < Math.abs(target - res)) {
            res = cSum;
        }
        return res;
    }


    // endregion
}
