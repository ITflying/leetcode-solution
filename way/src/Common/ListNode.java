package Common;

import java.util.List;
import java.util.Objects;

/**
 * 通用链表结构
 *
 * @date 2020/6/10
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void printAllVal() {
        System.out.print(val + "\t");
        ListNode temp = next;
        while (Objects.nonNull(temp)) {
            System.out.print(temp.val + "\t");
            temp = temp.next;
        }
        System.out.println("");
    }
}
