package _33_to_48._39_组合总和;

import java.rmi.ServerException;
import java.util.*;

/**
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的数字可以【无限制重复】被选取。
 * <p>
 * 说明：
 * 所有数字（包括target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * <p>
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/03/29
 **/
public class CombinationSum {
    public static void main(String[] args) throws ServerException {
        int[] candiadates = new int[]{2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum_02(candiadates, target));
    }

    /**
     * 迭代就回溯
     * 官方回溯
     */
    private static List<List<Integer>> combinationSum_01(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs_01(candidates, target, ret, combine, 0);

        return ret;
    }

    private static void dfs_01(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        dfs_01(candidates, target, ans, combine, idx + 1);
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs_01(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }

    /**
     * 带剪枝的回溯
     */
    private static List<List<Integer>> combinationSum_02(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs_02(candidates, 0, len, target, path, res);
        return res;
    }

    private static void dfs_02(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            if (target - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            dfs_02(candidates, i, len, target - candidates[i], path, res);
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }

}


