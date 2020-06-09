package _17_to_32._18_四数之和;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @date 2020/6/9
 **/
public class ForNumberSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        int target = -1;
        System.out.println(fourSum(nums, target));
    }

    /**
     * 第一种解法：
     * 三数之和用的是双指针，但想不起怎么做的，就想到也可以用for循环
     * 虽然时间有限制，但是利用最大值和最小值可以过滤一部分for循环 O(N^4)
     * 执行用时 :65 ms, 在所有 Java 提交中击败了7%的用户
     * 内存消耗 :40.6 MB, 在所有 Java 提交中击败了10.53%的用户
     * 双指针也就图一乐，真正打通leetcode还得靠for循环
     */
    public static List<List<Integer>> fourSum1(int[] nums, int target) {
        // 1. 校验
        List<List<Integer>> ret = new ArrayList<>();
        if (Objects.isNull(nums) || nums.length < 4) {
            return ret;
        }

        // 2. 循环排列
        Arrays.sort(nums);

        // 3.快速失败机制
        int len = nums.length;
        int min = nums[0] + nums[1] + nums[2] + nums[3];
        int max = nums[len - 1] + nums[len - 2] + nums[len - 3] + nums[len - 4];
        if (min > target || max < target) {
            return ret;
        }

        // 4. 挑选四个出来判断是否符合条件
        List<String> checkStr = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        int tempSum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (tempSum < target) {
                            continue;
                        }
                        if (tempSum > target) {
                            break;
                        }

                        String tempStr = nums[i] + "" + nums[j] + "" + nums[k] + "" + nums[l];
                        if (!checkStr.contains(tempStr) && tempSum == target) {
                            checkStr.add(tempStr);
                            List<Integer> tempList = new ArrayList<>();
                            tempList.add(nums[i]);
                            tempList.add(nums[j]);
                            tempList.add(nums[k]);
                            tempList.add(nums[l]);
                            ret.add(tempList);
                        }// for l
                    }// for k
                }// fro j
            }// for i
        }
        return ret;
    }


    /**
     * 第二种 题解：
     * 双指针实现
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        // 1. 校验
        List<List<Integer>> ret = new ArrayList<>();
        if (Objects.isNull(nums) || nums.length < 4) {
            return ret;
        }

        // 2. 循环排列
        Arrays.sort(nums);

        // 3.双指针判断
        int len = nums.length;
        // 定义4个指针: k i j l ，k从0遍历，i从k+1遍历，j指向i+1,h指向最大值
        for (int k = 0; k < len - 3; k++) {
            // 4.快速失败机制：重复跳过，最大最小快速过滤
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int min = nums[k] + nums[k + 1] + nums[k + 2] + nums[k + 3];
            if (min > target) {
                return ret;
            }
            int max = nums[k] + nums[len - 1] + nums[len - 2] + nums[len - 3];
            if (max < target){
                continue;
            }

            // 5. 第二次循环
            for (int i = k + 1; i < len - 2; i++) {
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                min = nums[k] + nums[i] + nums[i + 1] + nums[i + 2];
                max = nums[k] + nums[i] + nums[len - 1] + nums[len - 2];
                if (min > target || max < target) {
                    continue;
                }

                // 开始指针j和l的操作
                int j = i + 1;
                int l = len - 1;
                while (j < l) {
                    int cur = nums[k] + nums[i] + nums[j] + nums[l];
                    if (cur == target) {
                        ret.add(Arrays.asList(nums[k], nums[i], nums[j], nums[l]));
                        j++;
                        while (j < l && nums[j] == nums[j - 1]) {
                            j++;
                        }
                        l--;
                        while (j < l && i < l && nums[l] == nums[l + 1]) {
                            l--;
                        }
                    } else if (cur < target) {
                        j++;
                    } else {
                        l--;
                    }
                }
            }
        }
        return ret;
    }
}
