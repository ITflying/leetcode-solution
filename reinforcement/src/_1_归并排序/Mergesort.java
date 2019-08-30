package _1_归并排序;

import java.util.logging.Level;

/**
 * 算法采用分治法，且各层分治递归。
 * 归并排序的核心思想是将两个有序的数列合并成一个大的有序的序列。通过递归，层层合并，即为归并。
 * 这个需要使用到额外的存储空间，用空间换时间
 * 首先将原数组拆分为最小的数组(2个数据的数组)，然后再一层层往上合并成排序好的数组；分治，先分再治
 * @date 2019/8/30 
 **/
public class Mergesort {

    public static void main(String args[]) {
        int arr[] = new int[]{7, 4, 2, 10};
        System.out.println("之前：");
        for (int i : arr) {
            System.out.print(i + "\t");
        }

        System.out.println("\n之后：");
        mergeSort(arr);
        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

    public static void mergeSort(int arr[]) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        // 分：不断切割一半出来进行
        int mid = left + ((right - left) >> 1);
        sort(arr, left, mid);
        sort(arr, mid + 1, right);

        // 治：合并排序数据
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;

        // 将数据存储到临时数组当中，按从小到大的顺序来调换左右两个部分的数据
        while (p1 <= mid && p2 <= right) {
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 这里是将剩余的数据也填充到临时数组当中
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= right) {
            temp[i++] = arr[p2++];
        }
        
        // 把最终排序结果交给原数组
        for (i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
    }
}
