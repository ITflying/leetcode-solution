package _1_to_16._15_三数之和;

import java.util.*;

/**
 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

 注意：答案中不可以包含重复的三元组。

 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

 满足要求的三元组集合为：
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 * @date 2019/9/11 
 **/
public class SumOfThreeNum {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,10,-14,11,5,-4,2,0,-10,-10,5,7,-11,10,-2,-5,2,12,-5,14,-11,-15,-5,12,0
                ,13,8,7,10,6,-9,-15,1,14,11,-9,-13,-10,6,-8,-5,-11,6,-9,14,11,-7,-6,8,3,-7,5,-5,3,2,10,-6,
                -12,3,11,1,1,12,10,-8,0,8,-5,6,-8,-6,8,-12,-14,7,9,12,-15,-12,-2,-4,-4,-12,6,7,-3,-6,-14,
                -8,4,4,9,-10,-7,-4,-3,1,11,-1,-8,-12,9,7,-9,10,-1,-14,-1,-8,11,12,-5,-7};
        System.out.println("数字数组为："+ Arrays.toString(nums));
        System.out.println("满足条件的三元组集合为：" + threeSum_01(nums).toString());
    }

    /**
     * 第一种：会超出时间限制，但不考虑时间应该可以通过测试
     */
    private static List<List<Integer>> threeSum_01(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Set<String> numStr = new HashSet<>();
        if (nums == null || nums.length < 3) {
            return ret;
        }
        String temp;
        int[] tempArr;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    tempArr = new int[]{nums[i], nums[j], nums[k]};
                    Arrays.sort(tempArr);
                    temp = Arrays.toString(tempArr);
                    if (numStr.add(temp) && nums[i] + nums[j] + nums[k] == 0) {
                        ret.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                    }
                }
            }
        }
        return ret;
    }
    
    // region 抄作业时间

    /**
     * 第二种方法：左右坐标法
     * 模拟上一道题的左右指针法，而且只需要找到接近的，相对简单点
     */
    private static List<List<Integer>> threeSum_02(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (len < 3) {
            return ans;
        }

        // 1. 排序
        Arrays.sort(nums);

        // 2. for循环，两端判断
        for (int i = 0; i < len; i++) {
            // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (nums[i] > 0) {
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }
    
    // endregion

}
