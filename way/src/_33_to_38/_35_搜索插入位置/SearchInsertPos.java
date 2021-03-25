package _33_to_38._35_搜索插入位置;

import java.rmi.ServerException;

/**
 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 你可以假设数组中无重复元素。

 示例 1:
 输入: [1,3,5,6], 5
 输出: 2
 示例2:
 输入: [1,3,5,6], 2
 输出: 1
 示例 3:
 输入: [1,3,5,6], 7
 输出: 4
 示例 4:
 输入: [1,3,5,6], 0
 输出: 0

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/search-insert-position
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/03/25
 **/
public class SearchInsertPos {
    public static void main(String[] args) throws ServerException {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 2;

        int pos = searchInsert_01(nums, target);
        System.out.println("待插入的位置：" + pos);
    }

    /**
     * 排序数组，有序即二分，最后left的位置就是要插入的位置
     * 时间复杂度：O(logn) 空间复杂度：O(1)
     */
    private static int searchInsert_01(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int left = 0, right = nums.length; // 如果right为右闭区间，那么下面的循环需要考虑left == right的情况

        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

  
}
