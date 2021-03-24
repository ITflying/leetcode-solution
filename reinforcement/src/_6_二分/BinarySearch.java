package _6_二分;

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int target = 3;
        System.out.println(simple(nums, target));
    }

    private static int simple(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return -1;

        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
