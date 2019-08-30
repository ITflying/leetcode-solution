package _4_寻找两个有序数组的中位数;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * 核心：
 * @date 2019/8/28 11:34
 **/
public class FindMedianSortedArray {

    public static void main(String[] args) {
        int[] num1 = new int[]{2};
        int[] num2 = new int[]{};
        System.out.println("中位数为：" + findMedianSortedArrays_04(num1, num2));
    }

    /**
     * 最简单的方法：把两个合并起来然后取中位数，但是时间和复杂度其实是O(n)吧，然后时间花费了54ms
     */
    public static double findMedianSortedArrays_01(int[] nums1, int[] nums2) {
        List<Integer> list1 = Arrays.stream( nums1 ).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream( nums2 ).boxed().collect(Collectors.toList());
        list1.addAll(list2);
        list1 = list1.stream().sorted().collect(Collectors.toList());
        
        int size = list1.size();
        int midPos = list1.size() / 2;
        if (size % 2 ==0){
            return (list1.get(midPos-1) + list1.get(midPos)) / 2.0;
        }else{
            return (double)list1.get(midPos); 
        }
    }

    /**
     * 第二种方式：循环构造，到一半就结束 o((m+n)/2)，6ms，o(￣▽￣)ｄ，直线思维，复杂度达不到要求，但还是通过了
     * 这其实有点像归并排序的合并部分
     */
    public static double findMedianSortedArrays_02(int[] nums1, int[] nums2) {
        int sumLen = nums1.length + nums2.length;
        int num = 0;
        int left = 0, right = 0;
        int pos = (int) Math.ceil(sumLen / 2.0);
        for (int i = 0; i < pos; i++){
            if (right >= nums2.length || (left < nums1.length && nums1[left] < nums2[right]) ){
                num= nums1[left];
                left++;
            }else{
                num= nums2[right];
                right++;
            }
        }
        if (sumLen % 2 == 0){
            int sumNum = (right >= nums2.length || (left < nums1.length && nums1[left] < nums2[right])) ? nums1[left] : nums2[right];
            return (num + sumNum) / 2.0;
        }else{
            return (double)num;
        }
    }

    // region 抄作业时间

    /**
     * 第三种方式：数据推导法
     * 官方解法：先用数学推导得出处理基本逻辑，再得出公式，然后考虑边界情况。
     *           就跟解数学题目一样，读题列公式考虑边界情况，解答。
     * 脑子说你看懂了，敲代码的时候手说不你没懂
     */
    public static double findMedianSortedArrays_03(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 1. 保证num1的长度小于等于nums2
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }

        // m+n+1，之所以+1是为了防止越界的问题
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;

            if (i < iMax && nums2[j - 1] > nums1[i]) {
                // 如果nums2[j−1]>nums1[i]，说明i太小了，需要加大，达到nums2[j−1]≤nums1[i]
                iMin = i + 1;
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                // 说明i太大了，需要减少，使其达到nums1[i-1]<=nums2[j]
                iMax = i - 1;
            } else {
                int maxLeft = 0;
                // 考虑边界情况
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if (((m + n) % 2 == 1)) {
                    // 奇数
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0.0d;
    }

    /**
     * 第四种方法：递归循环二分丢弃，设定一个值，不断的二分查询数据
     * 这个思路很有趣，首先确定要切割的位置除以，然后对两个数组进行切割，
     * 选择切割位置数值小的数组，说明之前不可能是第k小的数据
     * 如果k=1，则可以对上下两个数组数据值进行对比
     * 如果k!=1，则不可以进行对比
     * 这个想法很巧妙的是不断把小的数组切割掉
     */
    public static double findMedianSortedArrays_04(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // 确定切割位置
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;

        // 因为会遇到偶数和奇数的情况，所以请求两次，如果是偶数则计算两次，奇数则计算两次相同的K，所以返回值要除以2
        int mid1 = getKth(nums1, 0, m - 1, nums2, 0, n - 1, left);
        int mid2 = getKth(nums1, 0, m - 1, nums2, 0, n - 1, right);

        return (mid1 + mid2) / 2.0;
    }

    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        // 确定切割后的数组长度
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        // 保证数组1的长度小于数组2的长度，方便一直使用数组2的长度
        if (len1 > len2) {
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        // 如果数组1被切割掉了，那么就可以直接返回数组2缺少的数字即可
        if (len1 == 0) {
            // -1是因为坐标从0开始
            return nums2[start2 + k - 1];
        }
        // 如果k==1所以只有一个数字，直接比较上下两个数组当前位置哪个小就选择哪个即可
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        // 重新确定切割起始位置，不断二分，切割完成则变成整个数组长度
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        
        // 这是核心思路，如果切割起始位置的数据，数组1的数据值大于数组2的数据值，
        // 那么说明数组2的数据值之前的所有数据小于当前数据，就不可能是第K小的的数据，就将其切割掉，形成新的数组
        // 用新的数组再进行切割，直到返回数据值，然后k的取值要变成减掉切掉那部分数组长度
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
    
    // endregoin
}
