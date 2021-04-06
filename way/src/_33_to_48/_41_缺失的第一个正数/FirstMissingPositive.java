package _33_to_48._41_缺失的第一个正数;

import java.rmi.ServerException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 
 * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 * 
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * 
 * 提示：
 * 0 <= nums.length <= 300
 * -231 <= ums[ni] <= 231 - 1
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/04/02
 **/
public class FirstMissingPositive {
    public static void main(String[] args) throws ServerException {
        int[] candiadates = new int[]{1, 2, 0};
        System.out.println(firstMissingPositive_02(candiadates));
    }

    /**
     * 迭代
     * 这是n^2的时间复杂度, for和contains
     */
    private static int firstMissingPositive_01(int[] nums) {
        int max = Integer.MAX_VALUE;
        List<Integer> temp = Arrays.stream(nums).boxed().collect(Collectors.toList());
        for (int i = 1; i < max; i++) {
            if (!temp.contains(i)) {
                return i;
            }
        }
        return 1;
    }


    /**
     * 哈希
     */
    private static int firstMissingPositive_02(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = len + 1;
            }
        }

        for (int i = 0; i < len; i++) {
            int num = Math.abs(nums[i]);
            if (num <= len) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return len + 1;
    }


}


