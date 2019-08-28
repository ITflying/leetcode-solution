package _2_两数相加;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 核心：简单的链表操作，快速
 * @date 2019/8/21
 **/
public class addTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = createListNodeByNums(5);
        ListNode l2 = createListNodeByNums(5);

        printListNode(l1);
        printListNode(l2);

        ListNode res = addTwoNumbers_3(l1, l2);
        printListNode(res);
    }


    /**
     * 第一种：遍历转换为数字列表然后循环进位相加， 速度巨慢，15ms
     * 时间复杂度应该为O（3n）
     */
    public static ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        List<Integer> num1 = getNumByListNode(l1);
        List<Integer> num2 = getNumByListNode(l2);

        ListNode res = null;
        ListNode temp = null;
        int addNext = 0;
        int len = num1.size() > num2.size() ? num1.size() : num2.size();
        for (int i = 0; i < len; i++) {
            int sum = addNext;
            if (i < num1.size()) sum = sum + num1.get(i);
            if (i < num2.size()) sum = sum + num2.get(i);
            int realNum = sum >= 10 ? sum % 10 : sum;
            addNext = sum >= 10 ? sum / 10 : 0;
            ListNode next = new ListNode(realNum);
            if (temp == null) {
                res = next;
                temp = res;
            } else {
                temp.next = next;
                temp = next;
            }
        }
        if (addNext > 0) {
            ListNode next = new ListNode(addNext);
            temp.next = next;
        }
        return res;
    }

    public static List<Integer> getNumByListNode(ListNode node) {
        List<Integer> numList = new ArrayList<>();

        ListNode temp = node;
        numList.add(temp.val);
        while (temp.next != null) {
            temp = temp.next;
            numList.add(temp.val);
        }
        return numList;
    }

    /**
     * 第二种：直接在链表上面进行操作，6ms
     * 时间复杂度应该是O（max(m,n)）
     */
    public static ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        ListNode res = null, temp = null;
        int addNext = 0;
        while (l1 != null || l2 != null) {
            int sum = addNext + null2zero(l1) + null2zero(l2);
            addNext = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            if (res == null) {
                res = newNode;
                temp = res;
            } else {
                temp.next = newNode;
                temp = newNode;
            }
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (addNext > 0) {
            temp.next = new ListNode(addNext);
        }
        return res;
    }

    public static int null2zero(ListNode node) {
        if (node == null) {
            return 0;
        }
        return node.val;
    }

    /**
     * 第三种方法：抄作业时间
     * 就利用原链表来实现
     */
    public static ListNode addTwoNumbers_3(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        int addNext = 0;
        while (q != null) {
            // 1. 补全下一位，保证两个listnode长度一样
            if (p.next == null && q.next != null) {
                p.next = new ListNode(0);
            }
            if (q.next == null && p.next != null) {
                q.next = new ListNode(0);
            }
            int sum = addNext + p.val + q.val;
            addNext = sum / 10;
            p.val = sum % 10;

            if (p.next == null && q.next == null && addNext > 0) {
                p.next = new ListNode(addNext);
            }

            p = p.next;
            q = q.next;
        }

        return l1;
    }

    // region 通用

    /**
     * 根据数字逆序创建链表
     */
    private static ListNode createListNodeByNums(int num) {
        String numStr = String.valueOf(num);

        ListNode main = new ListNode(numStr.charAt(numStr.length() - 1) - 48);
        ListNode temp = main;
        for (int i = numStr.length() - 2; i >= 0; i--) {
            ListNode next = new ListNode(numStr.charAt(i) - 48);
            temp.next = next;
            temp = next;
        }
        return main;
    }

    /**
     * 逆序输出链表数字
     */
    private static void printListNode(ListNode node) {
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(node.val));

        ListNode temp = node;
        while (temp.next != null) {
            temp = temp.next;
            list.add(String.valueOf(temp.val));
        }
        Collections.reverse(list);
        list.forEach(System.out::print);
        System.out.println();
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // endregion
}
