package _5_排序;

import java.util.Objects;

/**
 * @author
 * @date 2020/6/9
 **/
public class Sort {
    public static void main(String[] args) {
        System.out.println("以下均为O(n^2)的排序方法：");
        System.out.println("1、冒泡排序");
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        nums = bubbleSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }

        System.out.println("\n2、选择排序");
        nums = new int[]{1, 0, -1, 0, -2, 2};
        nums = selectionSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }

        System.out.println("\n3、直接插入排序");
        nums = new int[]{1, 0, -1, 0, -2, 2};
        nums = insertSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }

        System.out.println("\n\n以下均为O(nlogn)的排序方法：");
        System.out.println("4、希尔排序-插入排序");
        nums = new int[]{1, 0, -1, 0, -2, 2};
        nums = shellInsertSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }

        System.out.println("\n5、快排");
        nums = new int[]{1, 0, -1, 0, -2, 2};
        nums = quickSort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }

        System.out.println("\n6、归并排序");
        nums = new int[]{1, 0, -1, 0, -2, 2};
        mergeSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }

        System.out.println("\n7、堆排序");
        nums = new int[]{1, 0, -1, 0, -2, 2};
        mergeSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }
    }


    /**
     * 冒泡排序
     *
     * @param nums
     * @return
     */
    public static int[] bubbleSort(int[] nums) {
        if (Objects.nonNull(nums) && nums.length > 0) {
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] < nums[i]) {
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                    }
                }
            }
        }
        return nums;
    }

    /**
     * 选择排序
     * 其实也是for循环，相对于冒泡不稳定，不断把最小的/最大的放在一侧
     * O(n^2)
     *
     * @param nums
     * @return
     */
    public static int[] selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[index]) {
                    index = j;
                }
            }
            if (index != i) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }
        return nums;
    }

    /**
     * 插入排序
     * 不断将数据插入到已排列好的序列当中，第一层循环往后，如果判断到有值小于有序序列最大值，那么开启第二层循环
     * 第二层循环用于在有序序列找到对应的位置替换，并且在寻找的过程中将所有值往后移动一位
     * 适用于小规模数据和基本有序的数据
     * O(n^2)
     *
     * @param nums
     * @return
     */
    public static int[] insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                int j;
                int temp = nums[i];
                for (j = i - 1; j >= 0 && temp < nums[j]; j--) {
                    nums[j + 1] = nums[j];
                }
                nums[j + 1] = temp;
            }
        }
        return nums;
    }

    /**
     * 希尔插入排序
     * 先将待排记录序列分割成为若干子序列分别进行插入排序，待整个序列中的记录"基本有序"时，再对全体记录进行一次直接插入排序。
     * 有增量，增量可以用 H=n/2来计算
     * 跳跃性插入，不稳定
     * 类似于 1 3 5 7 9
     * 2 4 6 8 10的排序方式
     * O(nlogn)，不同增量序列的复杂度是不同的
     * Hibbard提出了另一个增量序列{1,3,7，...,2^k-1}，这种序列的时间复杂度(最坏情形)为O(n^1.5)
     * Sedgewick提出了几种增量序列，其最坏情形运行时间为O（n^1.3）,其中最好的一个序列是{1,5,19,41,109,...}
     *
     * @param nums
     * @return
     */
    public static int[] shellInsertSort(int[] nums) {
        int len = nums.length;
        int d = len;
        while (d > 1) {
            d = d / 3 + 1;
            // 1. 第一次循环，用于将分隔的序列排序
            for (int i = 0; i < d; i++) {
                // 2. 第二层循环，其实就是直接插入排序，不过要带上增量值
                for (int j = i + d; j < len; j = j + d) {
                    if (nums[j] < nums[j - d]) {
                        int k, temp = nums[j];
                        for (k = j - d; k >= 0 && temp < nums[k]; k = k - d) {
                            nums[k + d] = nums[k];
                        }
                        nums[k + d] = temp;
                    }
                }
            }
        }
        return nums;
    }

    /**
     * 快速排列
     * 其思路就是挑选一个位置作为基准数，然后让哨兵从左右两边开始依次将比它大的放右边，小的放左边，然后再对左右边的数据重复该过程
     * 这里面包含的双指针是很多算法的解决思路
     *
     * @param nums
     * @return
     */
    public static int[] quickSort(int[] nums, int l, int r) {
        if (l < r) {
            // 1. 找出支点位置，并将小的放左边，大的放右边
            int i = l, j = r, temp = nums[l];
            while (i < j) {
                // 从右往左，寻找第一个小于temp的值
                while (i < j && nums[j] > temp) {
                    j--;
                }
                if (i < j) {
                    nums[i++] = nums[j];
                }
                // 从左往右，寻找第一个大于temp的值
                while (i < j && nums[i] < temp) {
                    i++;
                }
                // 交换位置
                if (i < j) {
                    nums[j--] = nums[i];
                }
            }

            // 2.递归左边的和右边的
            quickSort(nums, l, i - 1);
            quickSort(nums, i + 1, r);
        }
        return nums;
    }


    /**
     * 归并排序（好难写代码）
     * 为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列
     * 照抄 https://zhuanlan.zhihu.com/p/36075856
     *
     * @param nums
     * @return
     */
    public static void mergeSort(int[] nums) {
        if (Objects.isNull(nums) || nums.length <= 0) {
            return;
        }
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
    }

    public static void mergeSort(int[] nums, int l, int r, int[] temp) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(nums, l, mid, temp); // 递归归并左边的的元素
            mergeSort(nums, mid + 1, r, temp); // 递归归并右边的元素
            mergeSort(nums, l, mid, r, temp);//将第二个有序序列合并
        }
    }

    /**
     * 合并两个有序数列
     * num[l] ~ num[mid]为第一组
     * num[mid+1] ~ num[r]为第二组
     */
    public static void mergeSort(int[] nums, int l, int mid, int r, int[] temp) {
        int i = l, j = mid + 1; // i 为第一组起点，j 为第二组起点
        int m = mid, n = r;   // m 为第一组终点，n 为第二组终点
        // k用于指向temp数组当前放到哪个位置
        int k = 0;

        // 1. 将两个有序序列循环比较，填入数组temp
        while (i <= m && j <= n) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 2. 如果比较完毕，第一组或第二组数字还有剩余，则全部填入temp中
        while (i <= m) {
            temp[k++] = nums[i++];
        }
        while (j <= n) {
            temp[k++] = nums[j++];
        }

        // 3. 将排序好的数回填到nums数组的对应位置
        for (i = 0; i < k; i++) {
            nums[l + i] = temp[i];
        }
    }
}
