package _17_to_32._31_下一个排列;

import java.rmi.ServerException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中[下一个更大]的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/03/16
 **/
public class NextPermutation {
    public static void main(String[] args) throws ServerException {
        int[] nums1 = new int[]{1, 2, 3, 8, 5};

        nextPermutation_02(nums1);

        for (int i : nums1) {
            System.out.print(i + "\t");
        }
    }

    /**
     * 下一个更大的排列
     * todo:想到用双指针来调换位置，但是不会用
     */
    private static void nextPermutation_01(int[] nums) {
    }

    /**
     * 简而言之：从左查找,最右替换,然后剩余升序(翻转)
     * 关键在于理解左边遇到小的，右边遇到比左边大一点的互换位置后再升序得到下一个更小的值
     */
    private static void nextPermutation_02(int[] nums) {
        int i = nums.length - 2;

        // 从最右往左遍历出前者比后者小的的数字位置
        while (i >= 0 && nums[i] >= nums[i+1] ) {
            i--;
        }

        // 找到右边相对较大的数字交换位置
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]){
                j--;
            }
            swap(nums, i, j);
        }

        // 从交换位置后一个逆序，从小到大，因为i的遍历是从左到右，从小到大，逆序后会得到较小的数字
        reverse(nums, i+1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }



}
