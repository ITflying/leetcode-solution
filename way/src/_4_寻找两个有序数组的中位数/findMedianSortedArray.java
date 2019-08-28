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
public class findMedianSortedArray {

    public static void main(String[] args) {
        int[] num1 = new int[]{0, 0};
        int[] num2 = new int[]{0,0 };
        System.out.println("中位数为：" + findMedianSortedArrays_02(num1, num2));
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
     * 第三种方式：递归方法
     */
    public static double findMedianSortedArrays_03(int[] nums1, int[] nums2) {
        return 0.0d;
    }
    
    // endregoin
}
