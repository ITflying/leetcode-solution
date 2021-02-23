package _17_to_32._24_两两交换链表中的节点;

import Common.ListNode;

import java.rmi.ServerException;
import java.util.Objects;

/**
 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。


 示例 1：
 输入：head = [1,2,3,4]
 输出：[2,1,4,3]
 示例 2：
 输入：head = []
 输出：[]
 示例 3：
 输入：head = [1]
 输出：[1]

 提示：
 链表中节点的数目在范围 [0, 100] 内
 0 <= Node.val <= 100

 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/02/22
 **/
public class SwapPairs {
    public static void main(String[] args) throws ServerException {
//        ListNode d = new ListNode(5);
        ListNode c = new ListNode(4);
        ListNode b = new ListNode(3, c);
        ListNode a = new ListNode(2, b);
        ListNode begin = new ListNode(1);
        begin.next = a;

        ListNode swapNode = swapPairs_01(begin);
        swapNode.printAllVal();
    }

    /**
     * 直接构造新的链表
     */
    public static ListNode swapPairs_01(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }
        ListNode ret = new ListNode(-1);
        ListNode pre = ret;
        ListNode a = head, b = head.next;

        while (Objects.nonNull(a) && Objects.nonNull(b)) {
            ListNode tempA = b.next;
            ListNode tempB = null;
            if (Objects.nonNull(b.next)) {
                tempB = b.next.next;
            }
            pre.next = b;
            pre.next.next = a;

            pre = a;
            a = tempA;
            b = tempB;
        }

        pre.next = Objects.isNull(a) ? b : a;
        return ret.next;
    }

    /**
     * 递归交换
     * 基线条件：递归的终止条件是链表中没有节点，或者链表中只有一个节点，此时无法进行交换。
     * 递归条件：链表中至少有两个节点
     */
    public static ListNode swapPairs_02(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }
        // 新节点是旧节点的下一个
        ListNode newHead = head.next;
        // 旧节点的下一个是新节点的下一个
        head.next = swapPairs_02(newHead.next);
        // 新节点的下一个是旧节点
        newHead.next = head;
        return newHead;
    }
}

