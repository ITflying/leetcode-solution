package _33_to_48._41_缺失的第一个正数;

import java.rmi.ServerException;
import java.util.*;

/**
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用[一次]。
 * <p>
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 示例1:
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例2:
 * 输入: candidates =[2,5,2,1,2], target =5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/03/29
 **/
public class FirstMissingPositive {
    public static void main(String[] args) throws ServerException {
        int[] candiadates = new int[]{2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum2_01(candiadates, target));
    }

    /**
     *
     */
    private static List<List<Integer>> combinationSum2_01(int[] candidates, int target) {
        // TODO: 2021/4/1 waiting for self solution
        return null;
    }

    /**
     * 易碎版本
     */
    private static List<List<Integer>> combinationSum2_02(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();

        // 排序方便剪枝
        Arrays.sort(candidates);

        // 剪枝回溯
        dfs_02(res, path, candidates, 0, target);
        return res;
    }

    private static void dfs_02(List<List<Integer>> res, Deque<Integer> path, int[] nums, int index, int target) {
        // 如果符合条件返回
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 超过限制
        if (index >= nums.length) return;
        // 可用的长度
        List<Integer> pre = new ArrayList<>(nums.length);
        // 遍历决定每一个位置的数字的值
        for (int i = index; i < nums.length; i++) {
            if (pre.contains(nums[i])) continue;
            // 剪枝，快速失败机制
            if (target - nums[i] < 0) break;
            // 增加数据
            path.addLast(nums[i]);
            pre.add(nums[i]);
            // 递归下一个数字
            dfs_02(res, path, nums, i + 1, target - nums[i]);
            path.removeLast();
        }
    }


}


