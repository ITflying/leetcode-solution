package _17_to_32._19_删除链表的倒数第N个节点;

import Common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * <p>
 * 说明：
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2020/6/10
 **/
public class RemoveNthFromEnd {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
//        ListNode a2 = new ListNode(2);
//        ListNode a3 = new ListNode(3);
//        ListNode a4 = new ListNode(4);
//        ListNode a5 = new ListNode(5);
//        head.next = a2;
//        a2.next = a3;
//        a3.next = a4;
//        a4.next = a5;

        ListNode tempHead = removeNthFromEnd_02(head, 1);
        ListNode temp = tempHead;
        while (Objects.nonNull(temp)) {
            System.out.print(temp.val + "\t");
            temp = temp.next;
        }
        System.out.println();

//        ListNode tempHead2 = removeNthFromEnd_02(head, 2);
//        ListNode temp2 = tempHead2;
//        while (Objects.nonNull(temp2)) {
//            System.out.print(temp2.val + "\t");
//            temp2 = temp2.next;
//        }

//        System.out.println();
//        ListNode tempHead3 = removeNthFromEnd_02(head, 3);
//        ListNode temp3 = tempHead3;
//        while (Objects.nonNull(temp3)) {
//            System.out.print(temp3.val + "\t");
//            temp3 = temp3.next;
//        }
    }

    /**
     * 用一个数组存起来
     *
     * @param head
     * @param n
     * @return
     */
    private static ListNode removeNthFromEnd_01(ListNode head, int n) {
        // 0、特殊情况特殊处理
        if (Objects.isNull(head.next)) {
            return null;
        } else if (Objects.isNull(head.next.next)) {
            if (n == 2) {
                return head.next;
            } else {
                head.next = null;
                return head;
            }
        }


        ListNode temp = head;

        // 1. 构造list存储数据
        List<ListNode> nodeList = new ArrayList<>();
        while (Objects.nonNull(temp)) {
            nodeList.add(temp);
            temp = temp.next;
        }

        // 2. 修改制定位置
        int pos = nodeList.size() - n;
        if (pos == 0) {
            return head.next;
        } else if (pos == nodeList.size() - 1) {
            nodeList.get(pos - 1).next = null;
        } else {
            nodeList.get(pos - 1).next = nodeList.get(pos + 1);
        }

        return head;
    }

    /**
     * 快慢指针法
     * 就是设置三个指针，pre指针指向head，star往前走，end和star保持n个距离，到最后的时候end就是要移除的
     *
     * @param head
     * @param n
     * @return
     */
    private static ListNode removeNthFromEnd_02(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode start = pre, end = pre;

        while (start.next != null) {
            n = n-1;
            if (n >= 0) {
                start = start.next;
            } else {
                start = start.next;
                end = end.next;
            }
        }
        
        end.next=end.next.next;
        return pre.next;
    }
}
