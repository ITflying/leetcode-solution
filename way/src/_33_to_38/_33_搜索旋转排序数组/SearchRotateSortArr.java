package _33_to_38._33_搜索旋转排序数组;

import java.rmi.ServerException;

/**
 整数数组 nums 按升序排列，数组中的值 互不相同 。
 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的索引，否则返回-1。

 示例 1：
 输入：nums = [4,5,6,7,0,1,2], target = 0
 输出：4
 示例2：
 输入：nums = [4,5,6,7,0,1,2], target = 3
 输出：-1
 示例 3：
 输入：nums = [1], target = 0
 输出：-1

 提示：
 1 <= nums.length <= 5000
 -10^4 <= nums[i] <= 10^4
 nums 中的每个值都 独一无二
 nums 肯定会在某个点上旋转
 -10^4 <= target <= 10^4

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/03/23
 **/
public class SearchRotateSortArr {
    public static void main(String[] args) throws ServerException {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search_02(nums, target));
    }

    /**
     * 暴力？居然通过了
     */
    private static int search_01(int[] nums, int target) {
        for (int i = 0; i < nums.length;i++){
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 因为题目里面提示比较多，可以减少计算量
     * 因为是有序的独一无二，所以使用二分
     */
    private static int search_02(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[0] <= nums[mid]) {
                // 升序
               if (nums[0] < target && target < nums[mid]) {
                   right = mid - 1;
               } else {
                   left = mid + 1;
               }
            } else {
                // 开始值比中间值大，意味着中间存在反转
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

}
