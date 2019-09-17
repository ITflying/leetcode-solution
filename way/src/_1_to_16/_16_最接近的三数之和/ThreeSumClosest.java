package _1_to_16._16_最接近的三数之和;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 返回这三个数的和。假定每组输入只存在唯一答案。

 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

 * @date 2019/9/16 
 **/
public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,2,1,-4};
        
        System.out.println("数字数组为："+ Arrays.toString(nums));
        System.out.println("与 target 最接近的三个数的和为：" + threeSumClosest_O2(nums, 1));
    }

    private static int threeSumClosest_O1(int[] nums, int target) {
        Integer closest = null;
        int closestDiff = Integer.MAX_VALUE, sum, diff;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    sum = nums[i]+nums[j]+nums[k];
                    diff = Math.abs(target  - sum);
                    if (closest == null || diff < closestDiff){
                        if (diff == 0){
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
        for (int i = 0; i < len; i++){
            int left = i + 1;
            int right = len - 1;
            while (left < right){
                int tempSum =nums[i]+nums[left]+nums[right];
                int tempDiff = Math.abs(target - tempSum);
                if (sum == null || tempDiff < diff){
                    sum = tempSum;
                    diff = tempDiff;
                }
                if (tempSum > target){
                    right--;
                }else if (tempSum < target){
                    left++;
                }else{
                    return sum;
                }
            }
        }
        return sum;
    }
}
