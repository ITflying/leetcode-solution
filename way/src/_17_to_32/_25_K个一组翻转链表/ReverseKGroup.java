package _17_to_32._25_K个一组翻转链表;

import Common.ListNode;

import java.rmi.ServerException;
import java.util.Objects;

/**

 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 k 是一个正整数，它的值小于或等于链表的长度。
 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

 示例：
 给你这个链表：1->2->3->4->5
 当 k = 2 时，应当返回: 2->1->4->3->5
 当 k = 3 时，应当返回: 3->2->1->4->5

 说明：
 你的算法只能使用常数的额外空间。
 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @date 2021/02/23
 **/
public class ReverseKGroup {
    public static void main(String[] args) throws ServerException {
        ListNode d = new ListNode(5);
        ListNode c = new ListNode(4, d);
        ListNode b = new ListNode(3, c);
        ListNode a = new ListNode(2, b);
        ListNode begin = new ListNode(1);
        begin.next = a;
        begin.printAllVal();

        ListNode swapNode = reverseKGroup_03(begin, 3);
        swapNode.printAllVal();
    }

    /**
     * 相当于n个小区域链表翻转
     * 1、切割出小区域
     * 2、把小区域链表翻转再拿回到链表中
     * 3、链表指向下一个
     * PS：不难，但是考虑头节点和后面节点区别
     */
    public static ListNode reverseKGroup_01(ListNode head, int k) {
        if (Objects.isNull(head) || Objects.isNull(head.next) || k <= 1) {
            return head;
        }
        ListNode ret = new ListNode(-1);
        ret.next = head;
        ListNode pre = ret;
        ListNode[] nodes = new ListNode[k];
        boolean flagOfFirst = false, flag = false;

        while (Objects.nonNull(pre)) {
            // 1. 切割出小区域
            ListNode begin = pre;
            int i;
            for (i = 0; i < k; i++) {
                nodes[i] = pre.next;
                pre = pre.next;
                if (Objects.isNull(pre)) {
                    break;
                }
            }
            if (i != k) {
                break;
            }

            // 2. 记录头节点位置和下一次翻转起始位置
            if (!flagOfFirst) {
                ret.next = nodes[k - 1];
                flagOfFirst = true;
            }

            // 3. 小区域翻转
            ListNode tempNext = pre.next;
            for (i = k - 1; i >= 0; i--) {
                if (i != 0) {
                    nodes[i].next = nodes[i - 1];
                } else {
                    nodes[i].next = tempNext;
                    if (flag) {
                        // 头节点之外的节点要指向下一个翻转节点的开始
                        begin.next = nodes[k - 1];
                    } else {
                        flag = true;
                    }
                    pre = nodes[i];
                }
            }
        }
        return ret.next;
    }

    /**
     * head前面没有hair
     */
    public static ListNode reverseKGroup_02(ListNode head, int k) {
        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode pre = hair;

        while (Objects.nonNull(head)) {
            // 循环判断是否符合k个数据
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (Objects.isNull(tail)) {
                    return hair.next;
                }
            }

            // nex:记录翻转链表的下一个位置
            ListNode nex = tail.next;
            // head:起始位置，tail终止位置
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            // 下一次循环
            pre = tail;
            head = tail.next;
        }
        return hair.next;
    }

    private static ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        // 相当于不断从最前面关联最后面的节点
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        // 返回新的开始节点和终止节点，其实就是入参反过来
        return new ListNode[]{tail, head};
    }


    /**
     * 递归实现
     * 大致过程可以分解为
     * 1、找到待翻转的k个节点（注意：若剩余数量小于 k 的话，则不需要反转，因此直接返回待翻转部分的头结点即可）。
     * 2、对其进行翻转。并返回翻转后的头结点（注意：翻转为左闭右开区间，所以本轮操作的尾结点其实就是下一轮操作的头结点）。
     * 3、对下一轮 k 个节点也进行翻转操作。
     * 4、将上一轮翻转后的尾结点指向下一轮翻转后的头节点，即将每一轮翻转的k的节点连接起来。
     * <p>
     * 作者：reedfan
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/di-gui-java-by-reedfan-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static ListNode reverseKGroup_03(ListNode head, int k) {
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (Objects.isNull(tail)) {
                return head;
            }
            tail = tail.next;
        }
        // 翻转k个元素，最开始的节点反转
        ListNode newHead = reverse(head, tail);
        // 下一轮开始的地方就是tail
        head.next = reverseKGroup_03(tail, k);

        return newHead;
    }

    private static ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}

