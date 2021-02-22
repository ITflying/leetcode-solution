package _17_to_32._21_合并两个有序链表;

import Common.CheckUtil;
import Common.ListNode;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * 
 * 提示：
 * 
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/02/18
 **/
public class MergeTwoLists {
    public static void main(String[] args) throws ServerException {
        ListNode b = new ListNode(4);
        ListNode a = new ListNode(2, b);
        ListNode begin = new ListNode(1);
        begin.next = a;

        ListNode B = new ListNode(4);
        ListNode A = new ListNode(3, B);
        ListNode BEGIN = new ListNode(1);
        BEGIN.next = A;

        ListNode newL = mergeTwoLists_02(begin, BEGIN);
        newL.printAllVal();
    }


    /**
     * 循环构造新的表
     */
    private static ListNode mergeTwoLists_01(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1) && Objects.isNull(l2)) {
            return null;
        }

        ListNode retNode = new ListNode();
        ListNode nextNode = new ListNode();
        retNode.next = nextNode;
        ListNode templ1 = new ListNode();
        templ1.next = l1;
        ListNode templ2 = new ListNode();
        templ2.next = l2;

        while (Objects.nonNull(templ1.next) || Objects.nonNull(templ2.next)) {
            boolean flag1 = (Objects.nonNull(templ1.next) && Objects.nonNull(templ2.next) && templ1.next.val <= templ2.next.val)
                    || (Objects.nonNull(templ1.next) && Objects.isNull(templ2.next));
            if (flag1) {
                nextNode.val = templ1.next.val;
                templ1 = templ1.next;
            } else {
                nextNode.val = templ2.next.val;
                templ2 = templ2.next;
            }

            if (Objects.nonNull(templ1.next) || Objects.nonNull(templ2.next)) {
                ListNode tempN = new ListNode();
                nextNode.next = tempN;
                nextNode = tempN;
            }
        }
        return retNode.next;
    }

    /**
     * 递归实现，实际分解到最后就是小问题
     */
    private static ListNode mergeTwoLists_02(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) {
            return l2;
        } else if (Objects.isNull(l2)) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists_02(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_02(l1, l2.next);
            return l2;
        }
    }

    /**
     * 官方迭代
     */
    private static ListNode mergeTwoLists_03(ListNode l1, ListNode l2) {
        ListNode preNode = new ListNode(-1);
        ListNode pre = preNode;
        while (Objects.nonNull(l1) && Objects.nonNull(l2)) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = Objects.nonNull(l1) ? l1 : l2;
        return preNode.next;
    }
}
