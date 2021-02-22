package _17_to_32._23_合并K个升序链表;

import Common.ListNode;

import java.rmi.ServerException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 
 * 
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * 
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 * 
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/02/19
 **/
public class MergeKLists {
    public static void main(String[] args) throws ServerException {
        ListNode b = new ListNode(5);
        ListNode a = new ListNode(4, b);
        ListNode begin = new ListNode(1);
        begin.next = a;

        ListNode B = new ListNode(4);
        ListNode A = new ListNode(3, B);
        ListNode BEGIN = new ListNode(1);
        BEGIN.next = A;

        ListNode A2 = new ListNode(6);
        ListNode BEGIN2 = new ListNode(2);
        BEGIN2.next = A2;

        ListNode[] lists = new ListNode[3];
        lists[0] = begin;
        lists[1] = BEGIN;
        lists[2] = BEGIN2;

        ListNode node = mergeKLists_04(lists);
        node.printAllVal();
    }

    /**
     * 把值塞进新的List，然后拿出来排列
     * 或者两两比较，一直到最后比完了也就合并完成了
     * 19 ms
     */
    private static ListNode mergeKLists_01(ListNode[] lists) {
        List<ListNode> allNode = new ArrayList<>();

        // 1、把节点全都拿出来
        for (ListNode node : lists) {
            if (Objects.nonNull(node)) {
                while (Objects.nonNull(node)) {
                    allNode.add(node);
                    node = node.next;
                }
            }
        }

        if (allNode.size() > 0) {
            // 2. 按从小到大排序
            allNode = allNode.stream().sorted(Comparator.comparing(x -> x.val)).collect(Collectors.toList());
            ListNode ret = new ListNode(-1);
            ListNode pre = ret;
            for (ListNode node : allNode) {
                pre.next = node;
                pre = node;
            }

            return ret.next;
        } else {
            return null;
        }
    }

    /**
     * 另一种方式的节点拿出来遍历
     */
    private static ListNode mergeKLists_02(ListNode[] lists) {
        List<Integer> allData = new ArrayList<>();

        // 1、把值全都拿出来
        for (ListNode node : lists) {
            if (Objects.nonNull(node)) {
                ListNode pre = node;
                while (Objects.nonNull(pre)) {
                    allData.add(pre.val);
                    pre = pre.next;
                }
            }
        }

        // 2. 按从小到大排序
        allData = allData.stream().sorted().collect(Collectors.toList());

        // 3. 按顺序构造新的值
        if (allData.size() > 0) {
            ListNode res = new ListNode(-1);
            ListNode pre = res;
            for (Integer data : allData) {
                ListNode node = new ListNode(data);
                pre.next = node;
                pre = node;
            }
            return res.next;
        } else {
            return null;
        }
    }

    /**
     * 两两合并
     * 耗时	298 ms，内存占用也大于上面两种
     */
    private static ListNode mergeKLists_03(ListNode[] lists) {
        ListNode res = null;
        for (int i = 0; i < lists.length; i++) {
            res = mergerTwoLists(res, lists[i]);
        }
        return res;
    }

    private static ListNode mergerTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        } else if (a.val < b.val) {
            a.next = mergerTwoLists(a.next, b);
            return a;
        } else {
            b.next = mergerTwoLists(a, b.next);
            return b;
        }
    }

    /**
     * 分而治之，也就是二分法
     * 3ms
     */
    private static ListNode mergeKLists_04(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private static ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergerTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    /**
     * 最优队列
     *
     * @param lists
     * @return
     */
    private static ListNode mergeKLists_05(ListNode[] lists) {
        for (ListNode node : lists) {
            if (Objects.nonNull(node)) {
                queue.offer(new Status(node.val, node));
            }
        }

        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }

    // PriorityQueue是基于优先堆的一个无界队列，这个优先队列中的元素可以默认自然排序或者通过提供的Comparator（比较器）在队列实例化的时排序
    static PriorityQueue<Status> queue = new PriorityQueue<>();

    static class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

}

