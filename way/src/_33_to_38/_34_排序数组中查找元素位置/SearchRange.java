package _33_to_38._34_排序数组中查找元素位置;

import java.rmi.ServerException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 * 进阶：
 * 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * 提示：
 * 0 <= nums.length <= 105
 * -109<= nums[i]<= 109
 * nums是一个非递减数组
 * -109<= target<= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/03/24
 **/
public class SearchRange {
    public static void main(String[] args) throws ServerException {
        int[] nums = new int[]{5};
        int target = 5;

        int[] ret = searchRange_04(nums, target);
        System.out.println("[" + ret[0] + "," + ret[1] + "]");
    }

    /**
     * for
     */
    private static int[] searchRange_01(int[] nums, int target) {
        int[] ret = new int[]{-1, -1};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (ret[0] == -1) {
                    ret[0] = i;
                    ret[1] = i;
                } else {
                    ret[1] = i;
                }
            }
        }

        return ret;
    }

    /**
     * 二分
     */
    private static int[] searchRange_02(int[] nums, int target) {
        int[] ret = new int[]{-1, -1};
        int len = nums.length;
        if (len == 0) return ret;

        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                while (mid >= 0 && nums[mid] == target) {
                    ret[0] = ret[1] = mid;
                    mid--;
                }
                mid = (left + right) / 2;
                while (mid < len && nums[mid] == target) {
                    ret[1] = mid;
                    mid++;
                }
                return ret;
            } else if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ret;
    }

    /**
     * 官方二分
     */
    private static int[] searchRange_03(int[] nums, int target) {
        int left = bs(nums, target, true);
        int right = bs(nums, target, false) - 1;
        if (left <= right && right < nums.length && nums[left] == target && nums[right] == target) {
            return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }

    private static int bs(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ret = nums.length;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ret = mid;
            } else {
                left = mid + 1;
            }
        }
        return ret;
    }

    /**
     * 递归 ryan
     * 羡慕会用递归的
     */
    private static int[] searchRange_04(int[] nums, int target) {
        return recur(0, nums.length - 1, nums, target);
    }

    private static int[] recur(int left, int right, int[] nums, int target) {
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                if (nums[left] != target) {
                    left = recur(left + 1, mid, nums, target)[0];
                }
                if (nums[right] != target) {
                    right = recur(mid, right - 1, nums, target)[1];
                }
                return new int[]{left, right};
            }
        }
        return new int[]{-1, -1};
    }
}
